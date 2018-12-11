package me.cosmic.prison.org.Utils;

import me.cosmic.prison.org.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Utils {

    private Main plugin;
    public Utils(Main plugin){
        this.plugin = plugin;
    }

    public String text(String text){
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    public ItemStack block(){
        ItemStack coal = new ItemStack(Material.COAL);
        ItemMeta meta = coal.getItemMeta();
        coal.setItemMeta(meta);
        return coal;
    }
    public ItemStack ferro(){
        ItemStack iron = new ItemStack(Material.IRON_INGOT);
        ItemMeta meta = iron.getItemMeta();
        iron.setItemMeta(meta);
        return iron;
    }
    public ItemStack redstone(){
        ItemStack redst = new ItemStack(Material.REDSTONE);
        ItemMeta metaa = redst.getItemMeta();
        redst.setItemMeta(metaa);
        return redst;
    }
    public ItemStack paperino(){
        ItemStack paper = new ItemStack(Material.PAPER, 1);
        ItemMeta papermeta = paper.getItemMeta();
        papermeta.setDisplayName(plugin.getUtils().text("&c&lBenvenuto nel nostro &bCosmic&9Prison"));
        papermeta.setLore(Arrays.asList((plugin.getUtils().text("&7Qui, imparerai a giocare"))));
        papermeta.setLore(Arrays.asList(plugin.getUtils().text("&7nella nostra modalità.")));

        return paperino();
    }
}
