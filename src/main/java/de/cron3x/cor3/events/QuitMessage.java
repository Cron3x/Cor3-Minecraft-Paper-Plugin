package de.cron3x.cor3.events;

import de.cron3x.cor3.Cor3;
import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuitMessage implements Listener {
//TODO: Random Leave Message

    private static final Pattern color_pattern = Pattern.compile("#[a-fA-F0-9]{6}");

    public static Color C3X_PINK = new Color(207, 0, 117);

    @EventHandler
    public static void onPlayerJoin(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        e.setQuitMessage(format(messageBuilder(player, getMessage(player))));//getOptions(Objects.requireNonNull(player.getName()))
    }
    private static String format(String msg){
        if (Bukkit.getVersion().contains("1.16")){
            Matcher match = color_pattern.matcher(msg);
            while (match.find()){
                String color = msg.substring(match.start(), match.end());
                msg = msg.replace(color, ChatColor.of(color)+"");
                match = color_pattern.matcher(msg);
            }
        }
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
    public static String getMessage(Player player) {
        StringBuilder file_content = new StringBuilder();
        String file_path = "cor3-plugin/quit_message.q3";
        try {
            File file = new File(file_path);
            log(file_path);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                String default_option = "NAME:{Cron3x} MESSAGE:{%%PLAYER%% left us};\n" +
                        "@ALL MESSAGE:{#9e9e9e%%PLAYER%% left the Game, we will miss him/her};";
                FileWriter fw = new FileWriter(file);
                fw.write(default_option);
                fw.close();
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                log("FileContent: "+data);
                file_content.append(data);
            }
            scanner.close();

            String[] fc_list = file_content.toString().split(";");
            String final_msg = "";
/*
            for (int i = 0; i <= fc_list.length; i++){
                log(""+i);
                log(">------------------------<");
                log(fc_list[i]);
            }
*/          log(""+player.getScoreboard().getTeams());
            for(int i = 0; i < fc_list.length; ++i){
                String fc_list_str = Arrays.toString(fc_list);
                log(""+fc_list.length);
                log(fc_list_str);
                String messageContent = StringUtils.substringBetween(fc_list[i], "MESSAGE:{","}");
                String name = "NAME:{"+player.getName()+"}";
                log("->"+fc_list_str+" -~*~- ");
                log("=>"+fc_list[i]);
                log("NAME:{"+player.getName()+"}");
                if (fc_list_str.contains(name)){ //StringUtils.substringBetween(fc_list[i], "NAME:{","}").contains("{"+player.getName()+"}")
                    if (fc_list[i].contains(name)){
                        final_msg = messageContent;
                        break;
                    }
                }
                else if (fc_list_str.contains("TEAM:{"+"LOL"+"}")){ //.contains((CharSequence) player.getScoreboard().getPlayerTeam(player))
                    final_msg = messageContent;
                    break;
                }
                else if(fc_list_str.contains("@ALL")){
                    log("aoijdjwaadonjwodawddddddddd");
                    if (fc_list[i].contains("@ALL")){
                        log("@ALL");
                        final_msg = messageContent;
                        break;
                    }
                }
                else {
                    final_msg = "Welcome "+player.getName();
                }
            }
            return final_msg;
        } catch (IOException e) {
            e.printStackTrace();
            log(e.toString());

            return "Welcome "+player.getName();
        }
    }
    public static String messageBuilder(Player player, String message){
        log(message.replace("%%PLAYER%%", player.getName()));
        return message.replace("%%PLAYER%%", player.getName());

    }
    public static void log(String text){
        if (Cor3.LOG) {Bukkit.getConsoleSender().sendMessage(Cor3.PREFIX+ text);}
    }
}