package de.cron3x.cor3.commands.subcommands.cor3tools;

import de.cron3x.cor3.Cor3;
import de.cron3x.cor3.commands.Subcommand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DebugConsoleMessageCommand extends Subcommand {
    @Override
    public String getDisplayName() {
        return "Console Debug Message";
    }

    @Override
    public String getName() {
        return "debug_console_message";
    }

    @Override
    public String getDescription() {
        return "shows debug messages in the console";
    }

    @Override
    public String getSyntax() {
        return "/cor3-tools debug_console_message <true | false>";
    }


    @Override
    public void perform(Player player, String[] args) {

        if (args.length > 1){
            if (args[1].equalsIgnoreCase("true")){
                Cor3.LOG = true;
            }
            else if (args[1].equalsIgnoreCase("false")){
                Cor3.LOG = false;
            }else if (args[1].equalsIgnoreCase("status")){
                player.sendMessage(Cor3.LOG+"");
            }
        }
        else if(args.length == 1){
            player.sendMessage("You didn't provide true or false");
        }
    }

    @Override
    public List<String> getSubcomandArguments(Player player, String[] args) {
        if (args.length == 2){
            List<String> options = new ArrayList<>();
            options.add("true");
            options.add("false");
            options.add("status");
            return options;
        }
        return null;
    }
}
