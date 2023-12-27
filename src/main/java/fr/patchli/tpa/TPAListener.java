package fr.patchli.tpa;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class TPAListener implements Listener {

    private final TPACommand tpaCommand;

    public TPAListener(TPACommand tpaCommand) {
        this.tpaCommand = tpaCommand;
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().equalsIgnoreCase("/tpaccept")) {
            Player player = event.getPlayer();
            Player requester = player.getServer().getPlayer(tpaCommand.getTeleportRequests().get(player.getUniqueId()));
            if (requester != null) {
                tpaCommand.teleport(requester, player);
            }
            event.setCancelled(true);
        }
    }
}