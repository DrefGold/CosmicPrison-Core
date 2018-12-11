package me.cosmic.prison.org.Staff;

import me.cosmic.prison.org.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.UUID;

public class Staff implements CommandExecutor, Listener {
    public static ArrayList<UUID> freeze = new ArrayList();

    Main plugin;
    public Staff(Main plugin){this.plugin = plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (cmd.getName().equalsIgnoreCase("freeze") && sender.hasPermission("cosmic.admin") && sender instanceof Player){
            Player p = (Player) sender;
            Player t = Bukkit.getPlayer(args[0]);
            if (args[0] == null){
                p.sendMessage("Target can't be null");
            }
            if (args[0] == t.getName()){
                if (freeze.contains(t.getUniqueId())){
                    freeze.remove(t.getUniqueId());
                }else{
                    freeze.add(t.getUniqueId());
                }
            }
        }
        return false;
    }@EventHandler
    public void onFreeze(BlockBreakEvent e){
        if (freeze.contains(e.getPlayer().getUniqueId())){
            e.setCancelled(true);
        }
    }
}
