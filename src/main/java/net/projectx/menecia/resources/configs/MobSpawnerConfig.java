package net.projectx.menecia.resources.configs;

import net.projectx.menecia.resources.utilities.Log;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class MobSpawnerConfig {

    private File file;
    private String fileNameWithExtension = "MobSpawner.yml";
    private FileConfiguration config;
    private File pluginDataFolder;
    private Plugin plugin;

    public MobSpawnerConfig(Plugin plugin) {
        this.plugin = plugin;
        this.pluginDataFolder = plugin.getDataFolder();
        file = new File(pluginDataFolder, fileNameWithExtension);
    }

    public void initialize() {
        if (!file.exists()) {
            if (!pluginDataFolder.exists()) {
                if (pluginDataFolder.mkdir()) {
                    Log.sendSuccess("Plugin folder has been created!");
                } else {
                    Log.sendError("Plugin folder has been created!");
                }
            }
            plugin.saveResource(fileNameWithExtension, false);
            Log.sendSuccess(fileNameWithExtension + " has been created!");
        }
        config = YamlConfiguration.loadConfiguration(file);
        Log.sendSuccess(fileNameWithExtension + " has been loaded!");
    }

    public void reload() {
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void set(String path, Object value) {
        config.set(path, value);
    }

    public String getString(String key) {
        return config.getString(key);
    }

    public int getInt(String key) {
        return config.getInt(key);
    }

    public double getDouble(String key) {
        return config.getDouble(key);
    }

}
