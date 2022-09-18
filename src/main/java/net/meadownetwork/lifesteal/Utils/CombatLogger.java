package net.meadownetwork.lifesteal.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CombatLogger implements Listener {
    public static boolean isInCombat = false;
    @EventHandler
    public void onPlayerDamage(org.bukkit.event.entity.EntityDamageByEntityEvent event) {
        // When a player is damaged by another player set the player to be in combat for 10 seconds
        // If the player is already in combat and is damaged by another player reset the timer to 10 seconds again
        // Send a message to both players that they are in combat and cannot log out for 10 seconds
        // After 10 seconds set the player to not be in combat and send a message to the player & damager that they are no longer in combat
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            Player player = (Player) event.getEntity();
            Player damager = (Player) event.getDamager();
            isInCombat = true;
            Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("LifeSteal"), new Runnable() {
                @Override
                public void run() {
                    isInCombat = false;
                    player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[⚔] " + ChatColor.RESET + ChatColor.RED + "You are no longer in combat");
                    damager.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[⚔] " + ChatColor.RESET + ChatColor.RED + "You are no longer in combat");


                }
            }, 200L);
            player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[⚔] " + ChatColor.RESET + ChatColor.RED + "You are now in combat");
            damager.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[⚔] " + ChatColor.RESET + ChatColor.RED + "You are now in combat");
        }


    }
    // Once the player is in combat dont allow them to open any inventories or send any commands to the server
    // If the player is in combat and tries to open an inventory or send a command to the server send a message to the player saying they are in combat and cannot do that
    @EventHandler
    public void onPlayerInventoryClick(org.bukkit.event.inventory.InventoryClickEvent event) {
        if (isInCombat) {
            event.setCancelled(true);
            event.getWhoClicked().sendMessage(ChatColor.RED + "You are in combat and cannot open inventories");
        }
    }
    @EventHandler
    public void onPlayerCommandPreprocess(org.bukkit.event.player.PlayerCommandPreprocessEvent event) {
        if (isInCombat) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "You are in combat and cannot send commands");
        }
    }
}
