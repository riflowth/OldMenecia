package net.projectx.menecia.npcs;

import com.comphenix.protocol.wrappers.EnumWrappers;

public class NPCBuilder {

    private NPC NPC;

    public NPCBuilder() {
        NPC = new NPC();
    }

    public NPC build() {
        return NPC;
    }

    public NPCBuilder setGameProfile(String gameProfileName) {
        NPC.setGameProfile(gameProfileName);
        return this;
    }

    public NPCBuilder setDisplayName(String displayName) {
        NPC.setDisplayName(displayName);
        return this;
    }

    public NPCBuilder setGameMode(EnumWrappers.NativeGameMode gameMode) {
        NPC.setGameMode(gameMode);
        return this;
    }

    public NPCBuilder setLatency(int latency) {
        NPC.setLatency(latency);
        return this;
    }

    public NPCBuilder setSkin(String value, String signature) {
        NPC.setSkin(value, signature);
        return this;
    }

}
