package de.cron3x.cor3.events;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Locale;

public class KillMessages implements Listener {
    @EventHandler
    public static void onPlayerKill(PlayerDeathEvent e){
        //e.getEntity().getPlayer().sendMessage("TOLL");
        if (e.getEntity().getKiller() != null) {
            Player killer = e.getEntity().getKiller().getPlayer();
            Player player = e.getEntity().getPlayer();
            if (killer != null && player != null){
                ItemStack item = killer.getActiveItem();
                if(item != null){
                    String[] pickaxes = {"diamond_pickaxe","golden_pickaxe","iron_pickaxe","stone_pickaxe","netherite_pickaxe","wooden_pickaxe","minecraft:diamond_pickaxe","minecraft:golden_pickaxe","minecraft:iron_pickaxe","minecraft:stone_pickaxe","minecraft:netherite_pickaxe","minecraft:wooden_pickaxe"};
                    //player.sendMessage(item.toString().toLowerCase());
                    if(Arrays.asList(pickaxes).contains(killer.getInventory().getItemInMainHand().getType().name().toLowerCase(Locale.ROOT))){
                        e.setDeathMessage("");
                        for(Player p : Bukkit.getOnlinePlayers()){
                            if(p.locale().toString().equalsIgnoreCase("de_de")){ p.sendMessage(ChatColor.LIGHT_PURPLE + player.getName()+ChatColor.WHITE+" wurde von "+ChatColor.LIGHT_PURPLE+killer.getName()+ChatColor.WHITE+" abgebaut");}
                            else {//p.sendMessage(ChatColor.LIGHT_PURPLE + player.getName()+" was broke down by "+killer.getName());
                                 p.sendMessage(ChatColor.LIGHT_PURPLE + player.getName()+" wurde von "+killer.getName()+" abgebaut");}
                        }
                    }
                }
            }
        }
    }
}
