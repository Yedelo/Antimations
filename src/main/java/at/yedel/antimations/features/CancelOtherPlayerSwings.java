package at.yedel.antimations.features;



import at.yedel.antimations.config.AntimationsConfig;
import net.minecraft.network.play.server.S0BPacketAnimation;
import cc.polyfrost.oneconfig.events.event.ReceivePacketEvent;
import cc.polyfrost.oneconfig.libs.eventbus.Subscribe;



public class CancelOtherPlayerSwings {
    private static final CancelOtherPlayerSwings INSTANCE = new CancelOtherPlayerSwings();

    public static CancelOtherPlayerSwings getInstance() {
        return INSTANCE;
    }

    private CancelOtherPlayerSwings() {}

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
}
