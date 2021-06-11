package de.cron3x.cor3.commands;

import de.cron3x.cor3.Cor3;
import net.md_5.bungee.api.ChatColor;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;

public class StatusCommand implements TabExecutor, Listener {
     /*
    *
    * TODO:
    *   register in plugin.yml
    *   ---------------
    *   Status Command;
    *   Select Ui
    *   ---------------
    *   with out Argument - open ui
    *    Menu - (Building) , Admin , Custom -> Anvil , Survival
    *
     */

    private final String GUI_NAME = "Change Status";

    public void openGUI(Player player){
        Inventory inventory = Bukkit.createInventory(null, 9*1, GUI_NAME);

        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(ChatColor.RESET+"" + ChatColor.WHITE+"Survival");
        item.setItemMeta(itemMeta);
        inventory.setItem(0, item);

        itemMeta.setDisplayName(ChatColor.RESET+"" + ChatColor.WHITE+"Creative");
        item.setType(Material.GRASS_BLOCK);
        item.setItemMeta(itemMeta);
        inventory.setItem(1, item);

        itemMeta.setDisplayName(ChatColor.RESET+"" + ChatColor.WHITE+"Custom");
        item.setType(Material.NAME_TAG);
        item.setItemMeta(itemMeta);
        inventory.setItem(8, item);

        player.openInventory(inventory);
    }
    public void openCustomGUI(Player player){
        new AnvilGUI.Builder()
                .open(player);
    }

    @EventHandler
    public void handleGuiClick(InventoryClickEvent event){
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase(GUI_NAME)){
            if (event.getCurrentItem().getItemMeta() == null) return;
            Bukkit.broadcastMessage(event.getCurrentItem().getItemMeta().getDisplayName().substring(1).toLowerCase());
            switch (event.getCurrentItem().getItemMeta().getDisplayName().substring(1).toLowerCase()){
                case "fsurvival":
                    Bukkit.broadcastMessage("Survival");
                    changePrefix(player, "survival", ChatColor.WHITE+"Survival | ");
                    break;
                case "fcreative":
                    Bukkit.broadcastMessage("Creative");
                    changePrefix(player, "creative", ChatColor.BLUE+"Creative | ");
                    break;
                case "fcustom":
                    Bukkit.broadcastMessage("Custom");

                    event.setCancelled(true);
                    event.getClickedInventory().close();
                    openCustomGUI(player);
                    //open Custom Name Inv (text input)
                    break;
                default:
                    break;
            }

            event.setCancelled(true);
            event.getClickedInventory().close();
        }
    }

    public void changePrefix(Player player, String name,String prefix){
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = null;

        if (scoreboard.getTeam(name) == null) {
            scoreboard.registerNewTeam(name);
        }
        team = scoreboard.getTeam(name);

        team.setPrefix(prefix);

        team.addPlayer(player);
    }




























    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length==0){
                openGUI(player);
            }
            if (args.length==1){
                if (args[0].equalsIgnoreCase("admin") && player.isOp()){
                    Bukkit.broadcastMessage("admin");
                }
                if (args[0].equalsIgnoreCase("build")){
                    Bukkit.broadcastMessage("build");
                }
                if (args[0].equalsIgnoreCase("survival")){
                    Bukkit.broadcastMessage("survival");
                }
                if (args[0].equalsIgnoreCase("custom")){
                    Bukkit.broadcastMessage("custom");
                    Inventory Inv = Bukkit.createInventory(player, InventoryType.ANVIL, ChatColor.GOLD + "Custom Anvil");
                    for (int i = 0; i < Inv.getSize(); i++) {
                            ItemStack air = new ItemStack(Material.AIR,1);
                            Inv.setItem(i, air);
                    }
                    player.openInventory(Inv);

                }
                if (args[0].equalsIgnoreCase("ui")){
                    Bukkit.broadcastMessage("ui");
                }
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            Cor3.getInstance().log("len 3");
            ArrayList<String> subcommandsArguments = new ArrayList<>();
            subcommandsArguments.add("admin");
            subcommandsArguments.add("build");
            subcommandsArguments.add("survival");
            subcommandsArguments.add("custom");
            subcommandsArguments.add("ui");
            return subcommandsArguments;
        }
    return null;
    }

}