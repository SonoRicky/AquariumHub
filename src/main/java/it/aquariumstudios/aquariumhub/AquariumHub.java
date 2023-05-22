package it.aquariumstudios.aquariumhub;

import it.aquariumstudios.aquariumhub.commands.*;
import it.aquariumstudios.aquariumhub.commands.gamemodes.CreativeCommand;
import it.aquariumstudios.aquariumhub.commands.gamemodes.SpectatorCommand;
import it.aquariumstudios.aquariumhub.commands.gamemodes.SurvivalCommand;
import it.aquariumstudios.aquariumhub.commands.teleport.TeleportCommand;
import it.aquariumstudios.aquariumhub.commands.teleport.TpallCommand;
import it.aquariumstudios.aquariumhub.commands.teleport.TphereCommand;
import it.aquariumstudios.aquariumhub.listeners.PlayerListener;
import it.aquariumstudios.aquariumhub.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class AquariumHub extends JavaPlugin {
    public static List<Player> vanishedStaffers;

    @Override
    public void onEnable() {
        instance = this;
        fileManager = new FileManager(instance);

        this.loader();

        System.out.println("AquariumHub abilitato con successo.");
        System.out.println("By SonoRicky");
        System.out.println("dsc.gg/AquariumStudios");

    }

    private void loader() {
        getCommand("gmc").setExecutor(new CreativeCommand());
        getCommand("gmsp").setExecutor(new SpectatorCommand());
        getCommand("gms").setExecutor(new SurvivalCommand());
        getCommand("tp").setExecutor(new TeleportCommand());
        getCommand("tpall").setExecutor(new TpallCommand());
        getCommand("tphere").setExecutor(new TphereCommand());
        getCommand("alert").setExecutor(new AlertCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("invsee").setExecutor(new InvseeCommand());
        getCommand("aquariumhub").setExecutor(new MainCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("store").setExecutor(new StoreCommand());
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("vote").setExecutor(new VoteCommand());

        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @Override
    public void onDisable() {
    }

    static {AquariumHub.vanishedStaffers = new ArrayList<Player>();}
    private static AquariumHub instance;
    public static AquariumHub getInstance() {
        return instance;
    }
    private static FileManager fileManager;
    public static FileManager getFileManager() {
        return fileManager;
    }

}
