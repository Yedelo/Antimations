package at.yedel.antimations;



import at.yedel.antimations.config.AntimationsConfig;
import at.yedel.antimations.features.AntimationsCommand;
import at.yedel.antimations.features.CancelOtherPlayerSwings;
import at.yedel.antimations.launch.AntimationsConstants;
//? if v0 {
import cc.polyfrost.oneconfig.events.EventManager;
import cc.polyfrost.oneconfig.utils.commands.CommandManager;
//?} else {
//import org.polyfrost.oneconfig.api.commands.v1.CommandManager;
//import org.polyfrost.oneconfig.api.event.v1.EventManager;
//?}
//? if forge {
import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkCheckHandler;
import net.minecraftforge.fml.relauncher.Side;
//?} else {
// import net.fabricmc.api.ClientModInitializer;
// import net.ornithemc.osl.lifecycle.api.client.MinecraftClientEvents;
//?}
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;


import java.util.Map;
import java.util.Objects;





//? if forge {
@Mod(
    modid = AntimationsConstants.MOD_ID,
    name = AntimationsConstants.MOD_NAME,
    version = AntimationsConstants.MOD_VERSION,
    clientSideOnly = true
)
//?}
public class Antimations /*? if ornithe {*/ /*implements ClientModInitializer *//*?}*/ {
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
        EventManager.INSTANCE.register(CancelOtherPlayerSwings.getInstance());
        CommandManager.register(AntimationsCommand.getInstance());
        itemModelMesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
    }

    //? if forge {
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        init();
        MinecraftForge.EVENT_BUS.register(this);
    }

    // For some reason, Forge rejects clients trying to connect to servers with this mod, even though it is client side only.
    // This overrides the check and tells Forge that any player can connect.
    @NetworkCheckHandler
    public boolean permitPlayers(Map<String, String> modMap, Side side) {
        return true;
    }

    @SubscribeEvent
    public void onOpenAnimationGUI(GuiScreenEvent.InitGuiEvent event) {
        if (Objects.equals(event.gui.getClass().getName(), "net.optifine.gui.GuiAnimationSettingsOF")) {
            event.buttonList.add(new GuiButton(2000, event.gui.width / 2 - 75, event.gui.height - 25, 150, 20, "Open Antimations Settings"));
        }
    }

    @SubscribeEvent
    public void onClickAntimationsButton(GuiScreenEvent.ActionPerformedEvent event) {
        if (Objects.equals(event.button.displayString, "Open Antimations Settings")) {
            AntimationsConfig.getInstance().open();
        }
    }
     
    //?} else {
    /*@Override
    public void onInitializeClient() {
        // idk this just makes it not crash
        MinecraftClientEvents.READY.register((minecraft) -> init());
    }
    *///?}
}