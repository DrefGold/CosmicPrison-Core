package me.cosmic.prison.org.Listeners;

import me.cosmic.prison.org.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.material.Coal;


public class BreakEvent implements Listener {


    private Main plugin;

    public BreakEvent(Main plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onIron(BlockBreakEvent e){
        Player p = e.getPlayer();
        int iron = plugin.getData().getConfig().getInt("Blocchi." + p.getName() + ".iron", 0);

        if (e.getBlock().getType().equals(Material.IRON_ORE)) {
            e.setCancelled(true);

            if (plugin.getData().getConfig().getInt("Blocchi." + p.getName() + ".coal") > 100) {
                e.getBlock().setType(Material.STONE);
                e.getBlock().getDrops().clear();
                p.getInventory().addItem(plugin.getUtils().ferro());
                p.updateInventory();

                plugin.getData().getConfig().set("Blocchi." + p.getName() + ".iron", iron+1); //si
                plugin.getData().saveConfig();
                plugin.getData().reloadConfig();

                Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                    @Override
                    public void run() {
                        e.getBlock().setType(Material.IRON_ORE);
                    }
                }, 80L);
            } else {
                p.sendMessage(plugin.getUtils().text(plugin.getConfig().getString("InsufficientToIron")));
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onCoal(BlockBreakEvent e) {
        Player p = e.getPlayer();
        int coal = plugin.getData().getConfig().getInt("Blocchi." + p.getName() + ".coal", 0);
        if (e.getBlock().getType().equals(Material.STONE)) {
            e.setCancelled(true);
        }
        if (e.getBlock().getType().equals(Material.COAL_ORE)) {
            e.setCancelled(true);
            e.getBlock().setType(Material.STONE);
            e.getBlock().getDrops().clear();

            p.getInventory().addItem(plugin.getUtils().block());
            p.updateInventory();

            plugin.getData().getConfig().set("Blocchi." + p.getName() + ".coal", coal+1);
            plugin.getData().saveConfig();
            plugin.getData().reloadConfig();

            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                @Override
                public void run() {
                    e.getBlock().setType(Material.COAL_ORE);
                }
            }, 80L);
        }
    }
}

