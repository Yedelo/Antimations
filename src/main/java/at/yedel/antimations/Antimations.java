package at.yedel.antimations;



import at.yedel.antimations.config.AntimationsConfig;
import at.yedel.antimations.features.AntimationsCommand;
import org.polyfrost.oneconfig.api.commands.v1.CommandManager;
import net.fabricmc.api.ClientModInitializer;
import net.ornithemc.osl.lifecycle.api.client.MinecraftClientEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;



public class Antimations implements ClientModInitializer {
    private static Antimations INSTANCE;

    public static Antimations getInstance() {
        return INSTANCE;
    }

    public Antimations() {
        INSTANCE = this;
    }

    public static ItemModelMesher itemModelMesher;

    private void init() {
        AntimationsConfig.getInstance().preload();
        CommandManager.register(AntimationsCommand.getInstance());
        itemModelMesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
    }

    @Override
    public void onInitializeClient() {
        // idk this just makes it not crash
        MinecraftClientEvents.READY.register((minecraft) -> init());
    }
}