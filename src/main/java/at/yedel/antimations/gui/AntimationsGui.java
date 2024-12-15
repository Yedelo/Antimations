package at.yedel.antimations.gui;



import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import at.yedel.antimations.Antimations;
import at.yedel.antimations.config.AntimationsConfig;
import at.yedel.antimations.config.ConfigCategory;
import at.yedel.antimations.config.ToggleObject;
import at.yedel.antimations.gui.aspects.ConfigAspect;
import at.yedel.antimations.gui.aspects.HoverableAspect;
import at.yedel.antimations.gui.aspects.IconAspect;
import at.yedel.antimations.gui.aspects.NextPageButton;
import at.yedel.antimations.gui.aspects.PreviousPageButton;
import at.yedel.antimations.gui.aspects.ToggleAspect;
import at.yedel.antimations.utils.Colorful;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;



public class AntimationsGui extends GuiScreen implements Colorful {
	private final GuiScreen parentScreen;

	private int midpoint;
	private int currentButtonId;
	private ArrayList<HoverableAspect> hoverableAspects;
	private final List<ConfigAspect> configAspects = new ArrayList<>();
	private final ConfigCategory[] configCategories = ConfigCategory.values();
	private ConfigCategory currentConfigCategory;
	private int currentPageNumber;
	private List<ConfigAspect> currentConfigAspects;

	private final URI modrinthUri = URI.create("https://modrinth.com/project/antimations");
	private final URI githubUri = URI.create("https://github.com/Yedelo/Antimations");
	private IconAspect modrinthButton;
	private IconAspect githubButton;
	private PreviousPageButton previousPageButton;
	private NextPageButton nextPageButton;
	private GuiButton resetButton;
	private GuiButton doneButton;
	private GuiButton openConfigFileButton;

	public AntimationsGui(GuiScreen parentScreen) {
		this.parentScreen = parentScreen;
	}

