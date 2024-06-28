package at.yedel.antimations.config;



import java.io.File;

import net.minecraftforge.common.config.Configuration;



public class AntimationsConfig {
    public static AntimationsConfig instance = new AntimationsConfig();
    private Configuration config;

    public ToggleObject cancelCreeperIgnitionSwings;
    public ToggleObject cancelFishingRodSwings;
    public ToggleObject cancelBlockHitSwings; // might be confusing, not blockhitting but when you hit a block
    public ToggleObject cancelAirOrEntitySwings;
    public ToggleObject cancelBlockInteractSwings;
    public ToggleObject cancelItemUseHandResets;
    public ToggleObject cancelItemConsumptionHandResets;
    public ToggleObject cancelItemUpdateHandResets;
    public ToggleObject cancelOtherPlayerSwings;

    public void setupConfig(File recommendedConfigFile) {
        config = new Configuration(recommendedConfigFile);
        config.load();
        cancelCreeperIgnitionSwings = new ToggleObject(config, getValue("cancelCreeperIgnitionSwings", true), "cancelCreeperIgnitionSwings", true);
        cancelFishingRodSwings = new ToggleObject(config, getValue("cancelFishingRodSwings", true), "cancelFishingRodSwings", true);
        cancelBlockHitSwings = new ToggleObject(config, getValue("cancelBlockHitSwings", true), "cancelBlockHitSwings", true);
        cancelAirOrEntitySwings = new ToggleObject(config, getValue("cancelAirOrEntitySwings", true), "cancelAirOrEntitySwings", true);
        cancelBlockInteractSwings = new ToggleObject(config, getValue("cancelBlockInteractSwings", true), "cancelBlockInteractSwings", true);
        cancelItemUseHandResets = new ToggleObject(config, getValue("cancelItemUseHandResets", true), "cancelItemUseHandResets", true);
        cancelItemConsumptionHandResets = new ToggleObject(config, getValue("cancelItemConsumptionHandResets", true), "cancelItemConsumptionHandResets", true);
        cancelItemUpdateHandResets = new ToggleObject(config, getValue("cancelItemUpdateHandResets", true), "cancelItemUpdateHandResets", true);
        cancelOtherPlayerSwings = new ToggleObject(config, getValue("cancelOtherPlayerSwings", false), "cancelOtherPlayerSwings", false);
        if (config.hasChanged()) config.save();
    }

    public void save() {config.save();}

    // i didn't want to write Configuration.CATEGORY_GENERAL everytime
    private boolean getValue(String variableName, boolean defaultValue) {
        return config.get(Configuration.CATEGORY_GENERAL, variableName, defaultValue).getBoolean();
    }
}
