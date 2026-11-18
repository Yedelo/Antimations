package at.yedel.antimations.mixin;



import at.yedel.antimations.config.AntimationsConfig;
import at.yedel.antimations.utils.CurrentSwingContext;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase {
    @Inject(method = "swingItem", at = @At("HEAD"), cancellable = true)
    private void antimations$cancelSwings(CallbackInfo ci) {
        if (AntimationsConfig.getInstance().enabled && CurrentSwingContext.shouldCancel()) {
            ci.cancel();
        }
    }
}
