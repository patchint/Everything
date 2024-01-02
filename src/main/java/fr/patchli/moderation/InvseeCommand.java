package fr.patchli.moderation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InvseeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Uniquement les joueurs peuvent exécuter cette commande");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage("Utilisation : /invsee <nomdujoueur>");
            return true;
        }

        Player target = Bukkit.getPlayer((args[0]));

        if (target == null) {
            player.sendMessage("Ce joueur n'existe pas");
            return true;
        }

        Inventory targetInventory = target.getInventory();
        player.openInventory(targetInventory);

        return true;
    }

}
