package at.yedel.antimations.config;



import net.minecraftforge.common.config.Configuration;



public class ToggleObject extends ConfigObject<Boolean> {
    public ToggleObject(String variableName, Boolean defaultValue) {
        super(getValue(variableName, defaultValue), variableName, defaultValue);
    }

    public void toggle() {
        if (get()) disable();
        else enable();
    }

    public void disable() {
        set(false);
    }

    public void enable() {
        set(true);
    }

    public void reset() {
        set(defaultValue);
    }

    @Override
    public void save(Boolean newValue) {
        AntimationsConfig.getInstance().config.get(Configuration.CATEGORY_GENERAL, variableName, defaultValue).set(newValue);
    }

    public String getToggleText() {
        if (get()) return ": §aEnabled";
        else return ": §cDisabled";
    }

    private static boolean getValue(String variableName, boolean defaultValue) {
        return AntimationsConfig.getInstance().config.get(Configuration.CATEGORY_GENERAL, variableName, defaultValue).getBoolean();
    }
}
