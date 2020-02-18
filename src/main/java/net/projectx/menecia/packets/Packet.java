package net.projectx.menecia.packets;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public abstract class Packet {

    protected PacketContainer packet;

    public Packet(PacketContainer packet) {
        this.packet = packet;
        packet.getModifier().writeDefaults();
    }

    public PacketContainer getPacket() {
        return packet;
    }

    public void sendPacket(Player receiver) {
        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(receiver, getPacket());
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Cannot send packet.", e);
        }
    }

    public void sendPacketNearby(Player originPlayer) {
        ProtocolLibrary.getProtocolManager().getEntityTrackers(originPlayer).forEach(this::sendPacket);
    }

    public void broadcastPacket() {
        ProtocolLibrary.getProtocolManager().broadcastServerPacket(getPacket());
    }

}
