package it.aquariumstudios.aquariumhub.commands.teleport;

import it.aquariumstudios.aquariumhub.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatUtils.getFormattedText("player-only"));
            return true;
        }

        if (!sender.hasPermission("aquariumhub.tp")) {
            sender.sendMessage(ChatUtils.getFormattedText("no-permission"));
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatUtils.getFormattedText("teleport.usage"));
            return true;

        } else if (args.length == 1) {
            Player target = Bukkit.getPlayerExact(args[0]);

            if (target == null) {
                player.sendMessage(ChatUtils.getFormattedText("player-offline"));
                return true;
            }

            if (player == target) {
                sender.sendMessage(ChatUtils.getFormattedText("teleport.tp-yourself-error"));
                return true;
            }

            player.teleport(target);
            player.sendMessage(ChatUtils.getFormattedText("teleport.success")
                    .replaceAll("%name%", target.getName()));
        }

        if (player.hasPermission("aquariumhub.tp.other")) {
            if (args.length == 2) {
                Player target = Bukkit.getPlayerExact(args[0]);
                Player target2 = Bukkit.getPlayerExact(args[1]);

                if (target == null) {
                    player.sendMessage(ChatUtils.getFormattedText("player-offline"));
                    return true;
                }

                if (target2 == null) {
                    player.sendMessage(ChatUtils.getFormattedText("player-offline"));
                    return true;
                }

                if (target == target2) {
                    sender.sendMessage(ChatUtils.getFormattedText("teleport.tp-other-self"));
                    return true;
                }

                target.teleport(target2);
                player.sendMessage(ChatUtils.getFormattedText("teleport.success-different")
                        .replaceAll("%name%", target.getName())
                        .replaceAll("%target%", target2.getName()));
            }
        }
        return true;
    }
}
