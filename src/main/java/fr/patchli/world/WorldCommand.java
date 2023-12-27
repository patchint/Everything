package fr.patchli.world;

import fr.patchli.utilities.DateAndTime;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Cette commande ne peut être exécuter que par un joueur.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatColor.YELLOW + "Utilisation: /world <tp|create|delete> [args]");
            return true;
        }

        String subCommand = args[0].toLowerCase();

        switch (subCommand) {
            case "tp":
                handleTpCommand(player, args);
                break;

            case "create":
                handleCreateCommand(player, args);
                break;

            case "delete":
                handleDeleteCommand(player, args);
                break;

            default:
                player.sendMessage(ChatColor.RED + "Sous command inconnue: " + subCommand);
                break;
        }

        return true;
    }

    private void handleTpCommand(Player player, String[] args) {
        if (args.length != 2) {
            player.sendMessage(ChatColor.RED + "Utilisation: /world tp <worldName>");
            return;
        }

        String worldName = args[1];

        World targetWorld = player.getServer().getWorld(worldName);

        if (targetWorld != null) {
            player.teleport(((World) targetWorld).getSpawnLocation());
            player.sendMessage(ChatColor.GREEN + "Téléportation dans le monde: " + worldName);
        } else {
            player.sendMessage(ChatColor.RED + "Ce monde n'existe pas");
        }
    }

    private void handleCreateCommand(Player player, String[] args) {
        if (args.length < 3 || args.length > 5) {
            player.sendMessage(ChatColor.RED + "Utilisation: /world create <Type de monde> <Nom du monde> [--time=<enable/disable>]");
            return;
        }

        String worldType = args[1].toLowerCase();
        String worldName = args[2];
        boolean includeTime = true;

        if (args.length >= 4) {
            if (args[3].equalsIgnoreCase("--time=disable")) {
                includeTime = false;
            } else if (args[3].equalsIgnoreCase("--time")) {
                if (args.length == 5) {
                    if (args[4].equalsIgnoreCase("disable")) {
                        includeTime = false;
                    }
                }
            }
        }

        String formattedDate = DateAndTime.getFormattedDate();
        WorldManager.createWorld(player, worldType, worldName, includeTime, formattedDate);
    }


    private void handleDeleteCommand(Player player, String[] args) {
        if (args.length != 2) {
            player.sendMessage(ChatColor.RED + "Utilisation: /world delete <Nom du monde>");
            return;
        }

        String worldName = args[1];

        WorldManager.deleteWorld(player, worldName);
    }
}