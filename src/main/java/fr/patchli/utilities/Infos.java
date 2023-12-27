package fr.patchli.utilities;

import fr.patchli.Everything;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.text.DecimalFormat;

import static org.bukkit.Bukkit.getServer;

public class Infos extends BukkitRunnable implements CommandExecutor{

    private final Everything plugin;

    public Infos(Everything plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        double tps = getServer().getTPS()[0];
        currentTPS = tps;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Cette commande ne peut être exécuter que par un joueur.");
            return true;
        }

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("infos")) {
            String serverInfo = getServerInformation();
            String tpsInfo = "\nTPS: " + getCurrentTPS(); 
            player.sendMessage(ChatColor.YELLOW + "Server Information:\n" + serverInfo + tpsInfo);
            return true;
        }


        return false;
    }

    public String getServerInformation() {
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();

        String cpuInfo = "CPU: " + osBean.getAvailableProcessors() + " cores\n";
        String systemType = "Système: " + osBean.getName() + " " + osBean.getVersion() + "\n";

        long maxMemory = Runtime.getRuntime().maxMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();

        DecimalFormat format = new DecimalFormat("#,##0.00");

        String memoryInfo = "Memoire (Pour Java) \n" +
                "Max: " + format.format(maxMemory / (1024.0 * 1024.0)) + " MB, \n" +
                "Total: " + format.format(totalMemory / (1024.0 * 1024.0)) + " MB, \n" +
                "Libre: " + format.format(freeMemory / (1024.0 * 1024.0)) + " MB";

        return systemType + "\n" + cpuInfo + "\n" + memoryInfo;
    }

    double currentTPS;

    private void displayTPS() {
        double tps = getServer().getTPS()[0];
        currentTPS = tps;
    }

    public String formatTPS(double tps) {
        DecimalFormat format = new DecimalFormat("#0.00");
        return format.format(tps);
    }

    public double getCurrentTPS() {
        return currentTPS;
    }
}

