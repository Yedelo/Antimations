package at.yedel.antimations;



import java.util.Map;
import java.util.Objects;

import at.yedel.antimations.config.AntimationsCommand;
import at.yedel.antimations.config.AntimationsConfig;
import at.yedel.antimations.launch.AntimationsConstants;
import cc.polyfrost.oneconfig.events.EventManager;
import cc.polyfrost.oneconfig.events.event.ReceivePacketEvent;
import cc.polyfrost.oneconfig.libs.eventbus.Subscribe;
import cc.polyfrost.oneconfig.utils.commands.CommandManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraftforge.fml.common.network.NetworkCheckHandler;
import net.minecraftforge.fml.relauncher.Side;



@Mod(
    modid = AntimationsConstants.MOD_ID,
    name = AntimationsConstants.MOD_NAME,
    version = AntimationsConstants.MOD_VERSION,
    clientSideOnly = true
)
public class Antimations {
    @Instance
    private static Antimations instance;

    public static Antimations getInstance() {
        return instance;
    }

    public static ItemModelMesher itemModelMesher;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        EventManager.INSTANCE.register(this);
        itemModelMesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        CommandManager.INSTANCE.registerCommand(AntimationsCommand.getInstance());
    }

    @SubscribeEvent
    public void cancelLimbMovements(RenderLivingEvent.Pre event) {
        if (!AntimationsConfig.getInstance().enabled) return;
        EntityLivingBase entity = event.entity;
        if (
            AntimationsConfig.getInstance().cancelOwnLimbMovements && entity == Minecraft.getMinecraft().thePlayer
            ||
            AntimationsConfig.getInstance().cancelOtherLimbMovements && entity != Minecraft.getMinecraft().thePlayer
        ) {
            entity.limbSwingAmount = 0;
        }
    }

    @Subscribe
    public void cancelOtherPlayerSwings(ReceivePacketEvent event) {
        if (AntimationsConfig.getInstance().enabled && AntimationsConfig.getInstance().cancelOtherPlayerSwings) {
            if (event.packet instanceof S0BPacketAnimation) {
                if (((S0BPacketAnimation) event.packet).getAnimationType() == 0) {
                    event.isCancelled = true;
                }
            }
        }
    }

    // For some reason, Forge rejects clients trying to connect to servers with this mod, even though it is client side only.
    // This overrides the check and tells Forge that any player can connect.

    @NetworkCheckHandler
    public boolean permitPlayers(Map<String, String> modMap, Side side) {
        return true;
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
            AntimationsConfig.getInstance().openGui();
        }
    }
}