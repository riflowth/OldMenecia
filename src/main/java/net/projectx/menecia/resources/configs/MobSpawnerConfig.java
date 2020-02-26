package net.projectx.menecia.resources.configs;

import net.projectx.menecia.mobs.spawner.MobSpawner;
import net.projectx.menecia.resources.utilities.Log;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MobSpawnerConfig {

    private File file;
    private String fileNameWithExtension = "MobSpawner.yml";
    private FileConfiguration config;
    private File pluginDataFolder;
    private Plugin plugin;

    public MobSpawnerConfig(Plugin plugin) {
        this.plugin = plugin;
        this.pluginDataFolder = plugin.getDataFolder();
        this.file = new File(pluginDataFolder, fileNameWithExtension);
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

    public void addSpawner(MobSpawner spawner) {
        Map<String, Object> object = new HashMap<>();
        object.put("mob-id", spawner.getMob().getId());
        object.put("spawn-rate", spawner.getSpawnRate().toString());
        Location firstLocation = spawner.getSpawningArea().getFirstLocation();
        Location secondLocation = spawner.getSpawningArea().getSecondLocation();
        Map<String, Object> locationObject = new HashMap<>();
        locationObject.put("first",
                Arrays.asList(
                        firstLocation.getBlockX(),
                        firstLocation.getBlockY(),
                        firstLocation.getBlockZ()
                )
        );
        locationObject.put("second",
                Arrays.asList(
                        secondLocation.getBlockX(),
                        secondLocation.getBlockY(),
                        secondLocation.getBlockZ()
                )
        );
        object.put("location", locationObject);
        object.put("maximum", spawner.getMaximumAmount());

        int spawnerId = getLatestSpawnerId() + 1;
        config.createSection("spawner-list." + spawnerId);
        config.set("spawner-list." + spawnerId, object);
        save();
    }

    public int getLatestSpawnerId() {
        List<?> spawnes = config.getList("spawner-list");
        if (spawnes != null) {
            return spawnes.size();
        } else {
            return 0;
        }
    }

    public List<MobSpawner> getSpawners() {
        List<MobSpawner> mobSpawners = new ArrayList<>();

        List<?> objects = config.getList("spawner-list");
        System.out.println(objects);

        return Collections.emptyList();
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        config = YamlConfiguration.loadConfiguration(file);
    }

}
