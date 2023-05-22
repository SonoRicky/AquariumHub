package it.aquariumstudios.aquariumhub.commands;

import it.aquariumstudios.aquariumhub.utils.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage(ChatUtils.getColoredText("&7Running &bAquariumHub &7version &a1.0 &7by &cSonoRicky"));
        sender.sendMessage(ChatUtils.getColoredText("&edsc.gg/AquariumStudios"));
        return true;
    }
}
