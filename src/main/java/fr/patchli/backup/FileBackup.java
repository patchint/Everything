package fr.patchli.backup;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FileBackup {

    private final Logger logger;
    private static List<String> backupWorlds = new ArrayList<>();
    private final File dataFolder;

    public FileBackup(Logger logger, File dataFolder) {
        this.logger = logger;
        this.dataFolder = dataFolder;
        backupWorlds = new ArrayList<>();
    }

    public static List<String> getBackupWorlds() {
        return new ArrayList<>(backupWorlds);
    }

    public void saveBackup() {
        File backupFile = new File(dataFolder, "backup.yml");
        YamlConfiguration config = new YamlConfiguration();

        config.set("backup", backupWorlds);

        try {
            config.save(backupFile);
        } catch (IOException e) {
            e.printStackTrace();
            logger.severe("N'a pas réussi à charger le fichier de backup: " + e.getMessage());
        }
    }

    public void loadBackup() {
        File backupFile = new File(dataFolder, "backup.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(backupFile);

        backupWorlds = config.getStringList("backup");

        logger.info("La configuration des backups a été chargé avec succès.");
    }
}
