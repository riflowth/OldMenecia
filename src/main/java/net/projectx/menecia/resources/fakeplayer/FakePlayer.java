package net.projectx.menecia.resources.fakeplayer;

import com.comphenix.protocol.wrappers.*;

import java.util.UUID;

public class FakePlayer {

    private WrappedGameProfile gameProfile;
    private String displayName;
    private EnumWrappers.NativeGameMode gameMode;
    private int latency;

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
