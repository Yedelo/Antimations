package at.yedel.antimations.gui;



import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import at.yedel.antimations.Antimations;
import at.yedel.antimations.config.AntimationsConfig;
import at.yedel.antimations.utils.Colorful;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;



public class AntimationsGui extends GuiScreen implements Colorful {
    private final GuiScreen parentScreen;

    public AntimationsGui(GuiScreen parentScreen) {
        this.parentScreen = parentScreen;
    }

    private int midpoint;

    private ToggleButton[] toggles;
    private HoverableButton[] hoverables;

    private ToggleButton cancelCreeperIgnitionSwings;
    private ToggleButton cancelFishingRodSwings;
    private ToggleButton cancelBlockHitSwings;
    private ToggleButton cancelAirOrEntitySwings;
    private ToggleButton cancelBlockInteractSwings;
    private ToggleButton cancelItemUseHandResets;
    private ToggleButton cancelItemConsumptionHandResets;
    private ToggleButton cancelItemUpdateHandResets;
    private ToggleButton cancelOtherPlayerSwings;

    private IconButton modrinthButton;
    private IconButton githubButton;
    private IconButton discordButton;

    private final URI modrinthUri = URI.create("https://modrinth.com/project/antimations");
    private final URI githubUri = URI.create("https://github.com/Yedelo/AntimationsMod");

    private GuiButton enableAllButton;
    private GuiButton disableAllButton;
    private GuiButton resetButton;

    private GuiButton doneButton;

    @Override
    public void initGui() {
        midpoint = width / 2;
        addSettingButtons();
        this.buttonList.add(modrinthButton = new IconButton(9, midpoint - 12 - 30 , 25, 25, 25, new ResourceLocation("antimations", "modrinth.png"), "Click to open the Modrinth page for this mod."));
        this.buttonList.add(githubButton = new IconButton(10, midpoint - 12, 25, 25, 25, new ResourceLocation("antimations", "github.png"), "Click to open the GitHub repository for this mod."));
        this.buttonList.add(discordButton = new IconButton(11, midpoint - 12 + 30, 25, 25, 25, new ResourceLocation("antimations", "discord.png"), "Discord: yedel"));
        toggles = new ToggleButton[] {
            cancelCreeperIgnitionSwings,
            cancelFishingRodSwings,
            cancelBlockHitSwings,
            cancelAirOrEntitySwings,
            cancelBlockInteractSwings,
            cancelItemUseHandResets,
            cancelItemConsumptionHandResets,
            cancelItemUpdateHandResets,
            cancelOtherPlayerSwings
        };
        hoverables = new HoverableButton[] {
            modrinthButton,
            githubButton,
            discordButton,

            cancelCreeperIgnitionSwings,
            cancelFishingRodSwings,
            cancelBlockHitSwings,
            cancelAirOrEntitySwings,
            cancelBlockInteractSwings,
            cancelItemUseHandResets,
            cancelItemConsumptionHandResets,
            cancelItemUpdateHandResets,
            cancelOtherPlayerSwings
        };
        this.buttonList.add(enableAllButton = new GuiButton(13, midpoint - 132, height - 50, 85, 20, "§aEnable All"));
        this.buttonList.add(disableAllButton = new GuiButton(14, midpoint - 42, height - 50, 85, 20, "§cDisable All"));
        this.buttonList.add(resetButton = new GuiButton(15, midpoint + 48, height - 50, 85, 20, "Reset"));
        this.buttonList.add(doneButton = new GuiButton(16, midpoint - 75, height - 25, 150, 20, "Done"));
    }

