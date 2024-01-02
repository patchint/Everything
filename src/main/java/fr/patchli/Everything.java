package fr.patchli;

import fr.patchli.home.*;
import fr.patchli.moderation.*;
import fr.patchli.night.NightCommand;
import fr.patchli.night.SleepListener;
import fr.patchli.player.*;
import fr.patchli.tpa.*;
import fr.patchli.utilities.*;
import fr.patchli.backup.*;
import fr.patchli.perms.*;
import fr.patchli.world.*;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class Everything extends JavaPlugin implements Listener {

    private static final String CONFIG_FOLDER = "Everything";
    private FileHome fileHome;

    @Override
    public void onEnable() {
        // On envoie dans les logs du serveur que le plugin a démarré
        getLogger().info("Everything a démarré");

        // Chargement des mondes existants

        WorldLoader worldLoader = new WorldLoader();
        worldLoader.loadAllWorlds();

        // Chargement des homes
        fileHome = new FileHome(getLogger(), getDataFolder());
        fileHome.loadHomes(getDataFolder());
        fileHome.saveHomes();

        getServer().getPluginManager().registerEvents(this, this);

        // TPA 
        TPACommand tpaCommand = new TPACommand(teleportRequests);
        getCommand("tpa").setExecutor(tpaCommand);
        getCommand("tpaccept").setExecutor(tpaCommand);
        getServer().getPluginManager().registerEvents(new TPAListener(tpaCommand), this);

        // Utilities 
        Infos infosCommand = new Infos(this);
        getCommand("infos").setExecutor(infosCommand);
        infosCommand.runTaskTimer(this, 0L, 20L);

        // Homes 
        getServer().getScheduler().runTaskTimer(this, fileHome::saveHomes, 0L, 1200L);
        getCommand("sethome").setExecutor(new SetHome(fileHome));
        getCommand("home").setExecutor(new HomeCommand(this, fileHome));
        getCommand("delhome").setExecutor(new DelHomeCommand(this, fileHome));

        getServer().getScheduler().runTaskTimer(this, Backup::saveBackup, 0L, 72000L);
        getCommand("backup").setExecutor(new BackupCommand(this));

        // Dimensions 

        getCommand("world").setExecutor(new WorldCommand());

        // Player 

        getCommand("ec").setExecutor(new EnderchestCommand());

        // Night

        getCommand("night").setExecutor(new NightCommand());
        getServer().getPluginManager().registerEvents(new SleepListener(this), this);

        // Moderation

        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("invsee").setExecutor(new InvseeCommand());

        // Suppression du censorship de Mojang dans le tchat
        // Code issue du plugin NoCensorShip
        this.saveDefaultConfig();
        this.initListener(new ChatMessage(this));

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String playerName = event.getPlayer().getName();
        event.getPlayer().sendMessage(ChatColor.GOLD + "Salut " + playerName + "! Amuse toi bien sur ce serveur MC");
        event.getPlayer().sendMessage(ChatColor.GOLD + "Nombre de joueur connectés : " + Bukkit.getServer().getOnlinePlayers().size());
        event.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + ("Plugin fait par patchli.fr avec le ♥" + " | " + "Version : " + this.getDescription().getVersion()));

        event.getPlayer().sendMessage(ChatColor.YELLOW + "Si vous voulez avoir les infos actuels du serveur, faites cette commande : /infos");
    }

    Map<UUID, UUID> teleportRequests = new HashMap<>();


    private void initListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Everything est stop");

        fileHome.saveHomes();
    }
}