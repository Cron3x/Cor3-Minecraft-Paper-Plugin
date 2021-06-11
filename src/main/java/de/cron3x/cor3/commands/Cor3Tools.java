package de.cron3x.cor3.commands;

import de.cron3x.cor3.Cor3;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.List;

public class Cor3Tools implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.isOp()){
            if(args.length > 0){
                if(args[0].equalsIgnoreCase("consolemessage") || args[0].equalsIgnoreCase("console_message") || args[0].equalsIgnoreCase("debug_console_message")){
                    if (args[1].equalsIgnoreCase("true")){
                        Cor3.LOG = true;
                        return true;
                    }
                    else if (args[1].equalsIgnoreCase("false")){
                        Cor3.LOG = false;
                        return true;
                    }
                }
                if(args[0].equalsIgnoreCase("server_usage") || args[0].equalsIgnoreCase("serverusage")){
                    if (args.length > 1) {
                        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
                        for (Long threadID : threadMXBean.getAllThreadIds()) {
                            ThreadInfo info = threadMXBean.getThreadInfo(threadID);
                            sender.sendMessage("---------");
                            sender.sendMessage("Thread name: " + info.getThreadName());
                            sender.sendMessage("Thread State: " + info.getThreadState());
                            sender.sendMessage(String.format("CPU time: %s ns", threadMXBean.getThreadCpuTime(threadID)));
                            sender.sendMessage(String.format("Thread Count: %s", threadMXBean.getThreadCount()));
                            sender.sendMessage("---------");
                            return true;
                        }
                    }
                    }
                if(args[0].equalsIgnoreCase("permissions")){
                        if(args[1].equalsIgnoreCase("add")){

                        }
                        if(args[1].equalsIgnoreCase("remove")){

                        }
                    }
                else if(args[0].equalsIgnoreCase("h") || args[0].equalsIgnoreCase("help")){
                    sender.sendMessage("valid options are:\n \"debug_console_message\"\n\"server_usage\"\n");
                }
                else sender.sendMessage("valid options are:\n \"debug_console_message\"\n\"server_usage\"\n");
            }else sender.sendMessage("/cor3-tools <FUNCTION> <true/false> (use '/cor3-tools help' to see all options)");
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1){
            List<String> option = new ArrayList<>();
            option.add("debug_console_message");
            option.add("server_usage");
            option.add("help");
            option.add("permissions");
            return option;
        }
        if (args.length == 2){
            //sender.sendMessage(args[0]);
            List<String> option = new ArrayList<>();
            if(args[0].equalsIgnoreCase("debug_console_message")){
                option.add("true");
                option.add("false");
            }if(args[0].equalsIgnoreCase("permissions")){
                option.add("add");
                option.add("remove");
            }else
            {
                option.add("");
            }
            return option;
        }
        if (args.length == 3){
            //sender.sendMessage(args[0]);
            List<String> option = new ArrayList<>();
            if(args[0].equalsIgnoreCase("add")){
                option.add("true");
            }else
            {
                return null;
            }
            return option;
        }
        return null;
    }
}