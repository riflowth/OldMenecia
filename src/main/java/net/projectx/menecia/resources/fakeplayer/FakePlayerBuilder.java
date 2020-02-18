package net.projectx.menecia.resources.fakeplayer;

import com.comphenix.protocol.wrappers.EnumWrappers;

public class FakePlayerBuilder {

    private FakePlayer fakePlayer;

    public FakePlayerBuilder() {
        fakePlayer = new FakePlayer();
    }

    public FakePlayer build() {
        return fakePlayer;
    }

    public FakePlayerBuilder setGameProfile(String gameProfileName) {
        fakePlayer.setGameProfile(gameProfileName);
        return this;
    }

    public FakePlayerBuilder setDisplayName(String displayName) {
        fakePlayer.setDisplayName(displayName);
        return this;
    }

    public FakePlayerBuilder setGameMode(EnumWrappers.NativeGameMode gameMode) {
        fakePlayer.setGameMode(gameMode);
        return this;
    }

    public FakePlayerBuilder setLatency(int latency) {
        fakePlayer.setLatency(latency);
        return this;
    }

    public FakePlayerBuilder setSkin(String value, String signature) {
        fakePlayer.setSkin(value, signature);
        return this;
    }

}
