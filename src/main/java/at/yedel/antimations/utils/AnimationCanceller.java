package at.yedel.antimations.utils;



import net.minecraft.network.play.client.C0APacketAnimation;

import static at.yedel.antimations.Antimations.minecraft;



public class AnimationCanceller {
    // When we redirect and don't put anything, it cancels the swing action in EntityPlayerSP,
    // which has the call to send the swing packet. We still need to send this packet, so lets replace
    // swingItem with sending the animation packet.
    public static void sendAnimationPacket() {
        minecraft.getNetHandler().addToSendQueue(new C0APacketAnimation());
    }
}
