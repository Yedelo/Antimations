package at.yedel.antimations.mixin;



import at.yedel.antimations.config.AntimationsConfig;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;



@Mixin(ItemRenderer.class)
public class MixinItemRenderer {
    @Shadow @Final private RenderItem itemRenderer;

    /*
        Complicated stuff.
        We want it to only reset the equipped progress when switching between different actual items.
        This happens when dropping your last item or switching items but also happens when the durability or stack size changes.
        This changes the method to make it return true if the actual items themselves are true
        Before: it checked if the item ids were equal
        This had the effect of not changing the item when the items had the same id (different types of planks and such)
        After: check for the item texture, which should be different for all items.
    */

    @Redirect(method = "updateEquippedItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getIsItemStackEqual(Lnet/minecraft/item/ItemStack;)Z"))
    public boolean antimations$simplifyEqual(ItemStack instance, ItemStack p_179549_1_) {
        if (!AntimationsConfig.instance.cancelItemUpdateHandResets.get()) {
            return instance.getIsItemStackEqual(p_179549_1_);
        }
        else {
            return this.itemRenderer.getItemModelMesher().getItemModel(instance) == this.itemRenderer.getItemModelMesher().getItemModel(p_179549_1_);
        }
    }
}
