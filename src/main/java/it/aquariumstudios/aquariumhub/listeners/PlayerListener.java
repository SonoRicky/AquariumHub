package it.aquariumstudios.aquariumhub.listeners;

import it.aquariumstudios.aquariumhub.AquariumHub;
import it.aquariumstudios.aquariumhub.utils.ChatUtils;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.block.*;
import org.bukkit.*;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.meta.*;
import org.bukkit.enchantments.*;
import org.bukkit.inventory.*;
import java.util.*;

public class PlayerListener implements Listener {
    public PlayerListener() {
        this.playersInPvP = new ArrayList<Player>();
    }
    List<Player> playersInPvP;

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        Player player = event.getPlayer();
        player.getInventory().clear();
        Inventory inv = player.getInventory();
        inv.setItem(1, this.createItem(Material.BOOK, "§dSocial", "§7Clicca per visualizzare §7tutti i nostri social", true, 1));
        inv.setItem(0, this.createItem(Material.SANDSTONE, "§eBlocchi", "§7Si rimuovono automaticamente §7dopo 10 secondi", true, 64));
        inv.setItem(7, this.createItem(Material.TORCH, "§cNascondi giocatori §7(Tasto destro)", "§7Clicca per nascondere §7i giocatori", true, 1));
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        if (AquariumHub.vanishedStaffers.contains(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent event) {
        event.setFoodLevel(20);
        event.setCancelled(true);
    }

    @EventHandler
    public void onAchievement(PlayerAchievementAwardedEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void WeatherChangeEvent(WeatherChangeEvent event) {
        if (!event.toWeatherState()) {
            return;
        }

        event.setCancelled(true);
        event.getWorld().setWeatherDuration(0);
        event.getWorld().setThundering(false);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!event.getBlock().getType().equals(Material.SANDSTONE)) {
            event.setCancelled(true);
        }
        Player player = event.getPlayer();
        Inventory inv = player.getInventory();
        inv.setItem(0, this.createItem(Material.SANDSTONE, "§eBlocchi", "§7Si rimuovono automaticamente §7dopo §e10 §7secondi", true, 64));
        Bukkit.getScheduler().runTaskLater(AquariumHub.getInstance(), () -> event.getBlock().setType(Material.AIR), 200L);
    }

    @EventHandler
    public void onPlayerPickup(PlayerPickupItemEvent event) {
        event.setCancelled(true);
    }


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Inventory playerinv = player.getInventory();
        if (event.getItem() == null) {
            return;
        }

        String displayName = event.getItem().getItemMeta().getDisplayName();

        switch (displayName) {
            case "§dSocial": {
                player.sendMessage(ChatUtils.getFormattedText("social.list"));
                break;
            }

            case "§cNascondi giocatori §7(Tasto destro)": {
                playerinv.setItem(7, this.createItem(Material.TORCH, "§aMostra giocatori §7(Tasto destro)", "§7Clicca per mostrare\n§7i giocatori", true, 1));
                for (Player players : Bukkit.getOnlinePlayers()) {
                    player.hidePlayer(players);
                }
                player.sendMessage("§cGiocatori nascosti");
                break;
            }

            case "§aMostra giocatori §7(Tasto destro)": {
                playerinv.setItem(7, this.createItem(Material.TORCH, "§cNascondi giocatori §7(Tasto destro)", "§7Clicca per nascondere\n§7i giocatori", true, 1));
                for (Player players : Bukkit.getOnlinePlayers()) {
                    player.showPlayer(players);
                }

                player.sendMessage("§aGiocatori visibili");
                break;
            }
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            event.setCancelled(true);
        }
    }

    private ItemStack createItem(Material material, String name, String lore, boolean enchant, int amount) {
        ItemStack item;
        if (name.startsWith("§bNascondi")) {
            item = new ItemStack(material, amount, (short)5);
        }
        else if (name.startsWith("§bMostra")) {
            item = new ItemStack(material, amount, (short)8);
        }
        else {
            item = new ItemStack(material, amount);
        }
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.spigot().setUnbreakable(true);
        if (enchant) {
            item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
            meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_UNBREAKABLE });
            meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
            meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        }
        if (lore != "") {
            meta.setLore((List)Arrays.asList(lore.split("\n")));
        }
        item.setItemMeta(meta);
        return item;
    }
}
