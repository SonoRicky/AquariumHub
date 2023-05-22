package it.aquariumstudios.aquariumhub.commands;

import it.aquariumstudios.aquariumhub.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatUtils.getFormattedText("player-only"));
            return true;
        }

        if (!(sender.hasPermission("aquariumhub.fly"))) {
            sender.sendMessage(ChatUtils.getFormattedText("no-permission"));
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            if (player.getAllowFlight()) {
                player.setAllowFlight(false);
                player.sendMessage(ChatUtils.getFormattedText("fly.disabled"));
                return true;
            } else {
                player.setAllowFlight(true);
                player.sendMessage(ChatUtils.getFormattedText("fly.enabled"));
                return true;
            }
        }

        Player target = Bukkit.getPlayerExact(args[0]);

        if (!target.isOnline()) {
            player.sendMessage(ChatUtils.getFormattedText("player-offline"));
            return true;
        } else {
            if (target.getAllowFlight()) {
                target.setAllowFlight(false);
                player.sendMessage(ChatUtils.getFormattedText("fly.disabled-for-player")
                        .replaceAll("%name%", target.getName()));
                target.sendMessage(ChatUtils.getFormattedText("fly-disabled-by-staff")
                        .replaceAll("%name%", player.getName()));
                return true;
            } else {
                target.setAllowFlight(true);
                player.sendMessage(ChatUtils.getFormattedText("fly.enabled-for-player")
                        .replaceAll("%name%", target.getName()));
                target.sendMessage(ChatUtils.getFormattedText("fly.disabled-for-player")
                        .replaceAll("%name%", player.getName()));
                return true;
            }
        }
    }
}
