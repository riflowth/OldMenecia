package net.projectx.menecia;

import net.projectx.menecia.resources.configs.MobSpawnerConfig;

public class Config {

    private MobSpawnerConfig mobSpawnerConfig;

    Config(Menecia plugin) {
        this.mobSpawnerConfig = new MobSpawnerConfig(plugin);
    }

    public MobSpawnerConfig getMobSpawnerConfig() {
        return mobSpawnerConfig;
    }

}
