package at.yedel.antimations.mixin;



import at.yedel.antimations.utils.AnimationCanceller;
import at.yedel.antimations.config.AntimationsConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;



@Mixin(Minecraft.class)
public abstract class MixinMinecraft {
	@Shadow public MovingObjectPosition objectMouseOver;

	@Redirect(method = "sendClickBlockToController", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;swingItem()V"))
	private void antimations$onSwingAtBlock(EntityPlayerSP instance) {
		if (AntimationsConfig.getInstance().enabled && AntimationsConfig.getInstance().cancelBlockHitSwings) {
			AnimationCanceller.sendAnimationPacket();
		}
		else instance.swingItem();
	}

	@Redirect(method = "clickMouse", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;swingItem()V"))
	private void antimations$onSwingBareOrAtEntity(EntityPlayerSP instance) {
		if (AntimationsConfig.getInstance().enabled) {
			boolean shouldCancel = false;
			if (AntimationsConfig.getInstance().cancelAirSwings && objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.MISS) {
				shouldCancel = true;
			}
			else if (AntimationsConfig.getInstance().cancelBlockHitSwings && objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
				shouldCancel = true;
			}
			else if (AntimationsConfig.getInstance().cancelEntityHitSwings && objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
				if (AntimationsConfig.getInstance().cancelOnlyHurtEntitySwings && objectMouseOver.entityHit instanceof EntityLivingBase) {
					// hurtTimeAdjustment = 10: if hurt time is over 10 - 10 = 0 (if the hurt time is anything at all), then cancel swings
					// hurtTimeAdjustment = 1: if hurt time is over 10 - 1 = 9 (just 1 tick), then cancel swings
					shouldCancel = ((EntityLivingBase) objectMouseOver.entityHit).hurtTime > (10 - AntimationsConfig.getInstance().hurtTimeAdjustment);
				}
				else {
					shouldCancel = true;
				}
			}
			if (shouldCancel) {
				AnimationCanceller.sendAnimationPacket();
			}
			else {
				instance.swingItem();
			}
		}
		else instance.swingItem();
	}

	@Redirect(method = "rightClickMouse", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;swingItem()V"))
	private void antimations$onInteractWithBlock(EntityPlayerSP instance) {
		if (AntimationsConfig.getInstance().enabled && AntimationsConfig.getInstance().cancelBlockInteractSwings) {
			AnimationCanceller.sendAnimationPacket();
		}
		else instance.swingItem();
	}
}
