package de.cron3x.cor3.events;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class OnPlaceBlock implements Listener {
    @EventHandler
    public void onSpawnArmorStand(EntitySpawnEvent e){
        if (!(e.getEntityType().equals(EntityType.ARMOR_STAND))) return;
        ArmorStand armorStand = (ArmorStand) e.getEntity();
        armorStand.setArms(true);

    }
}
