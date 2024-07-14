package at.yedel.antimations.mixin;



import at.yedel.antimations.Antimations;
import at.yedel.antimations.config.AntimationsConfig;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;



@Mixin(Item.class)
public abstract class MixinItem {
	@Inject(method = "shouldCauseReequipAnimation", at = @At("HEAD"), remap = false, cancellable = true)
	public void antimations$simplifyEqual(ItemStack oldStack, ItemStack newStack, boolean slotChanged, CallbackInfoReturnable<Boolean> cir) {
		if (AntimationsConfig.getInstance().cancelAllHandResets.get()) cir.setReturnValue(false);
		else if (AntimationsConfig.getInstance().cancelItemUpdateHandResets.get()) {
			if (slotChanged) cir.setReturnValue(true);
			cir.setReturnValue(Antimations.itemModelMesher.getItemModel(oldStack) != Antimations.itemModelMesher.getItemModel(newStack));
		}
	}
}
