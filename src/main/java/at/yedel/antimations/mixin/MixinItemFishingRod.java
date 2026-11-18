package at.yedel.antimations.mixin;



import at.yedel.antimations.utils.CurrentSwingContext;
import at.yedel.antimations.utils.SwingContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;



@Mixin(ItemFishingRod.class)
public class MixinItemFishingRod {
    @Inject(method = "onItemRightClick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/EntityPlayer;swingItem()V"))
    private void antimations$onUseFishingRod(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, CallbackInfoReturnable<ItemStack> cir) {
        CurrentSwingContext.context = SwingContext.FISHING_ROD;
    }
}
