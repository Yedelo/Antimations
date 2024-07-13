package at.yedel.antimations.gui;



import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import at.yedel.antimations.Antimations;
import at.yedel.antimations.config.AntimationsConfig;
import at.yedel.antimations.gui.aspects.ConfigAspect;
import at.yedel.antimations.gui.aspects.HoverableAspect;
import at.yedel.antimations.gui.aspects.IconAspect;
import at.yedel.antimations.gui.aspects.NextPageButton;
import at.yedel.antimations.gui.aspects.PreviousPageButton;
import at.yedel.antimations.gui.aspects.ToggleAspect;
import at.yedel.antimations.utils.Colorful;
import at.yedel.antimations.utils.FlowArrayList;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;



public class AntimationsGui extends GuiScreen implements Colorful {
	private GuiScreen parentScreen;
	private URI modrinthUri = URI.create("https://modrinth.com/project/antimations");
	private URI githubUri = URI.create("https://github.com/Yedelo/Antimations");
	private int midpoint;
	private int buttonId;
	private ArrayList<HoverableAspect> hoverableAspects;
	private IconAspect modrinthButton;
	private IconAspect githubButton;
	private PreviousPageButton previousPageButton;
	private NextPageButton nextPageButton;
	private List<ConfigAspect> swingCustomizationAspects = new ArrayList<>();
	private List<ConfigAspect> itemResetAspects = new ArrayList<>();
	private List<ConfigAspect> otherAspects = new ArrayList<>();
	private List<ConfigAspect> configAspects = new ArrayList<>();
	private ConfigPage swingCustomizationPage = new ConfigPage("Swing Customization", swingCustomizationAspects);
	private ConfigPage itemResetPage = new ConfigPage("Item Reset Customizaion", itemResetAspects);
	private ConfigPage otherPage = new ConfigPage("Other", otherAspects);
	private FlowArrayList<ConfigPage> configPages = new FlowArrayList<>();
	private ConfigPage currentConfigPage;
	private GuiButton resetButton;
	private GuiButton doneButton;
	private GuiButton openConfigFileButton;

	public AntimationsGui(GuiScreen parentScreen) {
		this.parentScreen = parentScreen;
	}

	@Override
	public void initGui() {
		buttonId = 0;
		midpoint = width / 2;
		IconAspect discordButton;

		buttonList.add(modrinthButton = new IconAspect(buttonId ++, midpoint - 12 - 30, 15, 25, 25, new ResourceLocation("antimations", "modrinth.png"), "Click to open the Modrinth page for this mod."));
		buttonList.add(githubButton = new IconAspect(buttonId ++, midpoint - 12, 15, 25, 25, new ResourceLocation("antimations", "github.png"), "Click to open the GitHub repository for this mod."));
		buttonList.add(discordButton = new IconAspect(buttonId ++, midpoint - 12 + 30, 15, 25, 25, new ResourceLocation("antimations", "discord.png"), "Discord: yedel"));
		buttonList.add(previousPageButton = new PreviousPageButton(buttonId ++, midpoint - 100, height - 39));
		buttonList.add(nextPageButton = new NextPageButton(buttonId ++, midpoint + 85, height - 39));

		setupConfigAspects();
		setupConfigPages();

		hoverableAspects = new ArrayList<>();
		hoverableAspects.add(modrinthButton);
		hoverableAspects.add(githubButton);
		hoverableAspects.add(discordButton);

		for (ConfigAspect configAspect: configAspects) {
			if (configAspect instanceof HoverableAspect) {
				hoverableAspects.add((HoverableAspect) configAspect);
			}
		}

		buttonList.add(resetButton = new GuiButton(buttonId ++, midpoint - 42, height - 50, 85, 20, "Reset"));
		buttonList.add(doneButton = new GuiButton(buttonId ++, midpoint - 75, height - 25, 150, 20, "Done"));
		buttonList.add(openConfigFileButton = new GuiButton(buttonId ++, width - 105, height - 25, 100, 20, "Open Config File"));

		currentConfigPage = configPages.get(0);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		drawCenteredString(fontRendererObj, "Antimations " + Antimations.version + " by Yedel", midpoint, 5, WHITE);
		drawHorizontalLine(80, width - 80, 45, GRAY);
		drawCenteredString(fontRendererObj, currentConfigPage.getTitle(), midpoint, 50, WHITE);
		drawString(fontRendererObj, Antimations.totalVersionString, 5, height - fontRendererObj.FONT_HEIGHT - 5, GRAY);
		for (GuiButton button: buttonList) {
			button.drawButton(mc, mouseX, mouseY);
		}
		currentConfigPage.drawScreen(mouseX, mouseY);
		for (HoverableAspect hoverableAspect: hoverableAspects) {
			if (hoverableAspect.isHovered()) {
				drawCreativeTabHoveringText(hoverableAspect.getHoverText(), mouseX, mouseY);
			}
		}
		drawString(fontRendererObj, "(" + mouseX + ", " + mouseY + ")", mouseX, mouseY, WHITE);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button == modrinthButton) {
			Desktop.getDesktop().browse(modrinthUri);
		}
		else if (button == githubButton) {
			Desktop.getDesktop().browse(githubUri);
		}
		else if (button == previousPageButton) {
			goToPreviousPage();
		}
		else if (button == nextPageButton) {
			goToNextPage();
		}
		else if (button == resetButton) {
			if (Objects.equals(button.displayString, "Reset")) {
				button.displayString = "§cAre you sure?";
				return;
			}
			button.displayString = "Reset";
			for (ConfigAspect configAspect: configAspects) {
				configAspect.onReset();
			}
		}
		else if (button == doneButton) {
			mc.displayGuiScreen(parentScreen);
		}
		else if (button == openConfigFileButton) {
			Desktop.getDesktop().browse(Antimations.getInstance().getSuggestedConfigurationURI());
		}
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		currentConfigPage.onClick(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) {
		switch (keyCode) {
			case (Keyboard.KEY_ESCAPE): mc.displayGuiScreen(parentScreen); break;
			case (Keyboard.KEY_LEFT): goToPreviousPage(); break;
			case (Keyboard.KEY_RIGHT): goToNextPage();
		}
	}

