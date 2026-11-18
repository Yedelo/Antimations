package at.yedel.antimations.utils;



import at.yedel.antimations.config.AntimationsConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;



public class CurrentSwingContext {
    private static SwingContext currentContext;
    private static Entity currentEntity;

    public static void context(SwingContext context) {
        currentContext = context;
    }

    public static void entity(Entity entity) {
        currentEntity = entity;
    }

    public static boolean shouldCancel() {
        return switch (currentContext) {
            case AIR -> AntimationsConfig.getInstance().cancelAirSwings;
            case ENTITY_ATTACK -> AntimationsConfig.getInstance().cancelEntityHitSwings && shouldCancelEntityHit();
            case BLOCK_HIT -> AntimationsConfig.getInstance().cancelBlockHitSwings;
            case BLOCK_INTERACT -> AntimationsConfig.getInstance().cancelBlockInteractSwings;
            case FISHING_ROD -> AntimationsConfig.getInstance().cancelFishingRodSwings;
            case CREEPER_IGNITION -> AntimationsConfig.getInstance().cancelCreeperIgnitionSwings;
            case null, default -> false;
        };
    }

    private static boolean shouldCancelEntityHit() {
            if (AntimationsConfig.getInstance().cancelOnlyHurtEntitySwings && currentEntity instanceof EntityLivingBase entityLiving) {
                // hurtTimeAdjustment = 10: if hurt time is over 10 - 10 = 0 (if the hurt time is anything at all), then cancel swings
                // hurtTimeAdjustment = 1: if hurt time is over 10 - 1 = 9 (just 1 tick), then cancel swings
                return entityLiving.hurtTime > (10 - AntimationsConfig.getInstance().hurtTimeAdjustment);
            }
            return true;
    }
}
