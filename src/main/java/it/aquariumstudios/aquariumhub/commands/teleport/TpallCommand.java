package it.aquariumstudios.aquariumhub.commands.teleport;

import it.aquariumstudios.aquariumhub.AquariumHub;
import it.aquariumstudios.aquariumhub.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpallCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatUtils.getFormattedText("player-only"));
            return true;
        }

        if (!sender.hasPermission("aquariumhub.tpall")) {
            sender.sendMessage(ChatUtils.getFormattedText("no-permission"));
            return true;
        }

        Location senderLocation = ((Player) sender).getLocation();

        Bukkit.getScheduler().runTaskAsynchronously(AquariumHub.getInstance(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.teleport(senderLocation);
            }
        });
        sender.sendMessage(ChatUtils.getFormattedText("tpall.success"));
        return true;
    }
}
