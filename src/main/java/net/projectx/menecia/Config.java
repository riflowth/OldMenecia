package net.projectx.menecia;

import net.projectx.menecia.resources.configs.MobSpawnerConfig;

public class Config {

    private MobSpawnerConfig mobSpawnerConfig;

    Config(Menecia plugin) {
        mobSpawnerConfig = new MobSpawnerConfig(plugin);
        mobSpawnerConfig.initialize();
    }

    public MobSpawnerConfig getMobSpawnerConfig() {
        return mobSpawnerConfig;
    }

}
