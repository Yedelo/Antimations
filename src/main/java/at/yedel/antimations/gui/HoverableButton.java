package at.yedel.antimations.gui;



import net.minecraft.client.gui.GuiButton;



public abstract class HoverableButton extends GuiButton {
    public HoverableButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
    }

    public abstract String getHoverText();
}
