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

    public void setupConfig(File recommendedConfigFile) {
        config = new Configuration(recommendedConfigFile);
        config.load();

        cancelCreeperIgnitionSwings = new ToggleObject("cancelCreeperIgnitionSwings", true);
        cancelFishingRodSwings = new ToggleObject("cancelFishingRodSwings", true);
        cancelBlockHitSwings = new ToggleObject("cancelBlockHitSwings", true);
        cancelAirOrEntitySwings = new ToggleObject("cancelAirOrEntitySwings", true);
        cancelBlockInteractSwings = new ToggleObject("cancelBlockInteractSwings", true);
        cancelOtherPlayerSwings = new ToggleObject("cancelOtherPlayerSwings", false);

        cancelItemUseHandResets = new ToggleObject("cancelItemUseHandResets", true);
        cancelItemUpdateHandResets = new ToggleObject("cancelItemUpdateHandResets", true);
        cancelAllHandResets = new ToggleObject("cancelAllHandResets", false);

        cancelOwnBlockAnimations = new ToggleObject("cancelOwnBlockAnimations", false);
        cancelThirdPersonBlockAnimations = new ToggleObject("cancelThirdPersonBlockAnimations", false);
        cancelOwnBowAnimations = new ToggleObject("cancelOwnBowAnimations", false);
        cancelThirdPersonBowAnimations = new ToggleObject("cancelThirdPersonBowAnimations", false);
        cancelEatingAnimations = new ToggleObject("cancelEatingAnimations", false);
        cancelDrinkingAnimations = new ToggleObject("cancelDrinkingAnimations", false);

        if (config.hasChanged()) config.save();
    }

    public void save() {config.save();}
}
