package fr.patchli.world;

import fr.patchli.utilities.DateAndTime;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.generator.BlockPopulator;
import org.codehaus.plexus.util.FileUtils;
import org.bukkit.generator.ChunkGenerator;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldManager {

    public static void createWorld(Player player, String worldType, String worldName, boolean includeTime, String formattedDate) {
        String uniqueWorldName;
        if (includeTime) {
            uniqueWorldName = worldName + "_" + formattedDate;
        } else {
            uniqueWorldName = worldName;
        }

        WorldCreator worldCreator = new WorldCreator(uniqueWorldName);

        if ("flat".equalsIgnoreCase(worldType)) {
            worldCreator.type(WorldType.FLAT);
        } else if ("void".equalsIgnoreCase(worldType)) {
            worldCreator.generator(new VoidGenerator());
        } 

        World newWorld = Bukkit.createWorld(worldCreator);

        if (newWorld != null) {
            player.sendMessage(ChatColor.GREEN + "Monde : " + uniqueWorldName + " créé");
        } else {
            player.sendMessage(ChatColor.RED + "Le monde n'a pas pu être créé.");
        }
    }

    public static void createVoidWorld(Player player, String worldName) {
        String uniqueWorldName = worldName + "_" + DateAndTime.getFormattedDate();
        WorldCreator worldCreator = new WorldCreator(uniqueWorldName);

        worldCreator.generator(new VoidGenerator());

        World newWorld = Bukkit.createWorld(worldCreator);
    }

    public static class VoidGenerator extends ChunkGenerator {

        @Override
        public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
            ChunkData chunkData = createChunkData(world);

            if (x == 0 && z == 0) {
                for (int y = 0; y < 64; y++) { 
                    chunkData.setBlock(8, y, 8, Material.STONE); 
                }
            }

            return chunkData;
        }

        @Override
        public boolean canSpawn(World world, int x, int z) {
            return true;
        }

        @Override
        public @NotNull List<BlockPopulator> getDefaultPopulators(World world) {
            return new ArrayList<>();
        }
    }

    public static void deleteWorld(Player player, String worldName) {
        World world = Bukkit.getWorld(worldName);

        if (world != null) {
            Bukkit.unloadWorld(world, false);
            deleteWorldFolder(world.getWorldFolder());
            player.sendMessage(ChatColor.GREEN + "Monde : " + worldName + " supprimé");
        } else {
            player.sendMessage(ChatColor.RED + "Le monde spécifié n'existe pas.");
        }
    }

    private static void deleteWorldFolder(java.io.File worldFolder) {
        try {
            FileUtils.deleteDirectory(worldFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}