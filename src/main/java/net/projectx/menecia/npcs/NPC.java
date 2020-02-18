package net.projectx.menecia.npcs;

import com.comphenix.protocol.wrappers.*;
import net.projectx.menecia.packets.ServerNamedEntitySpawn;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class NPC {

    private WrappedGameProfile gameProfile;
    private String displayName;
    private EnumWrappers.NativeGameMode gameMode;
    private int latency;

    public void spawn(Player player,  Location location) {
        ServerNamedEntitySpawn packet = new ServerNamedEntitySpawn();
        packet.setEntityID(ThreadLocalRandom.current().nextInt());
        packet.setPlayerUUID(gameProfile.getUUID());
        packet.setLocation(location);
        packet.sendPacket(player);
    }

    public PlayerInfoData getPlayerInfoData() {
        return new PlayerInfoData(gameProfile, latency, gameMode, WrappedChatComponent.fromText(displayName));
    }

    public WrappedGameProfile getGameProfile() {
        return gameProfile;
    }

    public void setGameProfile(String gameProfileName) {
        this.gameProfile = new WrappedGameProfile(UUID.randomUUID(), gameProfileName);
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public EnumWrappers.NativeGameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(EnumWrappers.NativeGameMode gameMode) {
        this.gameMode = gameMode;
    }

    public int getLatency() {
        return latency;
    }

    public void setLatency(int latency) {
        this.latency = latency;
    }

    public void setSkin(String value, String signature) {
        gameProfile.getProperties().put("textures", new WrappedSignedProperty("textures", value, signature));
    }

}
