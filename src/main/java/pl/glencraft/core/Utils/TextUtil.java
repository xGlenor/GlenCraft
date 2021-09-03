package pl.glencraft.core.Utils;

import org.bukkit.ChatColor;

public class TextUtil {

    public static String color(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
