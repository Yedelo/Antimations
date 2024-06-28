package at.yedel.antimations.gui;



import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;



public class IconButton extends HoverableButton {
    public final ResourceLocation iconLocation;
    private final String hoverText;

    public IconButton(int buttonId, int x, int y, int width, int height, ResourceLocation iconLocation, String hoverText) {
        super(buttonId, x, y, width, height, "");
        this.iconLocation = iconLocation;
        this.hoverText = hoverText;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(iconLocation);
        // credits sba and biscuit. without this the color of the icons depends on the last options (color codes i think)
        GlStateManager.color(1F, 1F, 1F, 1F);
        drawModalRectWithCustomSizedTexture(xPosition, yPosition, 0, 0, width, height, width, height);
        this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
    }

    @Override
    public String getHoverText() {
        return hoverText;
    }
}
