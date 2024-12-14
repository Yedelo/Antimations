package at.yedel.antimations.config;



public abstract class ConfigObject<T> {
    private T variable;
    protected String variableName;
    protected T defaultValue;
    public ConfigObject(T variable, String variableName, T defaultValue) {
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
