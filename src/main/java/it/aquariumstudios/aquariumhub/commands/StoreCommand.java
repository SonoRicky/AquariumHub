package it.aquariumstudios.aquariumhub.commands;

import it.aquariumstudios.aquariumhub.utils.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StoreCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage(ChatUtils.getFormattedText("social.store"));
        return true;
    }
}
