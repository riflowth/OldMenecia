package net.projectx.menecia.npcs;

import com.comphenix.protocol.wrappers.EnumWrappers;

public class NPCBuilder {

    private NPC npc;

    public NPCBuilder() {
        npc = new NPC();
    }

    public NPC build() {
        return npc;
    }

    public NPCBuilder setGameProfile(String gameProfileName) {
        npc.setGameProfile(gameProfileName);
        return this;
    }

    public NPCBuilder setDisplayName(String displayName) {
        npc.setDisplayName(displayName);
        return this;
    }

    public NPCBuilder setGameMode(EnumWrappers.NativeGameMode gameMode) {
        npc.setGameMode(gameMode);
        return this;
    }

    public NPCBuilder setLatency(int latency) {
        npc.setLatency(latency);
        return this;
    }

    public NPCBuilder setSkin(String value, String signature) {
        npc.setSkin(value, signature);
        return this;
    }

}
