package at.yedel.antimations.mixin;



import at.yedel.antimations.config.AntimationsConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(RendererLivingEntity.class)
public abstract class MixinRendererLivingEntity<T extends EntityLivingBase> {
    @Inject(method = "doRender(Lnet/minecraft/entity/EntityLivingBase;DDDFF)V", at = @At("HEAD"))
    private void antimations$cancelLimbMovements(T entity, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo ci) {
        if (!AntimationsConfig.getInstance().enabled) return;
        boolean isPlayer = entity == Minecraft.getMinecraft().thePlayer;
        if (
            (AntimationsConfig.getInstance().cancelOwnLimbMovements && isPlayer) || (AntimationsConfig.getInstance().cancelOtherLimbMovements && !isPlayer)
        ) {
            entity.limbSwingAmount *= AntimationsConfig.getInstance().cancelLimbScalingMultiplier;
        }
        else if (
            (AntimationsConfig.getInstance().weirderOwnLimbMovements && isPlayer) || (AntimationsConfig.getInstance().weirderOtherLimbMovements && !isPlayer)
        ) {
            entity.limbSwing *= AntimationsConfig.getInstance().weirderLimbScalingMultiplier;
        }
    }
}