	@Override
	public void initGui() {
		currentButtonId = 0;
		midpoint = width / 2;
		IconAspect discordButton;

		buttonList.add(modrinthButton = new IconAspect(buttonId(), midpoint - 12 - 30, 15, 25, 25, new ResourceLocation("antimations", "modrinth.png"), "Click to open the Modrinth page for this mod."));
		buttonList.add(githubButton = new IconAspect(buttonId(), midpoint - 12, 15, 25, 25, new ResourceLocation("antimations", "github.png"), "Click to open the GitHub repository for this mod."));
		buttonList.add(discordButton = new IconAspect(buttonId(), midpoint - 12 + 30, 15, 25, 25, new ResourceLocation("antimations", "discord.png"), "Discord: yedel"));
		buttonList.add(previousPageButton = new PreviousPageButton(buttonId(), midpoint - 100, height - 39));
		buttonList.add(nextPageButton = new NextPageButton(buttonId(), midpoint + 85, height - 39));

		setupConfigAspects();

		hoverableAspects = new ArrayList<>();
		hoverableAspects.add(modrinthButton);
		hoverableAspects.add(githubButton);
		hoverableAspects.add(discordButton);

		for (ConfigAspect configAspect: configAspects) {
			if (configAspect instanceof HoverableAspect) {
				hoverableAspects.add((HoverableAspect) configAspect);
			}
		}

		buttonList.add(resetButton = new GuiButton(buttonId(), midpoint - 42, height - 50, 85, 20, "Reset"));
		buttonList.add(doneButton = new GuiButton(buttonId(), midpoint - 75, height - 25, 150, 20, "Done"));
		buttonList.add(openConfigFileButton = new GuiButton(buttonId(), width - 105, height - 25, 100, 20, "Open Config File"));

		currentPageNumber = 1;
		updateConfigCategory();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		drawCenteredString(fontRendererObj, "Antimations " + Antimations.version + " by Yedel", midpoint, 5, WHITE);
		drawHorizontalLine(175, width - 175, 45, GRAY);
		String pageInfoString = currentConfigCategory.getName() + " (Page " + currentPageNumber + "/" + configCategories.length + ")";
		drawCenteredString(fontRendererObj, pageInfoString, midpoint, 50, WHITE);
		drawString(fontRendererObj, Antimations.totalVersionString, 5, height - fontRendererObj.FONT_HEIGHT - 5, GRAY);
		for (GuiButton button: buttonList) {
			button.drawButton(mc, mouseX, mouseY);
		}
		for (ConfigAspect configAspect: currentConfigAspects) {
			configAspect.render(mouseX, mouseY);
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
		else if (button == previousPageButton) {
			currentPageNumber --;
			if (currentPageNumber == 0) currentPageNumber = 3;
			updateConfigCategory();
		}
		else if (button == nextPageButton) {
			currentPageNumber ++;
			if (currentPageNumber > configCategories.length) currentPageNumber = 1;
			updateConfigCategory();
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
		for (ConfigAspect configAspect: currentConfigAspects) {
			configAspect.onClick(mouseX, mouseY, mouseButton);
		}
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) {
		if (keyCode == Keyboard.KEY_ESCAPE) {
			mc.displayGuiScreen(parentScreen);
		}
	}

	@Override
	public void onGuiClosed() {
		AntimationsConfig.getInstance().save();
	}

	private void updateConfigCategory() {
		currentConfigCategory = configCategories[currentPageNumber - 1];
		currentConfigAspects = configAspects.stream().filter(configAspect -> configAspect.getConfigCategory() == currentConfigCategory).collect(Collectors.toList());
	}

	// Stuff is moved into functions at the bottom to make it so that code above this comment doesn't have to be updated for new features

	private void setupConfigAspects() {
		configAspects.clear();
		addToggleAspect(AntimationsConfig.getInstance().cancelCreeperIgnitionSwings, midpoint - 112, 65, 225);
		addToggleAspect(AntimationsConfig.getInstance().cancelFishingRodSwings, midpoint - 112, 90, 225);
		addToggleAspect(AntimationsConfig.getInstance().cancelBlockHitSwings, midpoint - 112, 115, 225);
		addToggleAspect(AntimationsConfig.getInstance().cancelAirOrEntitySwings, midpoint - 112, 140, 225);
		addToggleAspect(AntimationsConfig.getInstance().cancelBlockInteractSwings, midpoint - 112, 165, 225);
		addToggleAspect(AntimationsConfig.getInstance().cancelOtherPlayerSwings, midpoint - 112, 190, 225);

		addToggleAspect(AntimationsConfig.getInstance().cancelItemUseHandResets, midpoint - 112, 65, 225);
		addToggleAspect(AntimationsConfig.getInstance().cancelItemUpdateHandResets, midpoint - 112, 90, 225);
		addToggleAspect(AntimationsConfig.getInstance().cancelAllHandResets, midpoint - 112, 115, 225);

		addToggleAspect(AntimationsConfig.getInstance().cancelOwnBlockAnimations, midpoint - 112, 65, 225);
		addToggleAspect(AntimationsConfig.getInstance().cancelThirdPersonBlockAnimations, midpoint - 125, 90, 250);
		addToggleAspect(AntimationsConfig.getInstance().cancelOwnBowAnimations, midpoint - 112, 115, 225);
		addToggleAspect(AntimationsConfig.getInstance().cancelThirdPersonBowAnimations, midpoint - 125, 140, 250);
		addToggleAspect(AntimationsConfig.getInstance().cancelEatingAnimations, midpoint - 112, 165, 225);
		addToggleAspect(AntimationsConfig.getInstance().cancelDrinkingAnimations, midpoint - 112, 190, 225);
		addToggleAspect(AntimationsConfig.getInstance().cancelOwnLimbMovements, midpoint - 112, 215, 225);
		addToggleAspect(AntimationsConfig.getInstance().cancelOtherLimbMovements, midpoint - 112, 240, 225);
	}

	private void addToggleAspect(ToggleObject toggleObject, int x, int y, int width) {
		configAspects.add(new ToggleAspect(toggleObject, buttonId(), x, y, width));
	}

	private int buttonId() {
		return currentButtonId ++;
	}
}
