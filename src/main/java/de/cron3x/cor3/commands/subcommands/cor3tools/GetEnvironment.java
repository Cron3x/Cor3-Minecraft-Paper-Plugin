package de.cron3x.cor3.commands.subcommands.cor3tools;

import de.cron3x.cor3.commands.Subcommand;
import org.bukkit.entity.Player;

import java.util.List;

public class GetEnvironment extends Subcommand {
    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public String getName() {
        return "get_dimension";
    }

    @Override
    public String getDescription() {
        return "list your Dimension";
    }

    @Override
    public String getSyntax() {
        return "/core-tools get_dimension";
    }

    @Override
    public void perform(Player player, String[] args) {
        player.sendMessage(String.valueOf(player.getWorld().getEnvironment()));
    }

    @Override
    public List<String> getSubcomandArguments(Player player, String[] args) {
        return null;
    }
}
