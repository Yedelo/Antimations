package at.yedel.antimations.mixin;



import at.yedel.antimations.utils.CurrentSwingContext;
import at.yedel.antimations.utils.SwingContext;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;



@Mixin(EntityCreeper.class)
public abstract class MixinEntityCreeper {
    @Inject(method = "interact", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/EntityPlayer;swingItem()V"))
    private void antimations$onIgniteCreeper(EntityPlayer player, CallbackInfoReturnable<Boolean> cir) {
        CurrentSwingContext.context = SwingContext.CREEPER_IGNITION;
    }
}
