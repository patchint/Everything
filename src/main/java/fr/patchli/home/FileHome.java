package fr.patchli.home;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

public class FileHome {

    private final Logger logger;
    private Map<UUID, Map<String, HomeLocation>> playerHomes = new HashMap<>();
    private final File dataFolder;

    public FileHome(Logger logger, File dataFolder) {
        this.logger = logger;
        this.dataFolder = dataFolder;
        this.playerHomes = new HashMap<>();
    }

    public Map<String, HomeLocation> getPlayerHomes(UUID playerId) {
        return playerHomes.computeIfAbsent(playerId, k -> new HashMap<>());
    }

    public void saveHomes() {
        File homesFile = new File(dataFolder, "homes.yml");
        YamlConfiguration config = new YamlConfiguration();

        for (Map.Entry<UUID, Map<String, HomeLocation>> entry : playerHomes.entrySet()) {
            String uuidString = entry.getKey().toString();

            for (Map.Entry<String, HomeLocation> homeEntry : entry.getValue().entrySet()) {
                String homeName = homeEntry.getKey();
                HomeLocation homeLocation = homeEntry.getValue();

                String path = uuidString + "." + homeName + ".";
                config.set(path + "x", homeLocation.getX());
                config.set(path + "y", homeLocation.getY());
                config.set(path + "z", homeLocation.getZ());
                config.set(path + "world", homeLocation.getWorld());
            }
        }

        try {
            config.save(homesFile);
        } catch (IOException e) {
            e.printStackTrace();
            logger.severe("N'a pas réussi à save les homes: " + e.getMessage());
        }
    }

    public void loadHomes(File dataFolder) {
        File homesFile = new File(dataFolder, "homes.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(homesFile);

        playerHomes.clear();

        for (String uuidString : config.getKeys(false)) {
            UUID uuid = UUID.fromString(uuidString);
            Map<String, HomeLocation> homes = new HashMap<>();

            for (String homeName : config.getConfigurationSection(uuidString).getKeys(false)) {
                String path = uuidString + "." + homeName + ".";
                double x = config.getDouble(path + "x");
                double y = config.getDouble(path + "y");
                double z = config.getDouble(path + "z");
                String world = config.getString(path + "world");

                Location location = new Location(Bukkit.getWorld(world), x, y, z);
                homes.put(homeName, new HomeLocation(x, y, z, world));
            }

            playerHomes.put(uuid, homes);
        }

        logger.info("Les homes ont été chargés avec succès.");
    }
}
