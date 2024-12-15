package at.yedel.antimations.gui.aspects;



import at.yedel.antimations.config.ConfigCategory;
import at.yedel.antimations.config.ToggleObject;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

import static at.yedel.antimations.Antimations.minecraft;



public class ToggleAspect extends GuiButton implements ConfigAspect, HoverableAspect {
    private final ToggleObject toggleObject;
    private final String settingName;
    private final String hoverText;

    public ToggleAspect(ToggleObject toggleObject, int buttonId, int x, int y, int width) {
        super(buttonId, x, y, width, 20, toggleObject.getName() + toggleObject.getToggleText());
        this.toggleObject = toggleObject;
        this.settingName = toggleObject.getName();
        this.hoverText = toggleObject.getDescription();
    }

    @Override
    public ConfigCategory getConfigCategory() {
        return toggleObject.getConfigCategory();
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
        return settingName + toggleObject.getToggleText();
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
