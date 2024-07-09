package at.yedel.antimations.mixin;



import at.yedel.antimations.config.AntimationsConfig;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;



@Mixin(ItemRenderer.class)
public abstract class MixinItemRenderer {
    @Shadow @Final private RenderItem itemRenderer;

    @Shadow private ItemStack itemToRender;

    @Redirect(method = "updateEquippedItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;shouldCauseReequipAnimation(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;Z)Z"))
    public boolean antimations$simplifyEqual(Item instance, ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        if (!AntimationsConfig.getInstance().cancelItemUpdateHandResets.get()) {
            return instance.shouldCauseReequipAnimation(oldStack, newStack, slotChanged);
        }
        else {
            if (slotChanged) return true;
            return itemRenderer.getItemModelMesher().getItemModel(oldStack) == itemRenderer.getItemModelMesher().getItemModel(newStack);
        }
    }

    @Redirect(method = "renderItemInFirstPerson", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;getItemInUseCount()I"))
    public int antimations$cancelOwnBlockAnimations(AbstractClientPlayer instance) {
        if (!AntimationsConfig.getInstance().cancelOwnBlockAnimations.get() || itemToRender.getItemUseAction() != EnumAction.BLOCK) {
            return instance.getItemInUseDuration();
        }
        return 0;
    }
}
