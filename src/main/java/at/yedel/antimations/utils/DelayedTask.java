package at.yedel.antimations.utils;



import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;



public class DelayedTask {
    private int counter;
    private final Runnable runnable;

    public DelayedTask(Runnable runnable) {
        counter = 0;
        this.runnable = runnable;
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase != Phase.START) return;
        if (counter <= 0) {
            MinecraftForge.EVENT_BUS.unregister(this);
            runnable.run();
        }
        counter --;
    }
}


