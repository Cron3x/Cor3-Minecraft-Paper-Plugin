package de.cron3x.cor3.events;

import com.destroystokyo.paper.event.server.ServerTickStartEvent;
import de.cron3x.cor3.Cor3;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class SleepEvent implements Listener {

    public List<Player> sleepingPlayers = new ArrayList<>();
    public List<Player> onlinePlayers = new ArrayList<>();
    boolean running = false;
    Integer count;

    @EventHandler
    public void onSleep (PlayerBedEnterEvent event){
        Player player = event.getPlayer();
        if(event.getPlayer().getWorld().getEnvironment().toString().equalsIgnoreCase("normal")){
            //player.sendMessage("ÄÄÄÄÄ");
            sleepingPlayers.add(player);
            PlayerBedEnterEvent.BedEnterResult resoult =  event.getBedEnterResult();
            //player.sendMessage(resoult+"");
            if (!running && event.getBedEnterResult().toString().equalsIgnoreCase("ok")){
                runnable();
            }
        }
    }

    @EventHandler
    public void onWakeup (PlayerBedLeaveEvent event){
        sleepingPlayers.remove(event.getPlayer());
    }

    public void runnable(){
        count =0;
        running = true;
        onlinePlayers.clear();



        //Bukkit.getServer().broadcastMessage("before start");
        new BukkitRunnable(){
            @Override
            public void run() {
                count ++;
                //Bukkit.getServer().broadcastMessage(count+"");
                for(Player player: Bukkit.getServer().getOnlinePlayers()){
                    if (player.getWorld().getEnvironment().toString().equalsIgnoreCase("normal") && !onlinePlayers.contains(player)){
                        onlinePlayers.add(player);
                    }
                }
                //Bukkit.getServer().broadcastMessage("onlinePlayers: "+onlinePlayers.toArray().length);
                //Bukkit.getServer().broadcastMessage("sleepingPlayers: "+sleepingPlayers.toArray().length);
                if (sleepingPlayers.toArray().length >= onlinePlayers.toArray().length/2 && count >= 20){
                    sleepingPlayers.get(0).getWorld().setTime(0);
                    count = 0;
                    Bukkit.getScheduler().cancelTask(this.getTaskId());
                    running = false;
                }
                if (onlinePlayers.get(0).getWorld().getTime() < 12300 && onlinePlayers.get(0).getWorld().getTime() > 0){
                    count = 0;
                    Bukkit.getScheduler().cancelTask(this.getTaskId());
                    running = false;
                }
                if (sleepingPlayers.toArray().length <= 0){
                    count = 0;
                    //Bukkit.getServer().broadcastMessage("OCH ROLF!");
                    Bukkit.getScheduler().cancelTask(this.getTaskId());
                    running = false;
                }
                if(sleepingPlayers.toArray().length < onlinePlayers.toArray().length/2){
                    count = 0;
                }
            }
        }.runTaskTimer(Cor3.getInstance(), 0, 10);
    }
}
