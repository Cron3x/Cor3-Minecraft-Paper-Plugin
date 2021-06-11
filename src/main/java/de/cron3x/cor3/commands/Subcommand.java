package de.cron3x.cor3.commands;

import org.bukkit.entity.Player;

import java.util.List;

public abstract class Subcommand {

    public abstract String getDisplayName();

    public abstract String getName();

    public abstract String getDescription();

    public abstract String getSyntax();

    public abstract void perform(Player player, String[] args);

    public abstract List<String> getSubcomandArguments(Player player, String[] args);

}
