package de.cron3x.cor3.commands.subcommands.cor3tools;

import de.cron3x.cor3.Cor3;
import de.cron3x.cor3.commands.Subcommand;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class RankCommand extends Subcommand{

    Cor3 getInstance = Cor3.getInstance();

    @Override
    public String getDisplayName() {
        return "Rank System";
    }

    @Override
    public String getName() {
        return "rank";
    }

    @Override
    public String getDescription() {
        return "permissions for commands";
    }

    @Override
    public String getSyntax() {
        return "/core-tools rank <player> <rank>";
    }

    @Override
    public void perform(Player player, String[] args) {
        if( player.hasPermission("cor3.admin") || player.isOp()) {
            player.sendMessage(args.length+"");
            if (args.length == 4) {
                player.sendMessage("args[1]." + args[1]);
                if (Bukkit.getPlayer(args[1]) == null) {
                    player.sendMessage(ChatColor.RED + "Player, " + args[1] + " is not Online!");
                }else {
                    Player target = Bukkit.getPlayer(args[1]);
                    player.sendMessage(target.toString());
                    if (args[2].equalsIgnoreCase("add")) {

                    }
                    if (args[2].equalsIgnoreCase("remove")) {

                    }
                    if (args[2].equalsIgnoreCase("list")) {
                    }
                }

            }
            else {
                player.sendMessage("§c args not 4");
            }
        }else {
            player.sendMessage("§c You don't have the permissions to do this command!");
        }
    }

    @Override
    public List<String> getSubcomandArguments(Player player, String[] args) {
        if (args.length == 2){
            List<String> playerNames = new ArrayList<>();
            Player[] players = new Player[Bukkit.getOnlinePlayers().size()];
            Bukkit.getOnlinePlayers().toArray(players);
            for(int i=0; i<players.length; i++){
                playerNames.add(players[i].getName());
            }
            return playerNames;
        }else if (args.length == 3){
            List<String> subcom = new ArrayList<>();
            subcom.add("add");
            subcom.add("remove");
            subcom.add("list");
            return subcom;
        }else if (args.length == 4){
            List<String> subcom = new ArrayList<>();
            subcom.add("List_of_perms");
            return subcom;
        }
        return null;
    }
}
