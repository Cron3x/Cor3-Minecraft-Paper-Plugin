package de.cron3x.cor3.commands.subcommands.cor3tools;

import de.cron3x.cor3.Cor3;
import de.cron3x.cor3.commands.Subcommand;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.List;

public class ServerUsage extends Subcommand {
    @Override
    public String getDisplayName() {
        return "Server Usage: ";
    }

    @Override
    public String getName() {
        return "server_usage";
    }

    @Override
    public String getDescription() {
        return "shows server usage";
    }

    @Override
    public String getSyntax() {
        return "/cor3-tools server_usage";
    }

    @Override
    public void perform(Player player, String[] args) {
            ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
            for(Long threadID : threadMXBean.getAllThreadIds()) {
                ThreadInfo info = threadMXBean.getThreadInfo(threadID);
                player.sendMessage("---------");
                player.sendMessage("Thread name: " + info.getThreadName());
                player.sendMessage("Thread State: " + info.getThreadState());
                player.sendMessage(String.format("CPU time: %s ns", threadMXBean.getThreadCpuTime(threadID)));
                player.sendMessage(String.format("Thread Count: %s", threadMXBean.getThreadCount()));
                player.sendMessage("---------");
        }
    }

    @Override
    public List<String> getSubcomandArguments(Player player, String[] args) {
        return null;
    }
}
