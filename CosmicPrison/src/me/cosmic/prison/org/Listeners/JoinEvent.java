package me.cosmic.prison.org.Listeners;

import me.cosmic.prison.org.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.lang.reflect.Constructor;

public class JoinEvent implements Listener {
    Main plugin;
    public JoinEvent(Main plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (p.hasPlayedBefore() == false) {
            p.sendMessage(plugin.getUtils().text(plugin.getConfig().getString("WelcomeMessage").replaceAll("%player%", p.getName())));

        } else {
            if (p.hasPlayedBefore() == true) {
                p.sendMessage(plugin.getUtils().text(plugin.getConfig().getString("WelcomeBackMessage").replaceAll("%player%", p.getName())));
            }
        }
    }
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        for (String word : e.getMessage().split(" ")) {
            if (plugin.getConfig().getStringList("badwords").contains(word)) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(plugin.getUtils().text(plugin.getConfig().getString("AntiCurseMess")));
            }
        }
    }
}
