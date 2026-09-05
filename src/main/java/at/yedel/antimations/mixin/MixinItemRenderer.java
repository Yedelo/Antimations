package at.yedel.antimations.mixin;



import at.yedel.antimations.config.AntimationsConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(ItemRenderer.class)
public abstract class MixinItemRenderer {
	@Shadow private ItemStack itemToRender;
	@Shadow @Final private RenderItem itemRenderer;
	@Shadow private int equippedItemSlot;
	@Shadow @Final private Minecraft mc;

	@Redirect(method = "updateEquippedItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getIsItemStackEqual(Lnet/minecraft/item/ItemStack;)Z"))
	private boolean antimations$simplifyEqual(ItemStack instance, ItemStack p_179549_1_) {
		if (AntimationsConfig.getInstance().enabled) {
			if (AntimationsConfig.getInstance().cancelAllHandResets) {
				EntityPlayer player = Minecraft.getMinecraft().thePlayer;
				this.itemToRender = player.inventory.getCurrentItem();
				this.equippedItemSlot = player.inventory.currentItem;
				return true;
			}
			if (AntimationsConfig.getInstance().cancelItemUpdateHandResets) {
				if (equippedItemSlot != mc.thePlayer.inventory.currentItem) return false;
				return itemRenderer.getItemModelMesher().getItemModel(instance) == itemRenderer.getItemModelMesher().getItemModel(p_179549_1_);
			}
		}
		return instance.getIsItemStackEqual(p_179549_1_);
	}

	@Redirect(method = "renderItemInFirstPerson", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItemUseAction()Lnet/minecraft/item/EnumAction;"))
	private EnumAction antimations$cancelFirstPersonAnimations(ItemStack instance) {
		EnumAction itemUseAction = itemToRender.getItemUseAction();
		if (
			AntimationsConfig.getInstance().enabled && (
				(AntimationsConfig.getInstance().cancelOwnBlockAnimations && itemUseAction == EnumAction.BLOCK)
					||
					(AntimationsConfig.getInstance().cancelOwnBowAnimations && itemUseAction == EnumAction.BOW)
					||
					(AntimationsConfig.getInstance().cancelEatingAnimations && itemUseAction == EnumAction.EAT)
					||
					(AntimationsConfig.getInstance().cancelDrinkingAnimations && itemUseAction == EnumAction.DRINK)
			)
		) {
			return EnumAction.NONE;
		}
		else return instance.getItemUseAction();
	}

	@Inject(method = "resetEquippedProgress", at = @At("HEAD"), cancellable = true)
	private void antimations$onUseItem(CallbackInfo ci) {
		if (AntimationsConfig.getInstance().enabled && AntimationsConfig.getInstance().cancelItemUseHandResets) {
			ci.cancel();
		}
	}

	@Inject(method = "resetEquippedProgress2", at = @At("HEAD"), cancellable = true)
	private void antimations$onConsumeItem(CallbackInfo ci) {
		if (AntimationsConfig.getInstance().enabled && AntimationsConfig.getInstance().cancelItemUseHandResets) {
			ci.cancel();
		}
	}
}
