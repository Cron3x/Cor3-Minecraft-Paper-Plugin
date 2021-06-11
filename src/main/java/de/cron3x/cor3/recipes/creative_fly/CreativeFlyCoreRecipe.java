package de.cron3x.cor3.recipes.creative_fly;

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

public class CreativeFlyCoreRecipe implements Listener {
    public ShapedRecipe getRecipe(){


        ItemStack item = new ItemStack(Material.STRUCTURE_BLOCK);
        ItemMeta meta = item.getItemMeta();

        meta.setCustomModelData(10);
        meta.setDisplayName(ChatColor.GOLD + "Creative Fly Core");
        meta.setUnbreakable(true);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(Cor3.getInstance(), "creative_fly_core");
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(
                "bgb",
                "dsd",
                "nen"
        );
        recipe.setIngredient('b', Material.FEATHER);
        recipe.setIngredient('g', Material.GOLD_INGOT);
        recipe.setIngredient('s', Material.NETHER_STAR);
        recipe.setIngredient('d', Material.DRAGON_BREATH);
        recipe.setIngredient('e', Material.ELYTRA);
        recipe.setIngredient('n', Material.NETHERITE_INGOT);

        return recipe;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        NamespacedKey key = new NamespacedKey(Cor3.getInstance(), "creative_fly_core");
        e.getPlayer().discoverRecipe(key);//<your namespace key for your recipe>
    }
}
