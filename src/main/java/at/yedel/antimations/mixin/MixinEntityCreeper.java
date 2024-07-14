package at.yedel.antimations.mixin;



import at.yedel.antimations.utils.AnimationCanceller;
import at.yedel.antimations.config.AntimationsConfig;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;



@Mixin(EntityCreeper.class)
public abstract class MixinEntityCreeper {
    @Redirect(method = "interact", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/EntityPlayer;swingItem()V"))
    public void antimations$onIgniteCreeper(EntityPlayer instance) {
        if (AntimationsConfig.getInstance().cancelCreeperIgnitionSwings.get()) {
            AnimationCanceller.sendAnimationPacket();
        }
        else instance.swingItem();
    }
}
