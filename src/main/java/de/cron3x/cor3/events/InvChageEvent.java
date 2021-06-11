package de.cron3x.cor3.events;

import de.cron3x.cor3.recipes.creative_fly.CreativeFlyRingRecipe;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InvChageEvent implements Listener {

    private ItemStack fly_ring = new CreativeFlyRingRecipe().getRecipe().getResult();

    @EventHandler
    public void onSwapHandEvent(PlayerSwapHandItemsEvent e){
        Player p = e.getPlayer();

        if (e.getOffHandItem().hasItemMeta() && e.getOffHandItem().getItemMeta().removeAttributeModifier(Attribute.GENERIC_MAX_HEALTH) == fly_ring.getItemMeta().removeAttributeModifier(Attribute.GENERIC_MAX_HEALTH)){
            p.setAllowFlight(true);
        }
        else if(p.getAllowFlight() == true && p.getGameMode().equals(GameMode.SURVIVAL)){
            p.setAllowFlight(false);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        Inventory inv = e.getClickedInventory();
        if (inv != null && inv.getType().equals(InventoryType.PLAYER)) {
            Player p = (Player) e.getWhoClicked();

            if (p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().removeAttributeModifier(Attribute.GENERIC_MAX_HEALTH) == fly_ring.getItemMeta().removeAttributeModifier(Attribute.GENERIC_MAX_HEALTH)){
                p.setAllowFlight(true);
            }
            else if(p.getAllowFlight() == true && p.getGameMode().equals(GameMode.SURVIVAL)){
                p.setAllowFlight(false);
            }
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        Player p = e.getPlayer();
        if (p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().removeAttributeModifier(Attribute.GENERIC_MAX_HEALTH) == fly_ring.getItemMeta().removeAttributeModifier(Attribute.GENERIC_MAX_HEALTH)){
            p.setAllowFlight(true);
        }
        else if(p.getAllowFlight() == true && p.getGameMode().equals(GameMode.SURVIVAL)){
            p.setAllowFlight(false);
        }
    }
}
