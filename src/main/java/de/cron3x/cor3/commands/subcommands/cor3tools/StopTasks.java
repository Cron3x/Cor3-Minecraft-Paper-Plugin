package de.cron3x.cor3.commands.subcommands.cor3tools;

import de.cron3x.cor3.Cor3;
import de.cron3x.cor3.commands.Subcommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scheduler.BukkitWorker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;

public class StopTasks extends Subcommand {

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public String getName() {
        return "bukkit_runnables";
    }

    @Override
    public String getDescription() {
        return "stop all runnables";
    }

    @Override
    public String getSyntax() {
        return "/cor3-tools bukkit_runnables <list|(stop)|stopall>";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (player.isOp()){
            if (args[1].equalsIgnoreCase("list")){
                List<BukkitWorker> workers = Bukkit.getScheduler().getActiveWorkers();
                List<BukkitTask> pendingTasks = Bukkit.getScheduler().getPendingTasks();
                Executor mainThreadExecutor = Bukkit.getScheduler().getMainThreadExecutor(Cor3.getInstance());

                Bukkit.broadcastMessage("list:");
                Bukkit.broadcastMessage(workers.toArray().length + " << workers.toArray().length");
                for (int i = 0; workers.toArray().length > i; i++){
                    Bukkit.broadcastMessage(workers.get(i).toString());
                }
                Bukkit.broadcastMessage("\n=============================================\n");
                Bukkit.broadcastMessage(pendingTasks.toArray().length + " << pendingTasks.toArray().length");
                for (int i = 0; pendingTasks.toArray().length > i; i++){
                    Bukkit.broadcastMessage(workers.get(i).toString());
                }
                Bukkit.broadcastMessage("\n=============================================\n");

                Bukkit.broadcastMessage(mainThreadExecutor +"");
                Bukkit.broadcastMessage("^ mainThreadExecutor ^");

            }
            else {
                player.sendMessage("§cuse: "+ getSyntax());
            }
        }else {
            player.sendMessage("§cyour not an operator");
        }
    }

    @Override
    public List<String> getSubcomandArguments(Player player, String[] args) {
        if(args.length == 2){
            List<String> options = new ArrayList<>();
            options.add("stopall");
            options.add("list");
            return options;
        }
        return null;
    }
}
