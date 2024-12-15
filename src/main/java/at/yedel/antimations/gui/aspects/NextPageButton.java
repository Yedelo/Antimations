package at.yedel.antimations.gui.aspects;



import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;



public class NextPageButton extends GuiButton {
    private final ResourceLocation serverSelectionButtons = new ResourceLocation("textures/gui/server_selection.png");

    public NextPageButton(int buttonId, int x, int y) {
        super(buttonId, x, y, 14, 22, "");
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        hovered = mouseX >= xPosition && mouseY >= yPosition && mouseX <= xPosition + width && mouseY <= yPosition + height;
        mc.getTextureManager().bindTexture(serverSelectionButtons);
        if (hovered) {
            Gui.drawModalRectWithCustomSizedTexture(xPosition, yPosition, 16F, 37F, 14, 22, 256F, 256F);
        }
        else {
            Gui.drawModalRectWithCustomSizedTexture(xPosition, yPosition, 16F, 5F, 14, 22, 256F, 256F);
        }
    }
}
