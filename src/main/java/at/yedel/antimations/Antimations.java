package at.yedel.antimations;



import at.yedel.antimations.config.AntimationsCommand;
import at.yedel.antimations.config.AntimationsConfig;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;



@Mod(
        acceptedMinecraftVersions = "1.8.9",
        clientSideOnly = true,
        modid = Antimations.modid,
        name = Antimations.name,
        version = Antimations.version,
        guiFactory = "at.yedel.antimations.gui.AntimationsGuiFactory"
)
public class Antimations {
    public static final String modid = "antimations";
    public static final String name = "Antimations";
    public static final String version = "2.0.2";

    public static final Minecraft minecraft = Minecraft.getMinecraft();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        AntimationsConfig.getInstance().setupConfig(event.getSuggestedConfigurationFile());
    }

    private boolean isOverflowAnimationsLoaded;
    private boolean isSk1erAnimationsLoaded;
    private boolean isOrangeAnimationsLoaded;
    private boolean isSpiderfrogAnimationsLoaded;

    public boolean isOverflowAnimationsLoaded() {
        return isOverflowAnimationsLoaded;
    }

    public boolean isSk1erAnimationsLoaded() {
        return isSk1erAnimationsLoaded;
    }

    public boolean isOrangeAnimationsLoaded() {
        return isOrangeAnimationsLoaded;
    }

    public boolean isSpiderfrogAnimationsLoaded() {
        return isSpiderfrogAnimationsLoaded;
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new AntimationsCommand());
        isOverflowAnimationsLoaded = Loader.isModLoaded("overflowanimations");
        isSk1erAnimationsLoaded = Loader.isModLoaded("sk1er_old_animations");
        isOrangeAnimationsLoaded = Loader.isModLoaded("animations");
        isSpiderfrogAnimationsLoaded = doesClassExist("com.spiderfrog.oldanimations.OldAnimationsMod");
    }

    private boolean doesClassExist(String className) {
        try {
            Class.forName(className);
            return true;
        }
        catch (ClassNotFoundException e) {
            return false;
        }
    }
}