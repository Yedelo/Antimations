package at.yedel.antimations.utils;



import at.yedel.antimations.config.AntimationsConfig;



public class CurrentSwingContext {
    public static SwingContext currentContext;

    public static void context(SwingContext context) {
        currentContext = context;
    }

    public static boolean shouldCancel() {
        return switch (currentContext) {
            case AIR -> AntimationsConfig.getInstance().cancelAirSwings;
            case ENTITY_ATTACK -> AntimationsConfig.getInstance().cancelEntityHitSwings;
            case BLOCK_HIT -> AntimationsConfig.getInstance().cancelBlockHitSwings;
            case BLOCK_INTERACT -> AntimationsConfig.getInstance().cancelBlockInteractSwings;
            case FISHING_ROD -> AntimationsConfig.getInstance().cancelFishingRodSwings;
            case CREEPER_IGNITION -> AntimationsConfig.getInstance().cancelCreeperIgnitionSwings;
            case null, default -> false;
        };
    }
}
