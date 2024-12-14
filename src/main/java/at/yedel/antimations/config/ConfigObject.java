package at.yedel.antimations.config;



public abstract class ConfigObject<T> {
    private final String name;
    private final String description;
    private T variable;
    private final ConfigCategory configCategory;
    protected final String variableName;
    protected final T defaultValue;
    public ConfigObject(String name, String description, T variable, ConfigCategory configCategory, String variableName, T defaultValue) {
        this.name = name;
        this.description = description;
        this.variable = variable;
        this.configCategory = configCategory;
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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ConfigCategory getConfigCategory() {
        return configCategory;
    }

    abstract void save(T newValue);
}
