package fr.patchli.utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatMessage implements Listener {

    private JavaPlugin plugin;

    public ChatMessage(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChatSendMessage(AsyncPlayerChatEvent event) {
        event.setCancelled(true);
        String message = event.getMessage();
        String sender = event.getPlayer().getDisplayName();
        String text = this.editString(message);
        Iterator var5 = Bukkit.getOnlinePlayers().iterator();

        while(var5.hasNext()) {
            Player player = (Player)var5.next();
            player.sendMessage(sender + ": " + text);
        }

    }

    @EventHandler
    public void OnSignEdit(SignChangeEvent event) {
        event.setCancelled(true);
        event.setLine(0, this.editString(event.getLine(0) != null ? event.getLine(0) : ""));
        event.setLine(1, this.editString(event.getLine(1) != null ? event.getLine(1) : ""));
        event.setLine(2, this.editString(event.getLine(2) != null ? event.getLine(2) : ""));
        event.setLine(3, this.editString(event.getLine(3) != null ? event.getLine(3) : ""));
    }

    @EventHandler
    public void OnAnvilEdit(PrepareAnvilEvent event) {
        ItemStack item = event.getResult();
        ItemMeta meta = event.getResult().getItemMeta();
        meta.setDisplayName(this.editString(meta.getDisplayName()));
        item.setItemMeta(meta);
        event.setResult(item);
    }

    private String editString(String text) {
        List<String> censored = new ArrayList();
        if (this.plugin.getConfig().getBoolean("censor-words")) {
            censored = this.plugin.getConfig().getStringList("censored-words");
        }

        String[] words = text.split(" ");

        for(int i = 0; i < words.length; ++i) {
            for(int x = 0; x < ((List)censored).size(); ++x) {
                if (words[i].equals(((List)censored).get(x))) {
                    words[i] = words[i].replaceAll("\\B\\w\\B", "*");
                }
            }
        }

        return String.join(" ", words);
    }

}
