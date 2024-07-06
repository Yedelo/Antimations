package at.yedel.antimations.mixin;



import at.yedel.antimations.utils.AnimationCanceller;
import at.yedel.antimations.config.AntimationsConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;



@Mixin(Minecraft.class)
public class MixinMinecraft {
    @Redirect(method = "sendClickBlockToController", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;swingItem()V"))
    public void antimations$onSwingAtBlock(EntityPlayerSP instance) {
        // This happens when you're swinging at a block.
        if (!AntimationsConfig.getInstance().cancelBlockHitSwings.get()) {
            instance.swingItem();
        }
        else AnimationCanceller.sendAnimationPacket();
    }

    @Redirect(method = "clickMouse", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;swingItem()V"))
    public void antimations$onSwingBareOrAtEntity(EntityPlayerSP instance) {
        // This happens when you swing bare or at an entity.
        if (!AntimationsConfig.getInstance().cancelAirOrEntitySwings.get()) {
            instance.swingItem();
        }
        else AnimationCanceller.sendAnimationPacket();
    }

    @Redirect(method = "rightClickMouse", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;swingItem()V"))
    public void antimations$onPlaceBlockOrInteractWithOne(EntityPlayerSP instance) {
        // This is the swing method and happens when you place a block or interact a chest or something.
        if (!AntimationsConfig.getInstance().cancelBlockInteractSwings.get()) {
            instance.swingItem();
        }
        else AnimationCanceller.sendAnimationPacket();
    }

    @Redirect(method = "rightClickMouse", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemRenderer;resetEquippedProgress()V"))
    public void antimations$onUseItemAndItDoesntGoAway(ItemRenderer instance) {
        // This happens when you look down and use a snowball or something (when item count doesnt change)
        if (!AntimationsConfig.getInstance().cancelItemUseHandResets.get()) {
            instance.resetEquippedProgress();
        }
    }

    @Redirect(method = "rightClickMouse", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemRenderer;resetEquippedProgress2()V"))
    public void antimations$onUseItemAndItDoesGoAway(ItemRenderer instance) {
        // This happens when you look down and use a snowball or something (when item count changes)
        if (!AntimationsConfig.getInstance().cancelItemConsumptionHandResets.get()) {
            instance.resetEquippedProgress();
        }
    }
}
