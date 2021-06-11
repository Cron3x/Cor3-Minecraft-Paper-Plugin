package de.cron3x.cor3.recipes;

import de.cron3x.cor3.Cor3;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class TeleportBlockRecipe implements Listener {
    public ShapedRecipe getRecipe(){


        ItemStack item = new ItemStack(Material.ITEM_FRAME);
        ItemMeta meta = item.getItemMeta();

        meta.setCustomModelData(5);
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Teleporter");
        meta.setUnbreakable(true);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(Cor3.getInstance(), "teleporter");
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(
                "nen",
                "ese",
                "drd"
        );
        recipe.setIngredient('n', Material.NETHERITE_SCRAP);
        recipe.setIngredient('e', Material.ENDER_PEARL);
        recipe.setIngredient('s', Material.NETHER_STAR);
        recipe.setIngredient('d', Material.DIAMOND);
        recipe.setIngredient('r', Material.RESPAWN_ANCHOR);



        return recipe;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        NamespacedKey key = new NamespacedKey(Cor3.getInstance(), "teleporter");
        e.getPlayer().discoverRecipe(key);//<your namespace key for your recipe>
    }
}
