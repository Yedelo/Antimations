package at.yedel.antimations.mixin;



import at.yedel.antimations.config.AntimationsConfig;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(RenderPlayer.class)
public abstract class MixinRenderPlayer {
    @Shadow public abstract ModelPlayer getMainModel();

    @Inject(method = "setModelVisibilities", at = @At("TAIL"))
    private void antimations$removeStatuses(AbstractClientPlayer clientPlayer, CallbackInfo ci) {
        ModelPlayer modelPlayer = getMainModel();
        if (AntimationsConfig.getInstance().cancelThirdPersonBlockAnimations.get() && modelPlayer.heldItemRight == 3) {
            modelPlayer.heldItemRight = 1;
        }
        if (AntimationsConfig.getInstance().cancelThirdPersonBowAnimations.get()) {
            modelPlayer.aimedBow = false;
        }
    }
}
