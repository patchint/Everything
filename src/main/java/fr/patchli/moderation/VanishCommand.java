package fr.patchli.moderation;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class VanishCommand implements CommandExecutor {

    private final Set<UUID> vanishedPlayers = new HashSet<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Cette commande ne peut être exécuter que par un joueur.");
            return true;
        }

        Player player = (Player) sender;

        toggleVanish(player);
        return true;
    }

    private void toggleVanish(Player player) {
        UUID playerId = player.getUniqueId();
        boolean isVanished = vanishedPlayers.contains(playerId);

        for (Player onlinePlayer : player.getServer().getOnlinePlayers()) {
            if (isVanished) {
                onlinePlayer.showPlayer(player);
            } else {
                onlinePlayer.hidePlayer(player);
            }
        }

        if (isVanished) {
            vanishedPlayers.remove(playerId);
        } else {
            vanishedPlayers.add(playerId);
        }

        player.sendMessage(ChatColor.GREEN + "Tu es maintenant " + (isVanished ? "visible." : "invisible."));
    }
}
