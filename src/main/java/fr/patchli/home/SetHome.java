package fr.patchli.home;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class SetHome implements CommandExecutor {

    private final FileHome fileHome;

    public SetHome(FileHome fileHome) {
        this.fileHome = fileHome;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Cette commande ne peut être exécuter que par un joueur.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage(ChatColor.RED + "Utilisation: /sethome <nom>");
            return true;
        }

        String homeName = args[0];
        Map<String, HomeLocation> playerHomes = fileHome.getPlayerHomes(player.getUniqueId());

        Location playerLocation = player.getLocation();
        HomeLocation homeLocation = new HomeLocation(playerLocation.getX(), playerLocation.getY(), playerLocation.getZ(), playerLocation.getWorld().getName());

        playerHomes.put(homeName, homeLocation);
        player.sendMessage(ChatColor.GREEN + "Home '" + homeName + "' mis!");

        fileHome.saveHomes();

        return true;
    }
}
