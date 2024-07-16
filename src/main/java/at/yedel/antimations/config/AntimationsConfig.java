package at.yedel.antimations.config;



import java.io.File;

import net.minecraftforge.common.config.Configuration;



public class AntimationsConfig {
    private static AntimationsConfig instance = new AntimationsConfig();
    
    public static AntimationsConfig getInstance() {
        return instance;
    }
    
    private Configuration config;

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

    public void setupConfig(File recommendedConfigFile) {
        config = new Configuration(recommendedConfigFile);
        config.load();

        cancelCreeperIgnitionSwings = new ToggleObject(config, getValue("cancelCreeperIgnitionSwings", true), "cancelCreeperIgnitionSwings", true);
        cancelFishingRodSwings = new ToggleObject(config, getValue("cancelFishingRodSwings", true), "cancelFishingRodSwings", true);
        cancelBlockHitSwings = new ToggleObject(config, getValue("cancelBlockHitSwings", true), "cancelBlockHitSwings", true);
        cancelAirOrEntitySwings = new ToggleObject(config, getValue("cancelAirOrEntitySwings", true), "cancelAirOrEntitySwings", true);
        cancelBlockInteractSwings = new ToggleObject(config, getValue("cancelBlockInteractSwings", true), "cancelBlockInteractSwings", true);
        cancelOtherPlayerSwings = new ToggleObject(config, getValue("cancelOtherPlayerSwings", false), "cancelOtherPlayerSwings", false);

        cancelItemUseHandResets = new ToggleObject(config, getValue("cancelItemUseHandResets", true), "cancelItemUseHandResets", true);
        cancelItemUpdateHandResets = new ToggleObject(config, getValue("cancelItemUpdateHandResets", true), "cancelItemUpdateHandResets", true);
        cancelAllHandResets = new ToggleObject(config, getValue("cancelAllHandResets", false), "cancelAllHandResets", false);

        cancelOwnBlockAnimations = new ToggleObject(config, getValue("cancelOwnBlockAnimations", false), "cancelOwnBlockAnimations", false);
        cancelThirdPersonBlockAnimations = new ToggleObject(config, getValue("cancelThirdPersonBlockAnimations", false), "cancelThirdPersonBlockAnimations", false);
        cancelOwnBowAnimations = new ToggleObject(config, getValue("cancelOwnBowAnimations", false), "cancelOwnBowAnimations", false);
        cancelThirdPersonBowAnimations = new ToggleObject(config, getValue("cancelThirdPersonBowAnimations", false), "cancelThirdPersonBowAnimations", false);
        cancelEatingAnimations = new ToggleObject(config, getValue("cancelEatingAnimations", false), "cancelEatingAnimations", false);
        cancelDrinkingAnimations = new ToggleObject(config, getValue("cancelDrinkingAnimations", false), "cancelDrinkingAnimations", false);

        if (config.hasChanged()) config.save();
    }

    public void save() {config.save();}

    // i didn't want to write Configuration.CATEGORY_GENERAL everytime
    private boolean getValue(String variableName, boolean defaultValue) {
        return config.get(Configuration.CATEGORY_GENERAL, variableName, defaultValue).getBoolean();
    }
}
