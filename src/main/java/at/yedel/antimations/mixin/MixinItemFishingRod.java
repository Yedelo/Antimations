package at.yedel.antimations.mixin;



import at.yedel.antimations.utils.AnimationCanceller;
import at.yedel.antimations.config.AntimationsConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFishingRod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;



@Mixin(ItemFishingRod.class)
public class MixinItemFishingRod {
    @Redirect(method = "onItemRightClick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/EntityPlayer;swingItem()V"))
    public void antimations$onUseFishingRod(EntityPlayer instance) {
        // This happens when you right click with a fishing rod.
        if (!AntimationsConfig.instance.cancelFishingRodSwings.get()) {
            instance.swingItem();
        }
        else AnimationCanceller.sendAnimationPacket();
    }
}
