package at.yedel.antimations.utils;



import at.yedel.antimations.config.AntimationsConfig;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.play.server.S0BPacketAnimation;


@Sharable
public class AntimationsPacketHandler extends ChannelDuplexHandler {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof S0BPacketAnimation) {
			if (
				((S0BPacketAnimation) msg).getAnimationType() == 0
				&&
				AntimationsConfig.getInstance().cancelThirdPersonBlockAnimations.get()
			) return;
		}
		super.channelRead(ctx, msg);
	}
}
