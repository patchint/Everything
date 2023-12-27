package fr.patchli.player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class EnderchestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande ne peut être exécuter que par un joueur.");
            return true;
        }
        Player player = (Player) sender;
        Inventory enderChestInventory = player.getEnderChest();
        player.openInventory(enderChestInventory);

        return true;
    }
}
