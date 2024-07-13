package at.yedel.antimations.gui.aspects;



import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;



public class IconAspect extends GuiButton implements HoverableAspect {
    private ResourceLocation iconLocation;
    private String hoverText;

    public IconAspect(int buttonId, int x, int y, int width, int height, ResourceLocation iconLocation, String hoverText) {
        super(buttonId, x, y, width, height, "");
        this.iconLocation = iconLocation;
        this.hoverText = hoverText;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(iconLocation);
        GlStateManager.color(1F, 1F, 1F, 1F);
        drawModalRectWithCustomSizedTexture(xPosition, yPosition, 0, 0, width, height, width, height);
        hovered = mouseX >= xPosition && mouseY >= yPosition && mouseX <= xPosition + width && mouseY <= yPosition + height;
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
