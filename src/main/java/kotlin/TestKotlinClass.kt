package de.cron3x.cor3.kotlin

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent


class TestKotlinClass : Listener{
    @EventHandler
    fun TestKotlinClass(event: PlayerJoinEvent) {
        event.player.sendMessage("Welcome I'm Kotlin")
    }
}