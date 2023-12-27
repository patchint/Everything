package fr.patchli.world;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.File;

public class WorldLoader {

    public void loadAllWorlds() {
        File worldFolder = new File(Bukkit.getWorldContainer().getAbsolutePath());
        for (File file : worldFolder.listFiles()) {
            if (file.isDirectory()) {
                File levelDat = new File(file, "level.dat");
                if (levelDat.exists()) {
                    loadWorld(file.getName());
                }
            }
        }
    }

    public void loadWorld(String worldName) {
        World existingWorld = Bukkit.getWorld(worldName);
        if (existingWorld != null) {
            System.out.println("Monde '" + worldName + "' est déjà chargé.");
            return;
        }

        WorldCreator worldCreator = new WorldCreator(worldName);
        World loadedWorld = Bukkit.createWorld(worldCreator);

        if (loadedWorld != null) {
            System.out.println("Monde '" + worldName + "' s'est bien chargé.");
        } else {
            System.out.println("Échec du chargement du monde '" + worldName + "'.");
        }
    }
}