package at.yedel.antimations.gui;



import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import at.yedel.antimations.Antimations;
import at.yedel.antimations.config.AntimationsConfig;
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

    private ToggleButton[] toggles;
    private HoverableButton[] hoverables;

    private int buttonId;

    private ToggleButton cancelCreeperIgnitionSwings;
    private ToggleButton cancelFishingRodSwings;
    private ToggleButton cancelBlockHitSwings;
    private ToggleButton cancelAirOrEntitySwings;
    private ToggleButton cancelBlockInteractSwings;
    private ToggleButton cancelItemUseHandResets;
    private ToggleButton cancelItemConsumptionHandResets;
    private ToggleButton cancelItemUpdateHandResets;
    private ToggleButton cancelOwnBlockAnimations;
    private ToggleButton cancelThirdPersonBlockAnimations;
    private ToggleButton cancelOtherPlayerSwings;

    private IconButton modrinthButton;
    private IconButton githubButton;
    @SuppressWarnings("FieldCanBeLocal")
    private IconButton discordButton;

    private final URI modrinthUri = URI.create("https://modrinth.com/project/antimations");
    private final URI githubUri = URI.create("https://github.com/Yedelo/Antimations");

    private GuiButton enableAllButton;
    private GuiButton disableAllButton;
    private GuiButton resetButton;

    private GuiButton doneButton;

    private String versionString;

    @Override
    public void initGui() {
        buttonId = 0;
        midpoint = width / 2;
        addSettingButtons();
        this.buttonList.add(modrinthButton = new IconButton(buttonId ++, midpoint - 12 - 30 , 25, 25, 25, new ResourceLocation("antimations", "modrinth.png"), "Click to open the Modrinth page for this mod."));
        this.buttonList.add(githubButton = new IconButton(buttonId ++, midpoint - 12, 25, 25, 25, new ResourceLocation("antimations", "github.png"), "Click to open the GitHub repository for this mod."));
        this.buttonList.add(discordButton = new IconButton(buttonId ++, midpoint - 12 + 30, 25, 25, 25, new ResourceLocation("antimations", "discord.png"), "Discord: yedel"));
        toggles = new ToggleButton[] {
            cancelCreeperIgnitionSwings,
            cancelFishingRodSwings,
            cancelBlockHitSwings,
            cancelAirOrEntitySwings,
            cancelBlockInteractSwings,
            cancelItemUseHandResets,
            cancelItemConsumptionHandResets,
            cancelItemUpdateHandResets,
            cancelOwnBlockAnimations,
            cancelThirdPersonBlockAnimations,
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
            cancelOwnBlockAnimations,
            cancelThirdPersonBlockAnimations,
            cancelOtherPlayerSwings
        };
        this.buttonList.add(enableAllButton = new GuiButton(buttonId ++, midpoint - 132, height - 50, 85, 20, "§aEnable All"));
        this.buttonList.add(disableAllButton = new GuiButton(buttonId ++, midpoint - 42, height - 50, 85, 20, "§cDisable All"));
        this.buttonList.add(resetButton = new GuiButton(buttonId ++, midpoint + 48, height - 50, 85, 20, "Reset"));
        this.buttonList.add(doneButton = new GuiButton(buttonId ++, midpoint - 75, height - 25, 150, 20, "Done"));
        versionString = "v" + Antimations.version + "-" + RealmsSharedConstants.VERSION_STRING;
    }

    @Override
    public boolean doesGuiPauseGame() {return false;}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        drawCenteredString(fontRendererObj, "Antimations " + Antimations.version + " by Yedel", midpoint, 5, WHITE);
        drawCenteredString(fontRendererObj, "§7Cancels swing animations on players", midpoint, 15, GRAY);
        drawHorizontalLine(midpoint - 122, midpoint + 120, 50, GRAY);
        drawString(fontRendererObj, versionString, 5, height - fontRendererObj.FONT_HEIGHT - 5, DARK_GRAY);
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
        AntimationsConfig.getInstance().save();
    }

    private void addSettingButtons() {
        int buttonX = midpoint - 112;
        buttonList.add(
                cancelCreeperIgnitionSwings = new ToggleButton(
                        buttonId ++,
                        buttonX,
                        55,
                        225,
                        "Cancel Creeper Ignition Swings",
                        "Cancel swing animations when igniting a creeper.",
                        AntimationsConfig.getInstance().cancelCreeperIgnitionSwings
                )
        );
        buttonList.add(
                cancelFishingRodSwings = new ToggleButton(
                        buttonId ++,
                        buttonX,
                        80,
                        225,
                        "Cancel Fishing Rod Swings",
                        "Cancel swing animations when using a fishing rod.",
                        AntimationsConfig.getInstance().cancelFishingRodSwings
                )
        );
        buttonList.add(
                cancelBlockHitSwings = new ToggleButton(
                        buttonId ++,
                        buttonX,
                        105,
                        225,
                        "Cancel Block Hit Swings",
                        "Cancel swing animations when breaking a block.",
                        AntimationsConfig.getInstance().cancelBlockHitSwings
                )
        );
        buttonList.add(
                cancelAirOrEntitySwings = new ToggleButton(
                        buttonId ++,
                        buttonX,
                        130,
                        225,
                        "Cancel Air or Entity Swings",
                        "Cancel swing animations when swinging at the air or at an entity.",
                        AntimationsConfig.getInstance().cancelAirOrEntitySwings
                )
        );
        buttonList.add(
                cancelBlockInteractSwings = new ToggleButton(
                        buttonId ++,
                        buttonX,
                        155,
                        225,
                        "Cancel Block Interact Swings",
                        "Cancel swing animations when placing or interacting with a block.",
                        AntimationsConfig.getInstance().cancelBlockInteractSwings
                )
        );
        buttonList.add(
                cancelItemUseHandResets = new ToggleButton(
                        buttonId ++,
                        buttonX,
                        180,
                        225,
                        "Cancel Item Use Hand Resets",
                        "Cancel hand position resetting when using items such as eggs or ender pearls.",
                        AntimationsConfig.getInstance().cancelItemUseHandResets
                )
        );
        buttonList.add(
                cancelItemConsumptionHandResets = new ToggleButton(
                        buttonId ++,
                        buttonX,
                        205,
                        225,
                        "Cancel Item Consumption Resets",
                        "Cancel hand position resetting when using items such as eggs or ender pearls, \nwhen the stack amount changes.",
                        AntimationsConfig.getInstance().cancelItemConsumptionHandResets
                )
        );
        buttonList.add(
                cancelItemUpdateHandResets = new ToggleButton(
                        buttonId ++,
                        buttonX,
                        230,
                        225,
                        "Cancel Item Update Resets",
                        "Cancel hand position resetting from the item updating. \nWith this, the reequipping animation will only happen if the items themselves are different.",
                        AntimationsConfig.getInstance().cancelItemUpdateHandResets
                )
        );
        buttonList.add(
                cancelOwnBlockAnimations = new ToggleButton(
                        buttonId ++,
                        buttonX,
                        255,
                        225,
                        "Cancel Own Block Animations",
                        "Cancel your own block animations in first person",
                        AntimationsConfig.getInstance().cancelOwnBlockAnimations
                )
        );
        buttonList.add(
                cancelThirdPersonBlockAnimations = new ToggleButton(
                        buttonId ++,
                        buttonX + 230,
                        255,
                        225,
                        "Cancel Third Person Block Animations",
                        "Cancel third person blocking animations from you and other players.",
                        AntimationsConfig.getInstance().cancelThirdPersonBlockAnimations
                )
        );
        buttonList.add(
                cancelOtherPlayerSwings = new ToggleButton(
                        buttonId ++,
                        buttonX,
                        280,
                        225,
                        "Cancel Other Player's Swings",
                        "Cancel all swing animations from other players.",
                        AntimationsConfig.getInstance().cancelOtherPlayerSwings
                )
        );
    }
}
