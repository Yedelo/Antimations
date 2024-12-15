package at.yedel.antimations.config;



import java.io.File;

import net.minecraftforge.common.config.Configuration;



public class AntimationsConfig {
    private static final AntimationsConfig instance = new AntimationsConfig();
    
    public static AntimationsConfig getInstance() {
        return instance;
    }
    
    public Configuration config;

    public ToggleObject cancelCreeperIgnitionSwings;
    public ToggleObject cancelFishingRodSwings;
    public ToggleObject cancelBlockHitSwings; // might be confusing, not blockhitting but when you hit a block
    public ToggleObject cancelAirOrEntitySwings;
    public ToggleObject cancelBlockInteractSwings;
    public ToggleObject cancelOtherPlayerSwings;

    public ToggleObject cancelItemUseHandResets;
    public ToggleObject cancelItemUpdateHandResets;
    public ToggleObject cancelAllHandResets;

    public ToggleObject cancelOwnBlockAnimations;
    public ToggleObject cancelThirdPersonBlockAnimations;
    public ToggleObject cancelOwnBowAnimations;
    public ToggleObject cancelThirdPersonBowAnimations;
    public ToggleObject cancelEatingAnimations;
    public ToggleObject cancelDrinkingAnimations;
    public ToggleObject cancelOwnLimbMovements;
    public ToggleObject cancelOtherLimbMovements;

    public void setupConfig(File recommendedConfigFile) {
        config = new Configuration(recommendedConfigFile);
        config.load();

        cancelCreeperIgnitionSwings = new ToggleObject(
            "Cancel Creeper Ignition Swings",
            "Cancel swinging your hand when igniting a creeper.",
            ConfigCategory.SWING_CUSTOMIZATION,
            "cancelCreeperIgnitionSwings",
            true
        );
        cancelFishingRodSwings = new ToggleObject(
            "Cancel Fishing Rod Swings",
            "Cancel swinging your hand when using a fishing rod.",
            ConfigCategory.SWING_CUSTOMIZATION,
            "cancelFishingRodSwings",
            true
        );
        cancelBlockHitSwings = new ToggleObject(
            "Cancel Block Hit Swings",
            "Cancel swinging your hand when hitting a block.",
            ConfigCategory.SWING_CUSTOMIZATION,
            "cancelBlockHitSwings",
            true
        );
        cancelAirOrEntitySwings = new ToggleObject(
            "Cancel Air or Entity Swings",
            "Cancel swinging your hand when swinging at the air or at an entity.",
            ConfigCategory.SWING_CUSTOMIZATION,
            "cancelAirOrEntitySwings",
            true
        );
        cancelBlockInteractSwings = new ToggleObject(
            "Cancel Block Interact Swings",
            "Cancel swinging your hand when interacting with blocks.",
            ConfigCategory.SWING_CUSTOMIZATION,
            "cancelBlockInteractSwings",
            true
        );
        cancelOtherPlayerSwings = new ToggleObject(
            "Cancel Other Player Swings",
            "Cancel swing animations from other players.",
            ConfigCategory.SWING_CUSTOMIZATION,
            "cancelOtherPlayerSwings",
            false
        );

        cancelItemUseHandResets = new ToggleObject(
            "Cancel Item Use Hand Resets",
            "Cancel your hand doing the re-equip animation when using an item.",
            ConfigCategory.ITEM_RESET,
            "cancelItemUseHandResets",
            true
        );
        cancelItemUpdateHandResets = new ToggleObject(
            "Cancel Item Update Hand Resets",
            "Cancel your hand doing the re-equip animation when your item updates (durability, lore...).",
            ConfigCategory.ITEM_RESET,
            "cancelItemUpdateHandResets",
            true
        );
        cancelAllHandResets = new ToggleObject(
            "Cancel All Hand Resets",
            "Always cancel your hand doing the re-equip animation. Not recommended because switching between different items doesn't look smooth.",
            ConfigCategory.ITEM_RESET,
            "cancelAllHandResets",
            false
        );

        cancelOwnBlockAnimations = new ToggleObject(
            "Cancel Own Block Animations",
            "Cancel your own blocking animations in first person.",
            ConfigCategory.OTHER,
            "cancelOwnBlockAnimations",
            false
        );
        cancelThirdPersonBlockAnimations = new ToggleObject(
            "Cancel Third Person Block Animations",
            "Cancel third person blocking animations from you and other players.",
            ConfigCategory.OTHER,
            "cancelThirdPersonBlockAnimations",
            false
        );
        cancelOwnBowAnimations = new ToggleObject(
            "Cancel Own Bow Animations",
            "Cancel your own bow animations in first person. §eArrow models will still be drawn.",
            ConfigCategory.OTHER,
            "cancelOwnBowAnimations",
            false
        );
        cancelThirdPersonBowAnimations = new ToggleObject(
            "Cancel Third Person Bow Animations",
            "Cancel third person bow animations from you and other players. §eArrow models will still be drawn.",
            ConfigCategory.OTHER,
            "cancelThirdPersonBowAnimations",
            false
        );
        cancelEatingAnimations = new ToggleObject(
            "Cancel Eating Animations",
            "Cancel first person eating animations.",
            ConfigCategory.OTHER,
            "cancelEatingAnimations",
            false
        );
        cancelDrinkingAnimations = new ToggleObject(
            "Cancel Drinking Animations",
            "Cancel first person drinking animations.",
            ConfigCategory.OTHER,
            "cancelDrinkingAnimations",
            false
        );
        cancelOwnLimbMovements = new ToggleObject(
            "Cancel Own Limb Movements",
            "Cancel your own limb movements.",
            ConfigCategory.OTHER,
            "cancelOwnLimbMovements",
            false
        );
        cancelOtherLimbMovements = new ToggleObject(
            "Cancel Other Limb Movements",
            "Cancels limb movements from other players. Is quite terrifying when you are being chased.",
            ConfigCategory.OTHER,
            "cancelOtherLimbMovements",
            false
        );

        if (config.hasChanged()) config.save();
    }

    public void save() {
        config.save();
    }
}
