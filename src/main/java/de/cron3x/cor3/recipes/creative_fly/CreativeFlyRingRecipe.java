package de.cron3x.cor3.recipes.creative_fly;

import de.cron3x.cor3.Cor3;
import de.cron3x.cor3.recipes.RingRecipe;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;
import java.util.UUID;

public class CreativeFlyRingRecipe implements Listener {
    public ShapedRecipe getRecipe(){

        ItemStack wing = new ItemStack(Material.STRUCTURE_BLOCK);
        ItemMeta wing_meta = wing.getItemMeta();
        wing_meta.setCustomModelData(100);
        wing_meta.setUnbreakable(true);
        wing_meta.setDisplayName(ChatColor.GOLD + "Creative Fly Wing");
        wing.setItemMeta(wing_meta);

        ItemStack core = new ItemStack(Material.STRUCTURE_BLOCK);
        ItemMeta core_meta = core.getItemMeta();
        core_meta.setCustomModelData(10);
        core_meta.setUnbreakable(true);
        core_meta.setDisplayName(ChatColor.GOLD + "Creative Fly Core");
        core.setItemMeta(core_meta);

        ItemStack ring = new ItemStack(Material.STRUCTURE_BLOCK);
        ItemMeta ring_meta = ring.getItemMeta();
        ring_meta.setCustomModelData(1);
        ring_meta.setUnbreakable(true);
        ring_meta.setDisplayName(ChatColor.WHITE + "Golden Ring");
        ring.setItemMeta(ring_meta);

        ItemStack item = new ItemStack(Material.STRUCTURE_BLOCK);
        ItemMeta meta = item.getItemMeta();

        meta.setCustomModelData(1000);
        meta.setDisplayName(ChatColor.BOLD+""+ChatColor.LIGHT_PURPLE+"Creative Fly Ring");
        meta.setUnbreakable(true);
        meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "Health reduction", -10.0, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.OFF_HAND));
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(Cor3.getInstance(), "creative_fly_ring");
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(
                " r ",
                "wcw",
                " r "
        );

        recipe.setIngredient('w', wing);
        recipe.setIngredient('c', core);
        recipe.setIngredient('r', ring);

        return recipe;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        NamespacedKey key = new NamespacedKey(Cor3.getInstance(), "creative_fly_ring");
        e.getPlayer().discoverRecipe(key); //<your namespace key for your recipe>
    }
}