    @Override
    public boolean doesGuiPauseGame() {return false;}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        drawCenteredString(fontRendererObj, "Antimations " + Antimations.version + " by Yedel", midpoint, 5, WHITE);
        drawCenteredString(fontRendererObj, "§7Cancels swing animations on players", midpoint, 15, GRAY);
        drawHorizontalLine(midpoint - 122, midpoint + 120, 50, GRAY);
        for (GuiButton button: buttonList) {
            button.drawButton(mc, mouseX, mouseY);
        }
        for (HoverableButton hoverable: hoverables) {
            if (hoverable.isMouseOver()) {
                drawCreativeTabHoveringText(hoverable.getHoverText(), mouseX, mouseY);
            }
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button instanceof ToggleButton) {
            ((ToggleButton) button).onClick();
        }
        else if (button == modrinthButton) {
            Desktop.getDesktop().browse(modrinthUri);
        }
        else if (button == githubButton) {
            Desktop.getDesktop().browse(githubUri);
        }
        else if (button == enableAllButton) {
            for (ToggleButton toggle: toggles) {
                toggle.enable();
            }
        }
        else if (button == disableAllButton) {
            for (ToggleButton toggle: toggles) {
                toggle.disable();
            }
        }
        else if (button == resetButton) {
            for (ToggleButton toggle: toggles) {
                toggle.setToggle(toggle.getToggleObject().getDefaultValue());
            }
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
        AntimationsConfig.instance.save();
    }

    private void addSettingButtons() {
        int buttonX = midpoint - 112;
        buttonList.add(
                cancelCreeperIgnitionSwings = new ToggleButton(
                        0,
                        buttonX,
                        55,
                        225,
                        "Cancel Creeper Ignition Swings",
                        "Cancel swing animations when igniting a creeper.",
                        AntimationsConfig.instance.cancelCreeperIgnitionSwings
                )
        );
        buttonList.add(
                cancelFishingRodSwings = new ToggleButton(
                        1,
                        buttonX,
                        80,
                        225,
                        "Cancel Fishing Rod Swings",
                        "Cancel swing animations when using a fishing rod.",
                        AntimationsConfig.instance.cancelFishingRodSwings
                )
        );
        buttonList.add(
                cancelBlockHitSwings = new ToggleButton(
                        2,
                        buttonX,
                        105,
                        225,
                        "Cancel Block Hit Swings",
                        "Cancel swing animations when breaking a block.",
                        AntimationsConfig.instance.cancelBlockHitSwings
                )
        );
        buttonList.add(
                cancelAirOrEntitySwings = new ToggleButton(
                        3,
                        buttonX,
                        130,
                        225,
                        "Cancel Air or Entity Swings",
                        "Cancel swing animations when swinging at the air or at an entity.",
                        AntimationsConfig.instance.cancelAirOrEntitySwings
                )
        );
        buttonList.add(
                cancelBlockInteractSwings = new ToggleButton(
                        4,
                        buttonX,
                        155,
                        225,
                        "Cancel Block Interact Swings",
                        "Cancel swing animations when placing or interacting with a block.",
                        AntimationsConfig.instance.cancelBlockInteractSwings
                )
        );
        buttonList.add(
                cancelItemUseHandResets = new ToggleButton(
                        5,
                        buttonX,
                        180,
                        225,
                        "Cancel Item Use Hand Resets",
                        "Cancel hand position resetting when using items such as eggs or ender pearls.",
                        AntimationsConfig.instance.cancelItemUseHandResets
                )
        );
        buttonList.add(
                cancelItemConsumptionHandResets = new ToggleButton(
                        6,
                        buttonX,
                        205,
                        225,
                        "Cancel Item Consumption Resets",
                        "Cancel hand position resetting when using items such as eggs or ender pearls, \nwhen the stack amount changes.",
                        AntimationsConfig.instance.cancelItemConsumptionHandResets
                )
        );
        buttonList.add(
                cancelItemUpdateHandResets = new ToggleButton(
                        7,
                        buttonX,
                        230,
                        225,
                        "Cancel Item Update Resets",
                        "Cancel hand position resetting from the item updating. \nWith this, the reequipping animation will only happen if the items themselves are different.",
                        AntimationsConfig.instance.cancelItemUpdateHandResets
                )
        );
        buttonList.add(
                cancelOtherPlayerSwings = new ToggleButton(
                        8,
                        buttonX,
                        255,
                        225,
                        "Cancel Other Player's Swings",
                        "Cancel all swing animations from other players.",
                        AntimationsConfig.instance.cancelOtherPlayerSwings
                )
        );
    }
}
