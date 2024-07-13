package at.yedel.antimations.config;



import net.minecraftforge.common.config.Configuration;



public abstract class ConfigObject<T> {
    protected Configuration config;
    private T variable;
    protected String variableName;
    protected T defaultValue;
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
        save(newValue);
    }

    abstract void save(T newValue);
}
