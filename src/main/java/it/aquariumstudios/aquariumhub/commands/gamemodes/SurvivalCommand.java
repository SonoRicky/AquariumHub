package it.aquariumstudios.aquariumhub.commands.gamemodes;

import it.aquariumstudios.aquariumhub.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SurvivalCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatUtils.getFormattedText("player-only"));
            return true;
        }

        if (!sender.hasPermission("aquariumhub.gms")) {
            sender.sendMessage(ChatUtils.getFormattedText("no-permission"));
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatUtils.getFormattedText("gamemodes.survival"));
            player.setGameMode(GameMode.SURVIVAL);
            return true;
        }

        else if (args.length == 1) {
            Player target = Bukkit.getPlayerExact(args[0]);

            if (target == null) {
                player.sendMessage(ChatUtils.getFormattedText("player-offline"));
                return true;
            }

            if (target == player) {
                player.sendMessage(ChatUtils.getFormattedText("gamemodes.survival"));
                player.setGameMode(GameMode.SURVIVAL);
                return true;
            }

            target.setGameMode(GameMode.SURVIVAL);
            player.sendMessage(ChatUtils.getFormattedText("gamemodes.survival-for-player")
                    .replaceAll("%name%", target.getName()));

            target.sendMessage(ChatUtils.getFormattedText("gamemodes.survival-by-staff")
                    .replaceAll("%name%", player.getName()));
        }
        return true;
    }
}
