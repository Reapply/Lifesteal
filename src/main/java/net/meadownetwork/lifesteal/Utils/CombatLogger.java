package net.meadownetwork.lifesteal.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CombatLogger implements Listener {
    @EventHandler
    // Get if a player has taken damage from another player
    public void onPlayerDamage(org.bukkit.event.entity.EntityDamageByEntityEvent event) {
        // If the entity that took damage is a player
        if (event.getEntity() instanceof Player) {
            // Get the player that took damage
            Player player = (Player) event.getEntity();
            // If the entity that damaged the player is a player
            if (event.getDamager() instanceof Player) {
                // Get the player that damaged the player
                Player damager = (Player) event.getDamager();
                // message both players that they are in combat
                player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "[Meadow] " + ChatColor.RESET + ChatColor.WHITE + "You are now in combat!");
                damager.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "[Meadow] " + ChatColor.RESET + ChatColor.WHITE + "You are now in combat!");
                // For the next 10 seconds if either player leaves the server Kill them
                // Check if the player or damager has left the server
                Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("LifeSteal"), new Runnable() {
                    @Override
                    public void run() {
                        // If the player has left the server kill them
                        if (!player.isOnline()) {
                            player.setHealth(0);
                        }
                        // If the damager has left the server kill them
                        if (!damager.isOnline()) {
                            damager.setHealth(0);
                        }
                    }
                }, 200);
                // After 10 seconds if the player or damager has not left the server remove the combat tag
                Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("LifeSteal"), new Runnable() {
                    @Override
                    public void run() {
                        // If the player is still online remove the combat tag
                        if (player.isOnline()) {
                            player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "[Combat] " + ChatColor.GREEN + "" + ChatColor.BOLD + "You are no longer in combat!");
                        }
                        // If the damager is still online remove the combat tag
                        if (damager.isOnline()) {
                            damager.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "[Combat] " + ChatColor.GREEN + "" + ChatColor.BOLD + "You are no longer in combat!");
                        }
                    }
                }, 400);

            }
        }
    }
}