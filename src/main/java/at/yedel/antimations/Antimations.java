package at.yedel.antimations;



import java.io.File;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

import at.yedel.antimations.config.AntimationsCommand;
import at.yedel.antimations.config.AntimationsConfig;
import at.yedel.antimations.gui.AntimationsGui;
import at.yedel.antimations.utils.AntimationsPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.realms.RealmsSharedConstants;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraftforge.fml.common.network.NetworkCheckHandler;
import net.minecraftforge.fml.relauncher.Side;



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
    public static final String version = "2.2.0";
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

    public static ItemModelMesher itemModelMesher;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        File suggestedConfigurationFile = event.getSuggestedConfigurationFile();
        suggestedConfigurationURI = suggestedConfigurationFile.toURI();
        AntimationsConfig.getInstance().setupConfig(suggestedConfigurationFile);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new AntimationsCommand());
        MinecraftForge.EVENT_BUS.register(this);
        itemModelMesher = minecraft.getRenderItem().getItemModelMesher();
    }

    // For some reason, Forge rejects clients trying to connect to servers with this mod, even though it is client side only.
    // This overrides the check and tells Forge that any player can connect.

    @NetworkCheckHandler
    public boolean permitPlayers(Map<String, String> modMap, Side side) {
        return true;
    }

    @SubscribeEvent
    public void onServerConnect(ClientConnectedToServerEvent event) {
        event.manager.channel().pipeline().addBefore("packet_handler", "antimations_packet_handler", new AntimationsPacketHandler());
    }

    @SubscribeEvent
    public void onOpenAnimationGUI(InitGuiEvent event) {
        if (Objects.equals(event.gui.getClass().getName(), "net.optifine.gui.GuiAnimationSettingsOF")) {
            event.buttonList.add(new GuiButton(2000, event.gui.width / 2 - 75, event.gui.height - 25, 150, 20, "Open Antimations Settings"));
        }
    }

    @SubscribeEvent
    public void onClickAntimationsButton(ActionPerformedEvent event) {
        if (Objects.equals(event.button.displayString, "Open Antimations Settings")) {
            minecraft.displayGuiScreen(new AntimationsGui(event.gui));
        }
    }
}