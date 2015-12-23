package main.astraeus.core.net.channel.packet.outgoing;

import main.astraeus.core.game.model.entity.item.Item;
import main.astraeus.core.game.model.entity.mobile.player.Player;
import main.astraeus.core.net.channel.packet.OutgoingPacket;
import main.astraeus.core.net.channel.packet.PacketBuilder;
import main.astraeus.core.net.channel.packet.PacketHeader;
import main.astraeus.core.net.channel.protocol.codec.game.ByteOrder;
import main.astraeus.core.net.channel.protocol.codec.game.ByteValue;

/**
 * The {@link OutgoingPacket} that sends an array of items on an interface.
 * 
 * @author SeVen
 */
public class SendItemOnInterface extends OutgoingPacket {

	/**
	 * The id of the interface to display the items on.
	 */
	private final int interfaceId;
	
	/**
	 * The items to display.
	 */
	private final Item[] items;
	
	/**
	 * Creates a new {@link SendItemOnInterface}.
	 * 
	 * @param interfaceId
	 * 		The id of the interface to display the items on.
	 * 
	 * @param items
	 * 		The items to display.
	 */
	public SendItemOnInterface(int interfaceId, Item[] items) {
		super(53, PacketHeader.VARIABLE_SHORT, 2048);
		this.interfaceId = interfaceId;
		this.items = items;
	}

	@Override
	public PacketBuilder dispatch(Player player) {
		player.getContext().prepare(this, builder);
		builder.putShort(interfaceId);
		builder.putShort(items.length);
		for(Item item : items) {
			if (item != null) {
			if(item.getAmount() > 254) {
				builder.putByte(255);
				builder.putInt(item.getAmount(), ByteOrder.INVERSE);				
			} else {
				builder.putByte(item.getAmount());
			}
			builder.putShort(item.getId() + 1, ByteValue.ADDITION, ByteOrder.LITTLE);
			} else {
				builder.putByte(0);
				builder.putShort(0, ByteValue.ADDITION, ByteOrder.LITTLE);
			}			
		}
		builder.endVariableShortPacketHeader();
		return builder;
	}

}