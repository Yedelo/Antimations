package at.yedel.antimations.mixin;



import at.yedel.antimations.config.AntimationsConfig;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;



@Mixin(NetHandlerPlayClient.class)
public class MixinNetHandlerPlayClient {
    @Redirect(method = "handleAnimation", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;swingItem()V"))
    public void antimations$onOtherPlayerSwings(EntityLivingBase instance) {
        if (!AntimationsConfig.instance.cancelOtherPlayerSwings.get()) instance.swingItem();
    }
}
