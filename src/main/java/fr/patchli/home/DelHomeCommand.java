package fr.patchli.home;

import fr.patchli.Everything;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

public class DelHomeCommand implements CommandExecutor {

    private final Everything plugin;
    private final FileHome fileHome;

    public DelHomeCommand(Everything plugin, FileHome fileHome) {
        this.plugin = plugin;
        this.fileHome = fileHome;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Cette commande ne peut être exécuter que par un joueur.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            Set<String> homeNames = fileHome.getPlayerHomes(player.getUniqueId()).keySet();
            player.sendMessage(ChatColor.YELLOW + "Vos homes: " + ChatColor.WHITE + String.join(", ", homeNames));
            return true;
        }

        String homeName = args[0];
        Set<String> homeNames = fileHome.getPlayerHomes(player.getUniqueId()).keySet();

        if (!homeNames.contains(homeName)) {
            player.sendMessage(ChatColor.RED + "Aucun home avec comme nom'" + homeName + "' n'a été trouvé.");
            return true;
        }

        fileHome.getPlayerHomes(player.getUniqueId()).remove(homeName);
        player.sendMessage(ChatColor.GREEN + "Home '" + homeName + "' supprimé.");

        fileHome.saveHomes();

        return true;
    }
}