package fr.patchli.backup;

import fr.patchli.Everything;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.broadcastMessage;

public class BackupCommand implements CommandExecutor {
    private final Everything plugin;

    public BackupCommand(Everything plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("backup")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                String playerName = player.getName();
                broadcastMessage(ChatColor.RED + playerName + " a fait la commande backup, donc des lags peuvent arriver");
                boolean success = Backup.saveBackup();
                if (success) {
                    player.sendMessage("Backup faite avec succès !");
                } else {
                    player.sendMessage("La backup a échoué. Veuillez vérifier dans la console si des erreurs se sont produites.");
                }
                return true;
            } else {
                sender.sendMessage("Seuls les joueurs peuvent utiliser cette commande.");
                return false;
            }
        }

        return false;
    }
}
