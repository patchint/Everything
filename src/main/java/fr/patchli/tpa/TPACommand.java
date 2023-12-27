package fr.patchli.tpa;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TPACommand implements CommandExecutor {

    private final Map<UUID, UUID> teleportRequests;

    public TPACommand(Map<UUID, UUID> teleportRequests) {
        this.teleportRequests = teleportRequests;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Cette commande ne peut être exécuter que par un joueur.");
            return true;
        }
        Player player = (Player) sender;

        if(args.length != 1){
            player.sendMessage(ChatColor.RED + "Utilisation: /tpa <pseudo>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null || !target.isOnline()){
            player.sendMessage(ChatColor.RED + "Le joueur " + args[0] + " n'a pas été trouver ou il est hors ligne.");
            return true;
        }

        if (teleportRequests.containsKey(player.getUniqueId())) {
            player.sendMessage(ChatColor.YELLOW + "Vous avez déjà envoyer une demande de TP à ce joueur");
            return true;
        }

        teleportRequests.put(target.getUniqueId(), player.getUniqueId());
        player.sendMessage(ChatColor.GREEN + "Demande de tp envoyée au joueur " + target.getName() + ".");
        target.sendMessage(ChatColor.YELLOW + player.getName() + " vous a envoyer une demande de TP. Tapez /tpaccept pour accepter");

        return true;
    }

    public boolean teleport(Player player, Player target) {
        if (teleportRequests.containsKey(target.getUniqueId()) && teleportRequests.get(target.getUniqueId()).equals(player.getUniqueId())) {
            teleportRequests.remove(target.getUniqueId());

            player.teleport(target.getLocation());
            target.sendMessage(ChatColor.GREEN + "Demande de TP acceptée");

            Player requester = Bukkit.getPlayer(teleportRequests.get(player.getUniqueId()));
            if (requester != null) {
                requester.sendMessage(ChatColor.YELLOW + player.getName() + " a accepté votre demande de TP.");
            }

            return true;
        } else {
            player.sendMessage(ChatColor.RED + "Aucune demande de TP en attente de " + target.getName() + ".");
            return false;
        }
    }

    public Map<UUID, UUID> getTeleportRequests() {
        return teleportRequests;
    }
}
