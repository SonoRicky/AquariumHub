package it.aquariumstudios.aquariumhub.utils;

import it.aquariumstudios.aquariumhub.AquariumHub;
import org.bukkit.ChatColor;

public class ChatUtils {
    public static String getColoredText(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static String getFormattedText(String path) {
        return getColoredText(AquariumHub.getFileManager().getMessages().getString(path));
    }
}
