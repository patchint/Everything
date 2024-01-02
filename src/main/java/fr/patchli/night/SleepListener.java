package fr.patchli.night;

import fr.patchli.Everything;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class SleepListener implements Listener {

    private final Everything plugin;

    public SleepListener(Everything plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        if (event.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.OK) {
            Player player = event.getPlayer();

            // Vérifier si la confirmation de la nuit est nécessaire et s'il y a plus d'un joueur
            if (NightCommand.isConfirmationNeeded() && Bukkit.getOnlinePlayers().size() > 1) {
                player.sendMessage(ChatColor.YELLOW + "Attendez que tous les joueurs répondent avant d'accélérer la nuit.");
                NightCommand.sendConfirmationRequest(player);
            } else {
                // Accélérer la nuit au lieu de définir l'heure instantanément
                SleepListener.acceleratedNight();

                Bukkit.broadcastMessage(ChatColor.GREEN +  player.getName() + " va dormir. Bonne nuit !");
            }
        }
    }

    public static boolean acceleratedNight(){
        Bukkit.getWorlds().forEach(world -> {
            long time = world.getTime();
            long newTime = (time + 6000) % 24000;  // Ajouter 1200 ticks (1 in-game hour)
            world.setTime(newTime);
        });
        return true;
    }
}
