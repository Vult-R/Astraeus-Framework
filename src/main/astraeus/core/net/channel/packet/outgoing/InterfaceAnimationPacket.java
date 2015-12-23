package main.astraeus.core.net.channel.packet.outgoing;

import main.astraeus.core.game.model.entity.mobile.player.Player;
import main.astraeus.core.net.channel.packet.OutgoingPacket;
import main.astraeus.core.net.channel.packet.PacketBuilder;

/**
 * The {@link OutgoingPacket} that shows an animation on an interface.
 * 
 * @author SeVen
 */
public class InterfaceAnimationPacket extends OutgoingPacket {
	
	/**
	 * The id of the interface that is showing the animation.
	 */
	private final int interfaceId;
	
	/**
	 * The id of the animation that is showing.
	 */
	private final int animationId;

	/**
	 * Creates a new {@link InterfaceAnimationPacket}.
	 * 
	 * @param interfaceId
	 * 		The id of the interface that is showing the animation.
	 * 
	 * @param animationId
	 * 		The id of the animation that is showing.
	 */
	public InterfaceAnimationPacket(int interfaceId, int animationId) {
		super(200, 10);
		this.interfaceId = interfaceId;
		this.animationId = animationId;
	}

	@Override
	public PacketBuilder dispatch(Player player) {
		player.getContext().prepare(this, builder);
		builder.putShort(interfaceId);
		builder.putShort(animationId);
		return builder;
	}

}