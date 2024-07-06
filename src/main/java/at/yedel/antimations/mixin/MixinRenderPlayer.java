package at.yedel.antimations.mixin;



import at.yedel.antimations.config.AntimationsConfig;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;



@Mixin(RenderPlayer.class)
public class MixinRenderPlayer {
    // this is in the if block '(if item action is BLOCK)'
    @Redirect(method = "setModelVisibilities", at = @At(value = "FIELD", target = "Lnet/minecraft/client/model/ModelPlayer;heldItemRight:I", opcode = Opcodes.PUTFIELD, ordinal = 2))
    public void doSomething(ModelPlayer instance, int value) {
        if (!AntimationsConfig.instance.cancelThirdPersonBlockAnimations.get()) {
            instance.heldItemRight = 3;
        }
        // else { don't change it }
    }
}
