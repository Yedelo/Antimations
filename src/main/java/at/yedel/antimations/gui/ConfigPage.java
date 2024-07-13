package at.yedel.antimations.gui;



import java.util.List;

import at.yedel.antimations.gui.aspects.ConfigAspect;



public class ConfigPage {
    private String title;

    public String getTitle() {
        return title;
    }

    private List<ConfigAspect> configAspects;

    public ConfigPage(String title, List<ConfigAspect> configAspects) {
        this.title = title;
        this.configAspects = configAspects;
    }

    public void drawScreen(int mouseX, int mouseY) {
        for (ConfigAspect configAspect: configAspects) {
            configAspect.render(mouseX, mouseY);
        }
    }

    public void onClick(int mouseX, int mouseY, int mouseButton) {
        for (ConfigAspect configAspect: configAspects) {
            configAspect.onClick(mouseX, mouseY, mouseButton);
        }
    }
 }
