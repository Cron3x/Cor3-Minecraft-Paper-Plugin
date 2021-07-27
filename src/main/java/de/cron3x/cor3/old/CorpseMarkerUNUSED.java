package de.cron3x.cor3.old;

import de.cron3x.cor3.Cor3;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class CorpseMarkerUNUSED implements Listener {

    private final HashMap<String, ItemStack[]> p_inv = new HashMap<>();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getEntity().getPlayer();
        Location deathloc = player.getLocation();
        Location loc = deathloc;
        int locX = deathloc.getBlockX();
        int locY = deathloc.getBlockY();
        int locZ = deathloc.getBlockZ();
        String dimension = "";
        TextComponent msg = new TextComponent();
        if (player.getWorld().getEnvironment().toString().equalsIgnoreCase("normal")){
            dimension = "Overworld";
        }

        if (player.getWorld().getEnvironment().toString().equalsIgnoreCase("nether")){
            dimension = "Nether";
        }

        if (player.getWorld().getEnvironment().toString().equalsIgnoreCase("the_end")){
            dimension = "End";
        }
        msg.setText("You died here: "+ ChatColor.LIGHT_PURPLE+locX+" "+locY+" "+locZ+ ChatColor.WHITE+" | "+ChatColor.GREEN+dimension);
        msg.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, locX+" "+locY+" "+locZ +" "+dimension));
        msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Copy your death coordinates to the clipboard").create()));
        player.sendMessage(msg);
        if (!player.getInventory().isEmpty()){
            ArmorStand grave_stand = spawnGraveStand(player).get(0);
            p_inv.put(grave_stand.getUniqueId().toString(), player.getInventory().getContents());
            e.getDrops().clear();
            spawnBeacon(player.getPlayer(), player.getLocation().getBlock().getLocation().toBlockLocation(), spawnGraveStand(player));
            graveStone(player.getPlayer(), player.getLocation().getBlock().getLocation().toBlockLocation());
        }

    }

    private void spawnBeacon(Player player, Location location, ArrayList<ArmorStand> stands){

        new BukkitRunnable(){
            int time;
            @Override
            public void run() {
                time++;
                ArmorStand name_stand = stands.get(1);
                name_stand.setCustomName("time: " + 1);
                name_stand.setCustomNameVisible(true);
                for (Player p: Bukkit.getOnlinePlayers()){
                    if(p.getWorld().getEnvironment().equals(location.getWorld().getEnvironment())){
                        if (p.getLocation().distance(location) <= 3 && !p.isDead() || time >= 12000){
                            Bukkit.getScheduler().cancelTask(this.getTaskId());
                            for (ArmorStand stand : stands) {
                                stand.getLocation().setY(-100);
                                stand.teleport(stand.getLocation());
                            }
                        }else if(location.distance(stands.get(0).getLocation()) <= 4){
                            for (int i = 0; i<255; i++){
                                player.spawnParticle(Particle.BARRIER,location.getBlockX(), i, location.getBlockZ(), 1);
                                player.spawnParticle(Particle.CRIT_MAGIC,location.getX(), i-1, location.getZ(), 1);
                                player.spawnParticle(Particle.TOTEM,location.getX(), i-2, location.getZ(), 1);
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(Cor3.getInstance(), 20,20);
    }

    private ArrayList<ArmorStand> spawnGraveStand(Player player){

        ArrayList<ArmorStand> stands = new ArrayList<>();

        Location location = player.getLocation().getBlock().getLocation().toCenterLocation();

        ArmorStand grave_stand = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        grave_stand.setSmall(false);
        grave_stand.setInvulnerable(true);
        grave_stand.addScoreboardTag("grave_stand");
        grave_stand.addScoreboardTag("grave_group");
        grave_stand.setCustomNameVisible(true);
        grave_stand.setCustomName(player.getName() + "'s Grave");
        grave_stand.setInvisible(true);
        stands.add(grave_stand);
        ArmorStand grave_stand_timer = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        grave_stand_timer.setSmall(true);
        grave_stand_timer.setInvulnerable(true);
        grave_stand_timer.addScoreboardTag("grave_timer");
        grave_stand_timer.addScoreboardTag("grave_group");
        grave_stand_timer.setCustomNameVisible(true);
        grave_stand_timer.setCustomName("Ticks remaining: "+ChatColor.GREEN+12000+ChatColor.WHITE+"/"+ChatColor.GREEN+12000);
        grave_stand_timer.setInvisible(true);
        grave_stand_timer.getLocation().setY(-0.5);
        grave_stand_timer.teleport(grave_stand_timer.getLocation());
        stands.add(grave_stand_timer);


        return stands;
    }

    private void graveStone(Player player, Location location){

        Block skullBlock = location.getBlock();
        skullBlock.setType(Material.PLAYER_HEAD);
        BlockState state = skullBlock.getState();
        Skull skull = (Skull) state;
        skull.setOwningPlayer(player);
        skull.update();
    }

    //

    @EventHandler
    public void breakBlock(BlockBreakEvent e){

        if (e.getBlock().getType() == Material.PLAYER_HEAD){
            Location loc = e.getBlock().getLocation();

            Collection<Entity> nearEntitys = loc.getNearbyEntities(5,5,5);

            for (Entity entity : nearEntitys){
                if (entity.getType() == EntityType.ARMOR_STAND && entity.getScoreboardTags().contains("grave_stand")){
                    UUID inv_slot = entity.getUniqueId();
                    BlockState block_data = e.getBlock().getState();
                    Skull head_data = (Skull) block_data;

                    Player owner = (Player) head_data.getOwningPlayer();

                    Player player = e.getPlayer();

                    if (owner != null){
                        Inventory corpseui = Bukkit.createInventory(player, 45, ChatColor.DARK_GRAY + owner.getName() + "'s Grave");

                        corpseui.setContents(p_inv.get(inv_slot.toString()));

                        player.openInventory(corpseui);

                        if (player.getOpenInventory().getType().equals(InventoryType.CHEST)){
                            new BukkitRunnable(){
                                @Override
                                public void run() {
                                    if (corpseui.isEmpty()){
                                        for (Entity ent : nearEntitys){
                                            if (ent.getType() == EntityType.ARMOR_STAND && ent.getScoreboardTags().contains("grave_group"))
                                                destroy(ent, loc);
                                            Bukkit.getScheduler().cancelTask( this.getTaskId());
                                        }

                                        Bukkit.getScheduler().cancelTask(this.getTaskId());
                                    }
                                    if (!corpseui.isEmpty()){
                                        p_inv.replace(inv_slot.toString(), corpseui.getContents());
                                    }
                                    if (!player.getOpenInventory().getType().equals(InventoryType.CHEST)){
                                        Bukkit.getScheduler().cancelTask(this.getTaskId());
                                    }
                                }
                            }.runTaskTimer(Cor3.getInstance(), 0,1);
                        }
                        e.setCancelled(true);
                    }
                    break;
                }
            }
        }
    }
    private void destroy(Entity entity, Location location){
        location.getBlock().setType(Material.AIR);
        p_inv.remove(entity.getUniqueId().toString());
        Location dieloc = entity.getLocation();
        dieloc.setY(-100);
        entity.teleport(dieloc);
    }
}
