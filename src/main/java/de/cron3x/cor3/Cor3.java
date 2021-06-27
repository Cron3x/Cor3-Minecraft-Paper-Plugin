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
import de.cron3x.cor3.selenium.GetDependencies;
import de.cron3x.cor3.selenium.LaunchChrome;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class Cor3 extends JavaPlugin {
    private static Cor3 instance;
    private File config = new File("plugins//Cor3//config.yml");
    private YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(config);

    public static String WEB_DRIVER_PATH = "";
    public static String BINARY_PATH = "";
    public static String PREFIX = "§7§lCor§6§l3§l §7@>§f§o ";
    public static boolean LOG = true;

    @Override
    public void onEnable() {
        instance = this;

        Bukkit.getConsoleSender().sendMessage(PREFIX + GetConfigYML("Test"));
        Bukkit.getConsoleSender().sendMessage(PREFIX + System.getProperty("os.name"));

        Bukkit.getConsoleSender().sendMessage(PREFIX + "Plugin Successfully loaded");
        regEvents();
        regCommands();
        regRecipes();
        regRunnables();
    }

    @Override
    public void onDisable() {

    }

    public void log(String text){
        if (LOG) {Bukkit.getConsoleSender().sendMessage(PREFIX + text);}
    }

    public void SetConfigYML(String key, Object value){
            yamlConfiguration.set(key, value);
            try {
                yamlConfiguration.save(this.config);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
    }
    public String GetConfigYML(String key){
        return yamlConfiguration.getString(key);
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
        
    }
    private void regCommands(){
        Objects.requireNonNull(getCommand("cor3-tools")).setExecutor(new Cor3ToolsManager());
        Objects.requireNonNull(getCommand("leave")).setExecutor(new LeaveCommand());
        Objects.requireNonNull(getCommand("status")).setExecutor(new StatusCommand());
        Objects.requireNonNull(getCommand("rlc")).setExecutor(new rlcCommand());

        //kotlin
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

    private void regRunnables() {
        regSeleniumDownloadRunnable();
    }

    private void regSeleniumDownloadRunnable(){

        //https://download-chromium.appspot.com/dl/Win_x64?type=snapshots

        String link = "";
        String binary_path = "";

        if (System.getProperty("os.name").equalsIgnoreCase("windows 10")){
            link =  "https://download-chromium.appspot.com/dl/Win_x64?type=snapshots";
        } else {
            link =  "https://download-chromium.appspot.com/dl/Linux_x64?type=snapshots";
        }

        File out = new File("plugins//Cor3//selenium_dependencies//chromebrowser//"+System.getProperty("os.name").toLowerCase().replace(" ", "_")+"//chrome.zip");

        BINARY_PATH = out.getParentFile().toString();
        new GetDependencies(link, out);


        if(Cor3.getInstance().GetConfigYML("SeleniumVersion") == null){
            Cor3.getInstance().SetConfigYML("SeleniumVersion", "92.0.4515.43");
        }
        if (System.getProperty("os.name").equalsIgnoreCase("windows 10")){
            link =  "https://chromedriver.storage.googleapis.com/"+Cor3.getInstance().GetConfigYML("SeleniumVersion")+"/chromedriver_win32.zip";
        } else if (System.getProperty("os.name").equalsIgnoreCase("nix") || System.getProperty("os.name").equalsIgnoreCase("nux") ||System.getProperty("os.name").equalsIgnoreCase("aix") ||System.getProperty("os.name").equalsIgnoreCase("linux")){
            link =  "https://chromedriver.storage.googleapis.com/"+Cor3.getInstance().GetConfigYML("SeleniumVersion")+"/chromedriver_linux64.zip";
        }else if (System.getProperty("os.name").equalsIgnoreCase("mac")){
            link =  "https://chromedriver.storage.googleapis.com/"+Cor3.getInstance().GetConfigYML("SeleniumVersion")+"/chromedriver_mac4.zip";
        }
        else {
            SetConfigYML("os", System.getProperty("os.name"));
        }

        out = new File("plugins//Cor3//selenium_dependencies//chromedriver//"+System.getProperty("os.name").toLowerCase().replace(" ", "_")+"//"+Cor3.getInstance().GetConfigYML("SeleniumVersion").replace(".","_")+"//chromedriver.zip");

        WEB_DRIVER_PATH = out.getParentFile().toString();

        new GetDependencies(link, out);
    }

    public static Cor3 getInstance(){
        return instance;
    }
}
