package de.cron3x.cor3.utils;

import de.cron3x.cor3.Cor3;
import de.cron3x.cor3.recipes.creative_fly.CreativeFlyRingRecipe;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

//TODO: use InventoryChange Event to change the fly mode

public class SurvivalFly{

    public void runnable(){

        new BukkitRunnable(){
            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()){

                    if (p.getPlayer().getInventory().getItemInOffHand().hasItemMeta() && p.getPlayer().getInventory().getItemInOffHand().getItemMeta().removeAttributeModifier(Attribute.GENERIC_MAX_HEALTH) == (new CreativeFlyRingRecipe().getRecipe().getResult().getItemMeta().removeAttributeModifier(Attribute.GENERIC_MAX_HEALTH)) && p.getGameMode().equals(GameMode.SURVIVAL)){
                        p.getPlayer().setAllowFlight(true);
                        p.addScoreboardTag("wing_particle");

                    }else if (p.getGameMode().equals(GameMode.SURVIVAL) && p.getAllowFlight() == true){
                        p.getPlayer().setAllowFlight(false);
                        p.removeScoreboardTag("wing_particle");
                    }
                }
            }
        }.runTaskTimer(Cor3.getInstance(), 0, 25);
    }
}
