package de.cron3x.cor3.arena.commands;

import de.cron3x.cor3.arena.Init;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ChallengeCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        assert sender instanceof Player;
        Player player = (Player) sender;
        if (sender.hasPermission("challange")){
            if(args.length > 0){
                if(args[0].equalsIgnoreCase("invite") || args[0].equalsIgnoreCase("i") || args[0].equalsIgnoreCase("inv")){
                    //invite to fight
                    /*


                    ╔════════════════════════════════════════════════════════════════════╗
                    ║   on invite register new temp team called like the player + _team ═╣
                    ║   on enemy accept, enemy join team                                 ║
                    ║                                                                    ║
                    ║   proceed                                                          ║
                    ║                                                                    ║
                    ║   on start setup everything                                        ║
                    ║                                                                    ║
                    ║                                                                    ║
                    ║   settings open window                                             ║
                    ║    - round count                                                   ║
                    ║    - equipment                                                     ║
                    ║                                                                    ║
                    ║                                                                    ║
                    ║                                                                    ║
                    ╠════════════════════════════════════════════════════════════════════╣
                    ║                                                                    ║
                    ║                                                                    ║
                    ╠═>    save team in Hash map?                                      <═╣
                    ║                                                                    ║
                    ║                                                                    ║
                    ║                                                                    ║
                    ║                                                                    ║
                    ║                                                                    ║
                    ╚════════════════════════════════════════════════════════════════════╝



                    * */
                }
                if (args[0].equalsIgnoreCase("start") || args[0].equalsIgnoreCase("s")){
                    //tp to Arena, begin war
                    new Init().setup(player, player);
                }
                if (args[0].equalsIgnoreCase("accept") || args[0].equalsIgnoreCase("a")){
                }
                if (args[0].equalsIgnoreCase("settings") || args[0].equalsIgnoreCase("options") || args[0].equalsIgnoreCase("o")){
                    //settings of the match
                }
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return null;
    }
}
