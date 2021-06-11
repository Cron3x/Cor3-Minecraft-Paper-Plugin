package de.cron3x.cor3;

import de.cron3x.cor3.commands.Cor3ToolsManager;
import de.cron3x.cor3.commands.LeaveCommand;
import de.cron3x.cor3.commands.StatusCommand;
import de.cron3x.cor3.commands.subcommands.cor3tools.rlcCommand;
import de.cron3x.cor3.events.*;
import de.cron3x.cor3.recipes.RingRecipe;
import de.cron3x.cor3.recipes.TeleportBlockRecipe;
import de.cron3x.cor3.recipes.creative_fly.CreativeFlyWingRecipe;
import de.cron3x.cor3.recipes.creative_fly.CreativeFlyCoreRecipe;
import de.cron3x.cor3.recipes.creative_fly.CreativeFlyRingRecipe;
import de.cron3x.cor3.recipes.elytras.DiamondElytra;
import de.cron3x.cor3.recipes.elytras.GoldenElytra;
import de.cron3x.cor3.recipes.elytras.IronElytra;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Cor3 extends JavaPlugin {
    private static Cor3 instance;
    public static String PREFIX = "§7§lCor§6§l3§l §7@>§f§o ";
    public static boolean LOG = false;

    @Override
    public void onEnable() {
        instance = this;
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        log("Plugin Successfully loaded");
        regEvents();
        regCommands();
        regRecipes();
    }

    @Override
    public void onDisable() {

    }

    public void log(String text){
        if (LOG) {Bukkit.getConsoleSender().sendMessage(PREFIX + text);}
    }

    private void regEvents(){
        getServer().getPluginManager().registerEvents(new QuitMessage(), this);
        getServer().getPluginManager().registerEvents(new WelcomeMessage(), this);
        getServer().getPluginManager().registerEvents(new KillMessages(), this);
        getServer().getPluginManager().registerEvents(new SleepEvent(), this);
        getServer().getPluginManager().registerEvents(new OnPlaceBlock(), this);
        getServer().getPluginManager().registerEvents(new InvChageEvent(), this);
        getServer().getPluginManager().registerEvents(new HigherEnchants(), this);

        getServer().getPluginManager().registerEvents(new StatusCommand(), this);

        //CUSTOM ITEMS
        getServer().getPluginManager().registerEvents(new TeleportBlockRecipe(), this);
        getServer().getPluginManager().registerEvents(new CreativeFlyCoreRecipe(), this);
        getServer().getPluginManager().registerEvents(new CreativeFlyRingRecipe(), this);
        getServer().getPluginManager().registerEvents(new CreativeFlyRingRecipe(), this);
        getServer().getPluginManager().registerEvents(new RingRecipe(), this);

        log("registered");
    }
    private void regCommands(){
        Objects.requireNonNull(getCommand("cor3-tools")).setExecutor(new Cor3ToolsManager());
        Objects.requireNonNull(getCommand("leave")).setExecutor(new LeaveCommand());
        Objects.requireNonNull(getCommand("status")).setExecutor(new StatusCommand());
        Objects.requireNonNull(getCommand("rlc")).setExecutor(new rlcCommand());
    }
    public void regRecipes(){
        Bukkit.addRecipe(new TeleportBlockRecipe().getRecipe());
        Bukkit.addRecipe(new CreativeFlyCoreRecipe().getRecipe());
        Bukkit.addRecipe(new CreativeFlyRingRecipe().getRecipe());
        Bukkit.addRecipe(new CreativeFlyWingRecipe().getRecipe());
        Bukkit.addRecipe(new RingRecipe().getRecipe());
        Bukkit.addRecipe(new DiamondElytra().getRecipe());
        Bukkit.addRecipe(new IronElytra().getRecipe());
        Bukkit.addRecipe(new GoldenElytra().getRecipe());
    }

    public static Cor3 getInstance(){
        return instance;
    }
}
