package at.yedel.antimations.mixin;



import at.yedel.antimations.utils.CurrentSwingContext;
import at.yedel.antimations.utils.SwingContext;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MovingObjectPosition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(Minecraft.class)
public abstract class MixinMinecraft {
	@Shadow public MovingObjectPosition objectMouseOver;

	@Inject(method = "sendClickBlockToController", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;swingItem()V"))
	private void antimations$onSwingAtBlock(boolean leftClick, CallbackInfo ci) {
		CurrentSwingContext.context(SwingContext.BLOCK_HIT);
	}

	@Inject(method = "clickMouse", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;swingItem()V"))
	private void antimations$onSwingBareOrAtEntity(CallbackInfo ci) {
        if (objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.MISS) {
            CurrentSwingContext.context(SwingContext.AIR);
        }
        else if (objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            CurrentSwingContext.context(SwingContext.BLOCK_HIT);
        }
        else if (objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
			CurrentSwingContext.context(SwingContext.ENTITY_ATTACK);
        }
    }

	@Inject(method = "rightClickMouse", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;swingItem()V"))
	private void antimations$onInteractWithBlock(CallbackInfo ci) {
		CurrentSwingContext.context(SwingContext.BLOCK_INTERACT);
	}
}
