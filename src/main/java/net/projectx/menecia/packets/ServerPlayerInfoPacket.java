package net.projectx.menecia.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;

import java.util.List;

public class ServerPlayerInfoPacket extends Packet {

    private static final PacketType packetType = PacketType.Play.Server.PLAYER_INFO;

    public ServerPlayerInfoPacket() {
        super(new PacketContainer(packetType));
    }

    public void setAction(EnumWrappers.PlayerInfoAction value) {
        packet.getPlayerInfoAction().write(0, value);
    }

    public void setData(List<PlayerInfoData> value) {
        packet.getPlayerInfoDataLists().write(0, value);
    }

}
