package at.yedel.antimations.gui.aspects;



import at.yedel.antimations.config.ToggleObject;
import net.minecraft.client.gui.GuiButton;



public class ToggleAspect extends GuiButton implements ConfigAspect, HoverableAspect {
    private final ToggleObject toggleObject;
    private final String settingDescription;
    private final String hoverText;

    public ToggleAspect(int buttonId, int x, int y, ToggleObject toggleObject, String settingDescription, String hoverText) {
        super(buttonId, x, y, settingDescription + toggleObject.getToggleText());
        this.toggleObject = toggleObject;
        this.settingDescription = settingDescription;
        this.hoverText = hoverText;
    }

    public void onClick() {
        toggleObject.toggle();
        displayString = getNewDisplayString();
    }

    public void onReset() {
        toggleObject.reset();
        displayString = getNewDisplayString();
    }

    private String getNewDisplayString() {
        return settingDescription + toggleObject.getToggleText();
    }

    @Override
    public String getHoverText() {
        return hoverText;
    }

    @Override
    public boolean isHovered() {
        return hovered;
    }
}