	@Override
	public void onGuiClosed() {
		AntimationsConfig.getInstance().save();
	}

	private void goToPreviousPage() {
		currentConfigPage = configPages.getPreviousElement(currentConfigPage);
	}

	private void goToNextPage() {
		currentConfigPage = configPages.getNextElement(currentConfigPage);
	}

	// Stuff is moved into functions at the bottom to make it so that code above this comment doesn't have to be updated for new features

	private void setupConfigAspects() {
		setupSwingCustomizationAspects();
		setupItemResetAspects();
		setupOtherAspects();
		addConfigAspects();
	}

	private void setupSwingCustomizationAspects() {
		swingCustomizationAspects.clear();
		swingCustomizationAspects.add(
			new ToggleAspect(
				buttonId ++,
				midpoint - 112,
				65,
				225,
				AntimationsConfig.getInstance().cancelCreeperIgnitionSwings,
				"Cancel Creeper Ignition Swings",
				"Cancel swinging your hand when igniting a creeper."
			)
		);
		swingCustomizationAspects.add(
			new ToggleAspect(
				buttonId ++,
				midpoint - 112,
				90,
				225,
				AntimationsConfig.getInstance().cancelFishingRodSwings,
				"Cancel Fishing Rod Swings",
				"Cancel swinging your hand when using a fishing rod."
			)
		);
		swingCustomizationAspects.add(
			new ToggleAspect(
				buttonId ++,
				midpoint - 112,
				115,
				225,
				AntimationsConfig.getInstance().cancelBlockHitSwings,
				"Cancel Block Hit Swings",
				"Cancel swinging your hand when hitting a block."
			)
		);
		swingCustomizationAspects.add(
			new ToggleAspect(
				buttonId ++,
				midpoint - 112,
				140,
				225,
				AntimationsConfig.getInstance().cancelAirOrEntitySwings,
				"Cancel Air or Entity Swings",
				"Cancel swinging your hand when swinging at the air or at an entity."
			)
		);
		swingCustomizationAspects.add(
			new ToggleAspect(
				buttonId ++,
				midpoint - 112,
				165,
				225,
				AntimationsConfig.getInstance().cancelBlockInteractSwings,
				"Cancel Block Interact Swings",
				"Cancel swinging your hand when interacting with blocks."
			)
		);
		swingCustomizationAspects.add(
			new ToggleAspect(
				buttonId ++,
				midpoint - 112,
				190,
				225,
				AntimationsConfig.getInstance().cancelOtherPlayerSwings,
				"Cancel Other Player Swings",
				"Cancel swing animations from other players."
			)
		);
	}

	private void setupItemResetAspects() {
		itemResetAspects.clear();
		itemResetAspects.add(
			new ToggleAspect(
				buttonId ++,
				midpoint - 112,
				65,
				225,
				AntimationsConfig.getInstance().cancelItemUseHandResets,
				"Cancel Item Use Hand Resets",
				"Cancel your hand doing the re-equip animation when using an item (without it being consumed)."
			)
		);
		itemResetAspects.add(
			new ToggleAspect(
				buttonId ++,
				midpoint - 125,
				90,
				250,
				AntimationsConfig.getInstance().cancelItemConsumptionHandResets,
				"Cancel Item Consumption Hand Resets",
				"Cancel your hand doing the re-equip animation when using an item (being consumed)."
			)
		);
		itemResetAspects.add(
			new ToggleAspect(
				buttonId ++,
				midpoint - 112,
				115,
				225,
				AntimationsConfig.getInstance().cancelItemUpdateHandResets,
				"Cancel Item Update Hand Resets",
				"Cancel your hand doing the re-equip animation when your item updates (durability, lore...)."
			)
		);
		itemResetAspects.add(
			new ToggleAspect(
				buttonId ++,
				midpoint - 112,
				140,
				225,
				AntimationsConfig.getInstance().cancelAllHandResets,
				"Cancel All Hand Resets",
				"Always cancel your hand doing the re-equip animation."
			)
		);
	}

	private void setupOtherAspects() {
		otherAspects.clear();
		otherAspects.add(
			new ToggleAspect(
				buttonId ++,
				midpoint - 112,
				65,
				225,
				AntimationsConfig.getInstance().cancelOwnBlockAnimations,
				"Cancel Own Block Animations",
				"Cancel your own blocking animations in first person."
			)
		);
		otherAspects.add(
			new ToggleAspect(
				buttonId ++,
				midpoint - 125,
				90,
				250,
				AntimationsConfig.getInstance().cancelThirdPersonBlockAnimations,
				"Cancel Third Person Block Animations",
				"Cancel third person blocking animations from you and other players"
			)
		);
	}

	private void addConfigAspects() {
		configAspects.addAll(swingCustomizationAspects);
		configAspects.addAll(itemResetAspects);
		configAspects.addAll(otherAspects);
	}

	private void setupConfigPages() {
		configPages.add(swingCustomizationPage);
		configPages.add(itemResetPage);
		configPages.add(otherPage);
	}
}
