package de.cron3x.cor3.commands.subcommands.cor3tools;

import de.cron3x.cor3.commands.Subcommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SaveInv extends Subcommand {

    private final HashMap<String, ItemStack[]> savedInventory = new HashMap<>();

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public String getName() {
        return "inv";
    }

    @Override
    public String getDescription() {
        return "saves the Inventory";
    }

    @Override
    public String getSyntax() {
        return "/cor3-tools inv <save/load>";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args[1].equalsIgnoreCase("save")){
            savedInventory.put(player.getName(), player.getInventory().getContents());
            player.getInventory().clear();
            player.sendMessage("§aInventory Saved");
        }else if (args[1].equalsIgnoreCase("load")){
            if(savedInventory.containsKey(player.getName())){
                player.getInventory().setContents(savedInventory.get(player.getName()));
                player.sendMessage("§bInventory loaded");
            }else {
                player.sendMessage("§cfirst save your inventory: /cor3-tools inv save");
            }
        }else {
            player.sendMessage("§cuse: "+ getSyntax());
        }
    }

    @Override
    public List<String> getSubcomandArguments(Player player, String[] args) {
        if(args.length == 2){
            List<String> options = new ArrayList<>();
            options.add("save");
            options.add("load");
            return options;
        }
        return null;
    }
}
