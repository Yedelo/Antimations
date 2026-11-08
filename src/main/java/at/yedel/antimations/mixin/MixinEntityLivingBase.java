package at.yedel.antimations.mixin;



import at.yedel.antimations.features.FakeSwingOnDamage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase extends Entity {
    public MixinEntityLivingBase(World worldIn) {
        super(worldIn);
    }

    @Inject(method = "performHurtAnimation", at = @At("HEAD"))
    private void antimations$handleHurtAnimation(CallbackInfo ci) {
        FakeSwingOnDamage.getInstance().onHurtAnimation(getEntityId());
    }

    @Inject(method = "handleStatusUpdate", at = @At("HEAD"))
    private void antimations$handleStatusUpdate(byte id, CallbackInfo ci) {
        if (id == 2) {
            FakeSwingOnDamage.getInstance().onHurtAnimation(getEntityId());
        }
    }
}
