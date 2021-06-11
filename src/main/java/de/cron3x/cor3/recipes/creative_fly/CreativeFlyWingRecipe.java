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

public class CreativeFlyWingRecipe implements Listener {

    private final String KEY = "creative_fly_wing";

    public ShapedRecipe getRecipe(){

        ItemStack item = new ItemStack(Material.STRUCTURE_BLOCK);
        ItemMeta meta = item.getItemMeta();

        meta.setCustomModelData(100);
        meta.setDisplayName(ChatColor.GOLD + "Creative Fly Wing");
        meta.setUnbreakable(true);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(Cor3.getInstance(), KEY);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(
                " rp",
                "rp ",
                "rp "
        );
        recipe.setIngredient('r', Material.BLAZE_ROD);
        recipe.setIngredient('p', Material.PAPER);



        return recipe;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        NamespacedKey key = new NamespacedKey(Cor3.getInstance(), KEY);
        e.getPlayer().discoverRecipe(key);//<your namespace key for your recipe>
    }
}
