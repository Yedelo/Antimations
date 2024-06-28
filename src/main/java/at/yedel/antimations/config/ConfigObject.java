package at.yedel.antimations.config;



import net.minecraftforge.common.config.Configuration;



public class ConfigObject<T> {
    private final Configuration config;
    private T variable;
    private final String variableName;
    private final T defaultValue;
    public ConfigObject(Configuration config, T variable, String variableName, T defaultValue) {
        this.config = config;
        this.variable = variable;
        this.variableName = variableName;
        this.defaultValue = defaultValue;
    }

    public T get() {
        return variable;
    }

    public void set(T newValue) {
        variable = newValue;
        if (defaultValue instanceof Boolean && newValue instanceof Boolean) {
            config.get(Configuration.CATEGORY_GENERAL, variableName, (Boolean) defaultValue).set((Boolean) newValue);
        }
    }

    public T getDefaultValue() {
        return defaultValue;
    }
}
