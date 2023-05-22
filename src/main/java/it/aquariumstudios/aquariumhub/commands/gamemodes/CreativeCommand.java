package it.aquariumstudios.aquariumhub.commands.gamemodes;

import it.aquariumstudios.aquariumhub.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreativeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatUtils.getFormattedText("player-only"));
            return true;
        }

        if (!sender.hasPermission("aquariumhub.gmc")) {
            sender.sendMessage(ChatUtils.getFormattedText("no-permission"));
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatUtils.getFormattedText("gamemodes.creative"));
            player.setGameMode(GameMode.CREATIVE);
            return true;
        }

        else if (args.length == 1) {
            Player target = Bukkit.getPlayerExact(args[0]);

            if (target == null) {
                player.sendMessage(ChatUtils.getFormattedText("player-offline"));
                return true;
            }

            if (target == player) {
                player.sendMessage(ChatUtils.getFormattedText("gamemodes.creative"));
                player.setGameMode(GameMode.CREATIVE);
                return true;
            }

            target.setGameMode(GameMode.CREATIVE);
            player.sendMessage(ChatUtils.getFormattedText("gamemodes.creative-for-player")
                    .replaceAll("%name%", target.getName()));

            target.sendMessage(ChatUtils.getFormattedText("gamemodes.creative-by-staff")
                    .replaceAll("%name%", player.getName()));
        }
        return true;
    }
}
