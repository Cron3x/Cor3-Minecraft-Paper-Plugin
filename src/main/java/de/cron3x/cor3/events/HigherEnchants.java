package de.cron3x.cor3.events;

import de.cron3x.cor3.event.lib.InventoryChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class HigherEnchants implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e){

        if (!(e.getWhoClicked() instanceof Player)){
            return;
        }
        Player p = (Player) e.getWhoClicked();
        if(!(e.getInventory().getType().equals(InventoryType.ANVIL))){
            return;
        }
        Inventory inventory = e.getInventory();

        anvilInventory(inventory, p);
    }

    @EventHandler
    public void onDrag(InventoryDragEvent e){
        if (!(e.getWhoClicked() instanceof Player)){
            return;
        }
        Player p = (Player) e.getWhoClicked();
        if(!(e.getInventory().getType().equals(InventoryType.ANVIL))){
            return;
        }
        Inventory inventory = e.getInventory();

        anvilInventory(inventory, p);
    }


    public void anvilInventory(Inventory inv, Player p){
        Bukkit.broadcastMessage("anvilInventory CLASS!");
    }
}
