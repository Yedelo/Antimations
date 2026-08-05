package at.yedel.antimations.config;



import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.Slider;
import cc.polyfrost.oneconfig.config.annotations.Switch;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;



public class AntimationsConfig extends Config {
    private static final AntimationsConfig INSTANCE = new AntimationsConfig();

    public static AntimationsConfig getInstance() {
        return INSTANCE;
    }

    private AntimationsConfig() {
        super(
            new Mod(
                "Antimations",
                ModType.UTIL_QOL,
                "/assets/antimations/antimations.png"
            ),
            "antimations.json",
            true,
            true
        );
        initialize();
    }

    @Switch(
        name = "Cancel Creeper Ignition Swings",
        description = "Cancel swinging your hand when igniting a creeper.",
        category = "Swing Customization"
    )
    public boolean cancelCreeperIgnitionSwings = true;

    @Switch(
        name = "Cancel Fishing Rod Swings",
        description = "Cancel swinging your hand when using a fishing rod.",
        category = "Swing Customization"
    )
    public boolean cancelFishingRodSwings = true;

    @Switch(
        name = "Cancel Block Hit Swings",
        description = "Cancel swinging your hand when hitting a block.",
        category = "Swing Customization"
    )
    public boolean cancelBlockHitSwings = true;

    @Switch(
        name = "Cancel Air or Entity Swings",
        description = "Cancel swinging your hand when swinging at the air or at an entity.",
        category = "Swing Customization"
    )
    public boolean cancelAirOrEntitySwings = true;

    @Switch(
        name = "Cancel Block Interact Swings",
        description = "Cancel swinging your hand when interacting with blocks.",
        category = "Swing Customization"
    )
    public boolean cancelBlockInteractSwings = true;

    @Switch(
        name = "Cancel Other Player Swings",
        description = "Cancel swing animations from other players.",
        category = "Swing Customization"
    )
    public boolean cancelOtherPlayerSwings = false;

    @Switch(
        name = "Cancel Item Use Hand Resets",
        description = "Cancel your hand doing the re-equip animation when using an item.",
        category = "Item Reset"
    )
    public boolean cancelItemUseHandResets = true;

    @Switch(
        name = "Cancel Item Update Hand Resets",
        description = "Cancel your hand doing the re-equip animation when your item updates (durability, lore...).",
        category = "Item Reset"
    )
    public boolean cancelItemUpdateHandResets = true;

    @Switch(
        name = "Cancel All Hand Resets",
        description = "Always cancel your hand doing the re-equip animation. Not recommended because switching between different items doesn't look smooth.",
        category = "Item Reset"
    )
    public boolean cancelAllHandResets = false;

    @Switch(
        name = "Cancel Own Block Animations",
        description = "Cancel your own blocking animations in first person.",
        category = "Other"
    )
    public boolean cancelOwnBlockAnimations = false;

    @Switch(
        name = "Cancel Third Person Block Animations",
        description = "Cancel third person blocking animations from you and other players.",
        category = "Other"
    )
    public boolean cancelThirdPersonBlockAnimations = false;

    @Switch(
        name = "Cancel Own Bow Animations",
        description = "Cancel your own bow animations in first person. §eArrow models will still be drawn.",
        category = "Other"
    )
    public boolean cancelOwnBowAnimations = false;

    @Switch(
        name = "Cancel Third Person Bow Animations",
        description = "Cancel third person bow animations from you and other players. §eArrow models will still be drawn.",
        category = "Other"
    )
    public boolean cancelThirdPersonBowAnimations = false;

    @Switch(
        name = "Cancel Eating Animations",
        description = "Cancel first person eating animations.",
        category = "Other"
    )
    public boolean cancelEatingAnimations = false;

    @Switch(
        name = "Cancel Drinking Animations",
        description = "Cancel first person drinking animations.",
        category = "Other"
    )
    public boolean cancelDrinkingAnimations = false;

    @Switch(
        name = "Cancel Own Limb Movements",
        description = "Cancel your own limb movements.",
        category = "Other"
    )
    public boolean cancelOwnLimbMovements = false;

    @Switch(
        name = "Cancel Other Limb Movements",
        description = "Cancels limb movements from other players. Is quite terrifying when you are being chased.",
        category = "Other"
    )
    public boolean cancelOtherLimbMovements = false;

    @Slider(
        name = "Scaling Multiplier",
        description = "The multiplier to modify limb movements by.",
        category = "Other",
        min = -2f,
        max = 2f
    )
    public float cancelLimbScalingMultiplier = 0f;

    @Switch(
        name = "Weirder Own Limb Movements",
        description = "Cancel your own limbs from moving after they already started (or are in the \"top\" of their movement).",
        category = "Other"
    )
    public boolean weirderOwnLimbMovements = false;

    @Switch(
        name = "Weirder Other Limb Movements",
        description = "Cancel other player's limbs from moving after they already started (or are in the \"top\" of their movement).",
        category = "Other"
    )
    public boolean weirderOtherLimbMovements = false;

    @Slider(
        name = "Scaling Multiplier",
        description = "The multiplier to modify limb movements by.",
        category = "Other",
        min = -2f,
        max = 2f
    )
    public float weirderLimbScalingMultiplier = 0f;
}
