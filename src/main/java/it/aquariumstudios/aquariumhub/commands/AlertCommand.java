package it.aquariumstudios.aquariumhub.commands;

import it.aquariumstudios.aquariumhub.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AlertCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!sender.hasPermission("aquariumhub.alert")) {
            sender.sendMessage(ChatUtils.getFormattedText("no-permission"));
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatUtils.getFormattedText("alert.usage"));
            return true;
        }

        String message = String.join(" ", args);
        Bukkit.broadcastMessage(ChatUtils.getColoredText("&7[&cAvviso&7]: &f" + message));
        return true;
    }
}
