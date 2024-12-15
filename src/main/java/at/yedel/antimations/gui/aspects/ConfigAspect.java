package at.yedel.antimations.gui.aspects;



import at.yedel.antimations.config.ConfigCategory;



public interface ConfigAspect {
    ConfigCategory getConfigCategory();
    void render(int mouseX, int mouseY);
    boolean onClick(int mouseX, int mouseY, int mouseButton);
    void onReset();
}
