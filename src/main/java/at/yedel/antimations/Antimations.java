package at.yedel.antimations;



import at.yedel.antimations.config.AntimationsCommand;
import at.yedel.antimations.config.AntimationsConfig;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
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
    public static final String version = "2.0.0";

    public static final Minecraft minecraft = Minecraft.getMinecraft();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        AntimationsConfig.instance.setupConfig(event.getSuggestedConfigurationFile());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new AntimationsCommand());
    }
}