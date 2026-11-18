package at.yedel.antimations.features;



import at.yedel.antimations.config.AntimationsConfig;
import net.minecraft.network.play.server.S0BPacketAnimation;
import org.polyfrost.oneconfig.api.event.v1.events.PacketEvent;
import org.polyfrost.oneconfig.api.event.v1.invoke.impl.Subscribe;



public class CancelOtherPlayerSwings {
    private static final CancelOtherPlayerSwings INSTANCE = new CancelOtherPlayerSwings();

    public static CancelOtherPlayerSwings getInstance() {
        return INSTANCE;
    }

    private CancelOtherPlayerSwings() {}

    @Subscribe
    public void cancelOtherPlayerSwings(PacketEvent.Receive event) {
        if (AntimationsConfig.getInstance().enabled && AntimationsConfig.getInstance().cancelOtherPlayerSwings) {
            if (event.getPacket() instanceof S0BPacketAnimation) {
                if (((S0BPacketAnimation) event.getPacket()).getAnimationType() == 0) {
                    event.cancelled = true;
                }
            }
        }
    }
}
