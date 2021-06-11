package de.cron3x.cor3.kotlin

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent


class TestKotlinClass : Listener{
    @EventHandler
    fun TestKotlinClass(event: PlayerJoinEvent) {
        val onPlayers = Bukkit.getOnlinePlayers();
        for (player in onPlayers){
            player.sendMessage("Hallo Du!")
        }
    }
}