package at.yedel.antimations.mixin;



import at.yedel.antimations.config.AntimationsConfig;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;



@Mixin(ItemRenderer.class)
public abstract class MixinItemRenderer {
	@Shadow
	private ItemStack itemToRender;

	@Redirect(method = "renderItemInFirstPerson", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;getItemInUseCount()I"))
	public int antimations$cancelCertainAnimations(AbstractClientPlayer instance) {
		EnumAction itemUseAction = itemToRender.getItemUseAction();
		if (
			(AntimationsConfig.getInstance().cancelOwnBlockAnimations.get() && itemUseAction == EnumAction.BLOCK)
				||
				(AntimationsConfig.getInstance().cancelEatingAnimations.get() && itemUseAction == EnumAction.EAT)
				||
				(AntimationsConfig.getInstance().cancelDrinkingAnimations.get() && itemUseAction == EnumAction.DRINK)
		) {
			return 0;
		}
		else return instance.getItemInUseDuration();
	}
}
