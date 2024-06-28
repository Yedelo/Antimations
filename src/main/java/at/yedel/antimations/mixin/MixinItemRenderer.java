package at.yedel.antimations.mixin;



import at.yedel.antimations.config.AntimationsConfig;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;



@Mixin(ItemRenderer.class)
public class MixinItemRenderer {
    @Redirect(method = "updateEquippedItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getIsItemStackEqual(Lnet/minecraft/item/ItemStack;)Z"))
    public boolean antimations$simplifyEqual(ItemStack instance, ItemStack p_179549_1_) {
        if (!AntimationsConfig.instance.cancelItemUpdateHandResets.get()) {
            return instance.getIsItemStackEqual(p_179549_1_);
        }
        else {
            return instance.getItem().getRegistryName().equals(p_179549_1_.getItem().getRegistryName());
        }
    }
}
