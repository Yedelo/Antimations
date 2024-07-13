package at.yedel.antimations.gui.aspects;

public interface ConfigAspect {
    void render(int mouseX, int mouseY);
    boolean onClick(int mouseX, int mouseY, int mouseButton);
    void onReset();
}
