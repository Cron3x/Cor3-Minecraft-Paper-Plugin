package de.cron3x.cor3.event.lib;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

public class InventoryChangeEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private Player user;

    public InventoryChangeEvent(Player user){
        this.user = user;
    }

    public Player getUser() {
        return user;
    }

    public HandlerList getHandlers(){
        return handlers;
    }
    public static HandlerList getHandlerList(){
        return handlers;
    }
}
