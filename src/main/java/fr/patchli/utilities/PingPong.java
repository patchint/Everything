package fr.patchli.utilities;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PingPong implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label,String[]args){

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Cette commande ne peut être exécuter que par un joueur.");
            return true;
        }

        Player player = (Player) sender;

        if(command.getName().equalsIgnoreCase("ping")){
            player.sendMessage(ChatColor.AQUA + "Pong!");
            return true;
        }

        return false;
    }

}
