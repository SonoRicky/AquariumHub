package it.aquariumstudios.aquariumhub.commands.teleport;

import it.aquariumstudios.aquariumhub.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TphereCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatUtils.getFormattedText("player-only"));
            return true;
        }

        if (!sender.hasPermission("aquariumhub.tphere")) {
            sender.sendMessage(ChatUtils.getFormattedText("no-permission"));
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatUtils.getFormattedText("tphere.usage"));
            return true;
        }

        if (args.length == 1) {
            String name = args[0];

            if (Bukkit.getPlayerExact(name) == player) {
                player.sendMessage(ChatUtils.getFormattedText("tphere.tp-yourself-error"));
                return true;
            }

            if (Bukkit.getPlayerExact(name) == null) {
                player.sendMessage(ChatUtils.getFormattedText("player-offline"));
                return true;
            }

            Player target = Bukkit.getPlayerExact(name);

            target.teleport(player.getLocation());
            player.sendMessage(ChatUtils.getFormattedText("tphere.success")
                    .replaceAll("%name%", target.getName()));
        }
        return true;
    }
}
