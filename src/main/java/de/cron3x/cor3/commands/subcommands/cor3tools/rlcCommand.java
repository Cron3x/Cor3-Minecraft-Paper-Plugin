package de.cron3x.cor3.commands.subcommands.cor3tools;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class rlcCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.isOp()){
            Bukkit.getServer().reload();
            Bukkit.broadcastMessage(ChatColor.GREEN+"Server reloaded \n Reload complete");
        }
        return false;
    }
}
