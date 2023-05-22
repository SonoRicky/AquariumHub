package it.aquariumstudios.aquariumhub.commands;

import it.aquariumstudios.aquariumhub.AquariumHub;
import it.aquariumstudios.aquariumhub.utils.ChatUtils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class SetSpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatUtils.getFormattedText("player-only"));
            return true;
        }

        if (!sender.hasPermission("aquariumhub.setspawn")) {
            sender.sendMessage(ChatUtils.getFormattedText("no-permission"));
            return true;
        }

        Player player = (Player) sender;

        Location location = player.getLocation();
        FileConfiguration config = AquariumHub.getFileManager().getConfig();

        File file = new File(AquariumHub.getInstance().getDataFolder(), "config.yml");

        config.set("spawn-location", location);
        AquariumHub.getFileManager().saveFile(config, file);

        sender.sendMessage(ChatUtils.getFormattedText("spawn.new-spawn-set"));
        return true;
    }
}