package at.yedel.antimations.gui;



import java.util.Collections;
import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;



public class AntimationsGuiFactory implements IModGuiFactory {
    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return AntimationsGui.class;
    }

    public void initialize(Minecraft minecraft) {}

    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return Collections.emptySet();
    }

    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement runtimeOptionCategoryElement) {
        return null;
    }
}
