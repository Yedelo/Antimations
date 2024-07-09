package at.yedel.antimations.gui;



import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import at.yedel.antimations.Antimations;
import at.yedel.antimations.config.AntimationsConfig;
import at.yedel.antimations.gui.aspects.ConfigAspect;
import at.yedel.antimations.gui.aspects.HoverableAspect;
import at.yedel.antimations.gui.aspects.IconAspect;
import at.yedel.antimations.utils.Colorful;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.realms.RealmsSharedConstants;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;



public class AntimationsGui extends GuiScreen implements Colorful {
    private final GuiScreen parentScreen;

    public AntimationsGui(GuiScreen parentScreen) {
        this.parentScreen = parentScreen;
    }

    private int midpoint;

    private int buttonId;

    private IconAspect modrinthButton;
    private IconAspect githubButton;



    private List<ConfigAspect> configAspects;

    private HoverableAspect[] hoverableAspects;

    private final URI modrinthUri = URI.create("https://modrinth.com/project/antimations");
    private final URI githubUri = URI.create("https://github.com/Yedelo/Antimations");

    private GuiButton resetButton;

    private GuiButton doneButton;

    private String versionString;

    @Override
    public void initGui() {
        buttonId = 0;
        midpoint = width / 2;
        buttonList.add(modrinthButton = new IconAspect(buttonId ++, midpoint - 12 - 30, 25, 25, 25, new ResourceLocation("antimations", "modrinth.png"), "Click to open the Modrinth page for this mod."));
        buttonList.add(githubButton = new IconAspect(buttonId ++, midpoint - 12, 25, 25, 25, new ResourceLocation("antimations", "github.png"), "Click to open the GitHub repository for this mod."));
        buttonList.add(new IconAspect(buttonId ++, midpoint - 12 + 30, 25, 25, 25, new ResourceLocation("antimations", "discord.png"), "Discord: yedel"));
        hoverableAspects = new HoverableAspect[] {

        };
        buttonList.add(resetButton = new GuiButton(buttonId ++, midpoint - 42, height - 50, 85, 20, "Reset"));
        buttonList.add(doneButton = new GuiButton(buttonId ++, midpoint - 75, height - 25, 150, 20, "Done"));
        versionString = "v" + Antimations.version + "-" + RealmsSharedConstants.VERSION_STRING;
    }

    @Override
    public boolean doesGuiPauseGame() {return false;}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawCenteredString(fontRendererObj, "Antimations " + Antimations.version + " by Yedel", midpoint, 5, WHITE);
        drawCenteredString(fontRendererObj, "§7Cancels swing animations on players", midpoint, 15, GRAY);
        drawString(fontRendererObj, versionString, 5, height - fontRendererObj.FONT_HEIGHT - 5, DARK_GRAY);
        for (GuiButton button: buttonList) {
            button.drawButton(mc, mouseX, mouseY);
        }
        for (HoverableAspect hoverableAspect: hoverableAspects) {
            if (hoverableAspect.isHovered()) {
                drawCreativeTabHoveringText(hoverableAspect.getHoverText(), mouseX, mouseY);
            }
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button == modrinthButton) {
            Desktop.getDesktop().browse(modrinthUri);
        }
        else if (button == githubButton) {
            Desktop.getDesktop().browse(githubUri);
        }

        else if (button == resetButton) {

        }
        else if (button == doneButton) {
            mc.displayGuiScreen(parentScreen);
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        if (keyCode == Keyboard.KEY_ESCAPE) mc.displayGuiScreen(parentScreen);
    }

    @Override
    public void onGuiClosed() {
        AntimationsConfig.getInstance().save();
    }

    private void addSettingAspects() {

    }
}
