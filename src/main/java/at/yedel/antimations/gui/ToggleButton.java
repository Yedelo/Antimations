package at.yedel.antimations.gui;



import at.yedel.antimations.config.ConfigObject;
import at.yedel.antimations.config.ToggleObject;



public class ToggleButton extends HoverableButton {
    private final String settingDescription;
    private final String hoverText;
    private final ToggleObject toggleObject;
    public ToggleButton(int buttonId, int x, int y, int width, String settingDescription, String hoverText, ToggleObject toggleObject) {
        super(buttonId, x, y, width, 20, settingDescription + ": " + toggleObject.getToggleText());
        this.settingDescription = settingDescription;
        this.hoverText = hoverText;
        this.toggleObject = toggleObject;
    }

    public void onClick() {
        setToggle(!getToggleObject().get());
    }

    public void setToggle(boolean newValue) {
        if (newValue) enable();
        else disable();
    }

    public void enable() {
        toggleObject.set(true);
        displayString = settingDescription + ": " + "§aEnabled";
    }

    public void disable() {
        toggleObject.set(false);
        displayString = settingDescription + ": " + "§cDisabled";
    }

    public String getHoverText() {
        return hoverText;
    }

    public ConfigObject<Boolean> getToggleObject() {
        return toggleObject;
    }
}
