package at.yedel.antimations;



import java.io.File;
import java.net.URI;

import at.yedel.antimations.config.AntimationsCommand;
import at.yedel.antimations.config.AntimationsConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.realms.RealmsSharedConstants;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;



@Mod(
        acceptedMinecraftVersions = "1.8.9",
        clientSideOnly = true,
        modid = Antimations.modid,
        name = Antimations.name,
        version = Antimations.version,
        guiFactory = "at.yedel.antimations.utils.AntimationsGuiFactory"
)
public class Antimations {
    public static final String modid = "antimations";
    public static final String name = "Antimations";
    public static final String version = "2.1.0";
    public static final String totalVersionString = "v" + version + "-" + RealmsSharedConstants.VERSION_STRING;

    public static final Minecraft minecraft = Minecraft.getMinecraft();

    @Instance
    private static Antimations instance;

    public static Antimations getInstance() {
        return instance;
    }

    private URI suggestedConfigurationURI;

    public URI getSuggestedConfigurationURI() {
        return suggestedConfigurationURI;
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        File suggestedConfigurationFile = event.getSuggestedConfigurationFile();
        suggestedConfigurationURI = suggestedConfigurationFile.toURI();
        AntimationsConfig.getInstance().setupConfig(suggestedConfigurationFile);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new AntimationsCommand());
        boolean isOverflowAnimationsLoaded = Loader.isModLoaded("overflowanimations");
        boolean isSk1erAnimationsLoaded = Loader.isModLoaded("sk1er_old_animations");
        boolean isOrangeAnimationsLoaded = Loader.isModLoaded("animations");
        boolean isSpiderfrogAnimationsLoaded = doesClassExist("com.spiderfrog.oldanimations.OldAnimationsMod");
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