package at.yedel.antimations.mixin;



import at.yedel.antimations.config.AntimationsConfig;
import net.minecraft.client.Minecraft;
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
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(ItemRenderer.class)
public abstract class MixinItemRenderer {
	@Shadow private ItemStack itemToRender;
	@Shadow @Final private RenderItem itemRenderer;
	@Shadow private int equippedItemSlot;
	@Shadow @Final private Minecraft mc;

	@Redirect(method = "updateEquippedItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getIsItemStackEqual(Lnet/minecraft/item/ItemStack;)Z"))
	public boolean antimations$simplifyEqual(ItemStack instance, ItemStack p_179549_1_) {
		if (AntimationsConfig.getInstance().cancelItemUpdateHandResets.get()) {
			if (equippedItemSlot != mc.thePlayer.inventory.currentItem) return false;
			return itemRenderer.getItemModelMesher().getItemModel(instance) == itemRenderer.getItemModelMesher().getItemModel(p_179549_1_);
		}
		return instance.getIsItemStackEqual(p_179549_1_);
	}

	@Redirect(method = "updateEquippedItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;shouldCauseReequipAnimation(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;Z)Z"))
	public boolean antimations$cancelAllHandResets(Item instance, ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		if (AntimationsConfig.getInstance().cancelAllHandResets.get()) return false;
		return instance.shouldCauseReequipAnimation(oldStack, newStack, slotChanged);
	}

	@Redirect(method = "renderItemInFirstPerson", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;getItemInUseCount()I"))
	public int antimations$cancelFirstPersonAnimations(AbstractClientPlayer instance) {
		EnumAction itemUseAction = itemToRender.getItemUseAction();
		if (
			(AntimationsConfig.getInstance().cancelOwnBlockAnimations.get() && itemUseAction == EnumAction.BLOCK)
			||
			(AntimationsConfig.getInstance().cancelOwnBowAnimations.get() && itemUseAction == EnumAction.BOW)
			||
			(AntimationsConfig.getInstance().cancelEatingAnimations.get() && itemUseAction == EnumAction.EAT)
			||
			(AntimationsConfig.getInstance().cancelDrinkingAnimations.get() && itemUseAction == EnumAction.DRINK)
		) {
			return 0;
		}
		else return instance.getItemInUseDuration();
	}

	@Inject(method = "resetEquippedProgress", at = @At("HEAD"), cancellable = true)
	public void antimations$onUseItem(CallbackInfo ci) {
		if (AntimationsConfig.getInstance().cancelItemUseHandResets.get()) {
			ci.cancel();
		}
	}

	@Inject(method = "resetEquippedProgress2", at = @At("HEAD"), cancellable = true)
	public void antimations$onConsumeItem(CallbackInfo ci) {
		if (AntimationsConfig.getInstance().cancelItemUseHandResets.get()) {
			ci.cancel();
		}
	}
}
