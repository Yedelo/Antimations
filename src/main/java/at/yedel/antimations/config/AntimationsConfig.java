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
        name = "Cancel Air Swings",
        description = "Cancel swinging your hand at the air.",
        category = "Swing Customization",
        size = 2
    )
    public boolean cancelAirSwings = false;

    @Switch(
        name = "Cancel Entity Hit Swings",
        description = "Cancel swinging your hand when hitting an entity.",
        category = "Swing Customization"
    )
    public boolean cancelEntityHitSwings = false;

    @Switch(
        name = "Cancel Only Hurt Entity Swings",
        description = "Only cancel swinging your hand when hitting hurt entities. Combine this with Cancel Air Swings to make yourself look like a pro timer. Note that some swings may be cancelled because of server delay.",
        category = "Swing Customization"
    )
    public boolean cancelOnlyHurtEntitySwings = false;

    @Slider(
        name = "Hurt Time Adjustment",
        description = "Control how long, in ticks, an entity is considered hurt for. Setting this to 10 means that swings will be cancelled for the entire hurt time, while setting this to 1 means that swings will be cancelled for 1 tick after the hurt time.",
        category = "Swing Customization",
        min = 1,
        max = 10
    )
    public int hurtTimeAdjustment = 10;

    @Switch(
        name = "Cancel Block Hit Swings",
        description = "Cancel swinging your hand when hitting a block.",
        category = "Swing Customization"
    )
    public boolean cancelBlockHitSwings = false;

    @Switch(
        name = "Cancel Block Interact Swings",
        description = "Cancel swinging your hand when interacting with blocks.",
        category = "Swing Customization"
    )
    public boolean cancelBlockInteractSwings = false;

    @Switch(
        name = "Cancel Fishing Rod Swings",
        description = "Cancel swinging your hand when using a fishing rod.",
        category = "Swing Customization"
    )
    public boolean cancelFishingRodSwings = false;

    @Switch(
        name = "Cancel Creeper Ignition Swings",
        description = "Cancel swinging your hand when igniting a creeper.",
        category = "Swing Customization"
    )
    public boolean cancelCreeperIgnitionSwings = false;

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
    public boolean cancelItemUseHandResets = false;

    @Switch(
        name = "Cancel Item Update Hand Resets",
        description = "Cancel your hand doing the re-equip animation when your item updates (durability, lore...).",
        category = "Item Reset"
    )
    public boolean cancelItemUpdateHandResets = false;

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
