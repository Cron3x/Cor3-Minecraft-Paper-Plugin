package de.cron3x.cor3.util;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorConverter {
    private static final Pattern color_pattern = Pattern.compile("#[a-fA-F0-9]{6}");
    public static String format(String msg){
        if (Bukkit.getVersion().contains("1.16") || Bukkit.getVersion().contains("1.17") || Bukkit.getVersion().contains("1.18") || Bukkit.getVersion().contains("1.19") || Bukkit.getVersion().contains("1.19") || Bukkit.getVersion().contains("1.20")){
            Matcher match = color_pattern.matcher(msg);
            while (match.find()){
                String color = msg.substring(match.start(), match.end());
                msg = msg.replace(color, ChatColor.of(color)+"");
                match = color_pattern.matcher(msg);
            }
        }
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
