package de.cron3x.cor3.recipes.elytras;

import de.cron3x.cor3.Cor3;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class IronElytra implements Listener {
    public ShapedRecipe getRecipe(){


        ItemStack item = new ItemStack(Material.ELYTRA);
        ItemMeta meta = item.getItemMeta();

        meta.setCustomModelData(1);
        meta.setDisplayName(ChatColor.WHITE + "Elytra");
        meta.setUnbreakable(true);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(Cor3.getInstance(), "iron_elytra");
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(
                "ggg",
                "geg",
                "ggg"
        );
        recipe.setIngredient('g', Material.IRON_INGOT);
        recipe.setIngredient('e', Material.ELYTRA);



        return recipe;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        NamespacedKey key = new NamespacedKey(Cor3.getInstance(), "iron_elytra");
        e.getPlayer().discoverRecipe(key);//<your namespace key for your recipe>
    }
}
