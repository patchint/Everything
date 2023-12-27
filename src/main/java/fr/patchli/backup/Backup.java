package fr.patchli.backup;

import fr.patchli.utilities.DateAndTime;
import org.bukkit.ChatColor;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.bukkit.Bukkit.broadcastMessage;

public class Backup {

    public static boolean saveBackup(){
        try {
            // Si vous avez plusieurs mondes, vous devez modifier la liste des mondes qui vont être backup ici, à l'avenir je compte mettre la config
            // des backups dans le fichier config.yml, c'est juste que puisque pour l'instant ça ne pressait pas je ne l'ai pas encore fait.s

            broadcastMessage(ChatColor.RED + "Backup démarrée. Attention des lags peuvent arriver");

            Path worldPath = Paths.get("world");
            Path netherPath = Paths.get("world_nether");
            Path endPath = Paths.get("world_the_end");

            Path backupPath = Paths.get("plugins/Everything/backup");
            Files.createDirectories(backupPath);

            try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(backupPath.resolve("backup_" + DateAndTime.getFormattedDate() + ".zip")))) {
                compressFolder(worldPath, "world", zipOutputStream);
                compressFolder(netherPath, "world_nether", zipOutputStream);
                compressFolder(endPath, "world_the_end", zipOutputStream);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            broadcastMessage(ChatColor.RED + "Backup faite avec succès !");

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void compressFolder(Path source, String entryName, ZipOutputStream zipOutputStream) throws IOException {
        try (Stream<Path> paths = Files.walk(source)) {
            paths.forEach(path -> {
                if (Files.isRegularFile(path)) {
                    try {
                        String relativePath = source.relativize(path).toString().replace("\\", "/");
                        ZipEntry zipEntry = new ZipEntry(entryName + "/" + relativePath);
                        zipOutputStream.putNextEntry(zipEntry);
                        Files.copy(path, zipOutputStream);
                        zipOutputStream.closeEntry();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
