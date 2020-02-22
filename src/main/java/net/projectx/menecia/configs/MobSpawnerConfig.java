package net.projectx.menecia.configs;

import net.projectx.menecia.utilities.Log;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class MobSpawnerConfig {

    private static MobSpawnerConfig instance;
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
        instance = this;
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

    public void destroy() {
        instance = null;
    }

    public void reload() {
        config = YamlConfiguration.loadConfiguration(file);
    }

    public static void set(String path, Object value) {
        instance.config.set(path, value);
    }

    public static String getString(String key) {
        return instance.config.getString(key);
    }

    public static int getInt(String key) {
        return instance.config.getInt(key);
    }

    public static double getDouble(String key) {
        return instance.config.getDouble(key);
    }

}
