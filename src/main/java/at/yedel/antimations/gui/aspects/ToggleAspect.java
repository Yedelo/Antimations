package at.yedel.antimations.gui.aspects;



import at.yedel.antimations.config.ToggleObject;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

import static at.yedel.antimations.Antimations.minecraft;



public class ToggleAspect extends GuiButton implements ConfigAspect, HoverableAspect {
    private ToggleObject toggleObject;
    private String settingDescription;
    private String hoverText;

    public ToggleAspect(int buttonId, int x, int y, int width, ToggleObject toggleObject, String settingDescription, String hoverText) {
        this(buttonId, x, y, width, 20, toggleObject, settingDescription, hoverText);
    }

    public ToggleAspect(int buttonId, int x, int y, int width, int height, ToggleObject toggleObject, String settingDescription, String hoverText) {
        super(buttonId, x, y, width, height, settingDescription + toggleObject.getToggleText());
        this.toggleObject = toggleObject;
        this.settingDescription = settingDescription;
        this.hoverText = hoverText;
    }

    public void render(int mouseX, int mouseY) {
        super.drawButton(minecraft, mouseX, mouseY);
    }

    public boolean onClick(int mouseX, int mouseY, int mouseButton) {
        if (hovered) {
            toggleObject.toggle();
            displayString = getNewDisplayString();
            minecraft.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
        }
        return hovered;
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
