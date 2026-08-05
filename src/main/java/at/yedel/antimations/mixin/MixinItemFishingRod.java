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
    private void antimations$onUseFishingRod(EntityPlayer instance) {
        if (AntimationsConfig.getInstance().enabled && AntimationsConfig.getInstance().cancelFishingRodSwings) {
            AnimationCanceller.sendAnimationPacket();
        }
        else instance.swingItem();
    }
}
