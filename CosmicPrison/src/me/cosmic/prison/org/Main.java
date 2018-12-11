package me.cosmic.prison.org;

import me.cosmic.prison.org.Listeners.BreakEvent;
import me.cosmic.prison.org.Listeners.JoinEvent;
import me.cosmic.prison.org.Staff.Staff;
import me.cosmic.prison.org.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;

public class Main extends JavaPlugin {

    private static Main plugin;

    private Utils utils;

    private FileManager userdata;

    public void onEnable(){
        plugin = this;
        System.out.println("CosmicPrison enabled");
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        registerEvents();
        userdata = new FileManager(getDataFolder(), "userdata.yml");
        registerIstances();

    }
    private void registerIstances(){
        utils = new Utils(this);
    }
    public void registerEvents(){
            PluginManager pm = Bukkit.getPluginManager();

            pm.registerEvents(new BreakEvent(this), this);
            pm.registerEvents(new JoinEvent(this), this);
        }
    public void registerCommands(){
        getCommand("cosmic").setExecutor(this);
    }
    public Utils getUtils() {
        return utils;
    }
    public FileManager getData(){
        return userdata;
    }

    public static Main getInstance(){
        return plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Comando eseguibile solo da gioco");
        }
        if (args.length != 1){
            sender.sendMessage("§cUsa /cosmic reload");
        }
        if (cmd.getName().equalsIgnoreCase("cosmic")){
            if (args.length == 1 && args[0].equalsIgnoreCase("reload") && sender.hasPermission("cosmic.reload")){
                userdata.reloadConfig();
                userdata.saveConfig();
                reloadConfig();
                saveConfig();
                Bukkit.getLogger().info("§8[§6Cosmic§bCore§8] §cRicaricato con successo");
                sender.sendMessage("§8[§6Cosmic§bCore§8] §cRicaricato con successo");
            }
        }
        return true;
    }
}
