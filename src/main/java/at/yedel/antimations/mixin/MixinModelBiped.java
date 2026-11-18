package at.yedel.antimations.mixin;



import at.yedel.antimations.config.AntimationsConfig;
import at.yedel.antimations.utils.CurrentSwingContext;
import com.llamalad7.mixinextras.expression.Definition;import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.model.ModelBiped;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;



@Mixin(ModelBiped.class)
public abstract class MixinModelBiped {
    @Definition(id = "swingProgress", field = "Lnet/minecraft/client/model/ModelBiped;swingProgress:F")
    @Expression("this.swingProgress > -9990.0")
    @ModifyExpressionValue(method = "setRotationAngles", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean antimations$cancelModelSwings(boolean original) {
        if (AntimationsConfig.getInstance().enabled && CurrentSwingContext.shouldCancel()) {
            return false;
        }
        return original;
    }
}
