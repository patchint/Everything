package fr.patchli.home;

import fr.patchli.Everything;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class HomeCommand implements CommandExecutor {

    private final Everything plugin;
    private final FileHome fileHome;

    public HomeCommand(Everything plugin, FileHome fileHome) {
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

        UUID playerUUID = player.getUniqueId();
        Map<String, HomeLocation> playerHomes = fileHome.getPlayerHomes(playerUUID);

        if (args.length == 0) {
            Set<String> homeNames = playerHomes.keySet();
            player.sendMessage(ChatColor.YELLOW + "Vos homes: " + ChatColor.WHITE + String.join(", ", homeNames));
            return true;
        }

        String homeName = args[0];

        if (!playerHomes.containsKey(homeName)) {
            player.sendMessage(ChatColor.RED + "Aucun home avec comme nom'" + homeName + "' n'a été trouvé.");
            return true;
        }

        HomeLocation homeLocation = playerHomes.get(homeName);
        Location teleportLocation = new Location(
                plugin.getServer().getWorld(homeLocation.getWorld()),
                homeLocation.getX(),
                homeLocation.getY(),
                homeLocation.getZ()
        );

        player.teleport(teleportLocation);
        player.sendMessage(ChatColor.GREEN + "TP au home '" + homeName + "'.");

        return true;
    }
}
