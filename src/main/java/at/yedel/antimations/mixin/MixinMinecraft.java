package at.yedel.antimations.mixin;



import at.yedel.antimations.utils.AnimationCanceller;
import at.yedel.antimations.config.AntimationsConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;



@Mixin(Minecraft.class)
public abstract class MixinMinecraft {
	@Redirect(method = "sendClickBlockToController", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;swingItem()V"))
	private void antimations$onSwingAtBlock(EntityPlayerSP instance) {
		if (AntimationsConfig.getInstance().cancelBlockHitSwings.get()) {
			AnimationCanceller.sendAnimationPacket();
		}
		else instance.swingItem();
	}

	@Redirect(method = "clickMouse", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;swingItem()V"))
	private void antimations$onSwingBareOrAtEntity(EntityPlayerSP instance) {
		if (AntimationsConfig.getInstance().cancelAirOrEntitySwings.get()) {
			AnimationCanceller.sendAnimationPacket();
		}
		else instance.swingItem();
	}

	@Redirect(method = "rightClickMouse", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;swingItem()V"))
	private void antimations$onInteractWithBlock(EntityPlayerSP instance) {
		if (AntimationsConfig.getInstance().cancelBlockInteractSwings.get()) {
			AnimationCanceller.sendAnimationPacket();
		}
		else instance.swingItem();
	}
}
