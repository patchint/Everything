package fr.patchli.night;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class NightCommand implements CommandExecutor {

    private static Set<Player> confirmedPlayers = new HashSet<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande ne peut être exécutée que par un joueur.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("Utilisation : /night <y/n>");
            return true;
        }

        String argument = args[0];

        if (argument.equalsIgnoreCase("y")) {
            if (confirmedPlayers.contains(player)) {
                player.sendMessage("Vous avez déjà confirmé pour la nuit.");
                return true;
            }

            confirmedPlayers.add(player);
            player.sendMessage("Vous avez confirmé pour la nuit.");

            int onlinePlayers = Bukkit.getServer().getOnlinePlayers().size();
            if (confirmedPlayers.size() >= Math.ceil(onlinePlayers / 3.0)) {
                SleepListener.acceleratedNight();
                Bukkit.broadcastMessage(ChatColor.GREEN + "La nuit est passée.");
                confirmedPlayers.clear();
            } else {
                player.sendMessage("Attendez que d'autres joueurs répondent avant de passer la nuit.");
            }

        } else if (argument.equalsIgnoreCase("n")) {
            player.sendMessage("Vous ne souhaitez pas passer la nuit");
            confirmedPlayers.remove(player);
        } else {
            player.sendMessage("Utilisation : /night <y/n>");
        }

        return true;
    }

    public static boolean isConfirmationNeeded() {
        int onlinePlayers = Bukkit.getServer().getOnlinePlayers().size();
        return confirmedPlayers.size() < Math.ceil(onlinePlayers / 3.0);
    }

    public static void sendConfirmationRequest(Player requester) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (!onlinePlayer.equals(requester)) {
                onlinePlayer.sendMessage(ChatColor.YELLOW + requester.getName() + " souhaite passer la nuit. Répondez avec /night y ou /night n.");
            }
        }
    }
}
