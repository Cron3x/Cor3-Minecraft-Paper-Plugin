package de.cron3x.cor3.commands;

import de.cron3x.cor3.Cor3;
import de.cron3x.cor3.commands.subcommands.cor3tools.*;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Cor3ToolsManager implements TabExecutor {

    private final ArrayList<Subcommand> subcommands = new ArrayList<>();
    public Cor3ToolsManager(){
        subcommands.add(new DebugConsoleMessageCommand());
        subcommands.add(new ServerUsage());
        subcommands.add(new RankCommand());
        subcommands.add(new GetEnvironment());
        subcommands.add(new SaveInv());
        subcommands.add(new StopTasks());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length > 0){
                for(int i = 0; i< getSubcommands().size(); i++){
                    if (args[0].equalsIgnoreCase(getSubcommands().get(i).getName())){
                        getSubcommands().get(i).perform(player, args);
                    }
                }
            }else {
                player.sendMessage(ChatColor.AQUA+"All subcommands: ");
                for (int i = 0; i < getSubcommands().size(); i++){
                    player.sendMessage(ChatColor.GREEN+"  "+getSubcommands().get(i).getSyntax() +" - "+getSubcommands().get(i).getDescription());
                }
            }

        }
        return true;
    }

    public ArrayList<Subcommand> getSubcommands(){
        return subcommands;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        Cor3.getInstance().log("tabcompl");
        if(args.length == 1){
            Cor3.getInstance().log("len 3");
            ArrayList<String> subcommandsArguments = new ArrayList<>();
            for(int i = 0; i< getSubcommands().size(); i++){
                subcommandsArguments.add(getSubcommands().get(i).getName());
            }
            return subcommandsArguments;
        }else if (args.length == 2){
            for(int i = 0; i< getSubcommands().size(); i++) {
                Cor3.getInstance().log("len 2");
                if (args[0].equalsIgnoreCase(getSubcommands().get(i).getName())) {
                    Cor3.getInstance().log("len 2 subcom");
                    return getSubcommands().get(i).getSubcomandArguments((Player) sender, args);
                }

            }
        }else if (args.length == 3){
            Cor3.getInstance().log("sudo");
            for(int i = 0; i< getSubcommands().size(); i++) {
                if (args[0].equalsIgnoreCase(getSubcommands().get(i).getName())) {
                    Cor3.getInstance().log("len 3 subcom");
                    return getSubcommands().get(i).getSubcomandArguments((Player) sender, args);
                }
            }
        }else if (args.length == 4){
            Cor3.getInstance().log("sudo");
            for(int i = 0; i< getSubcommands().size(); i++) {
                if (args[0].equalsIgnoreCase(getSubcommands().get(i).getName())) {
                    Cor3.getInstance().log("len 3 subcom");
                    return getSubcommands().get(i).getSubcomandArguments((Player) sender, args);
                }
            }
        }
        return null;
    }
}
