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
            isInCombat = false;
            Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("LifeSteal"), new Runnable() {
                @Override
                public void run() {
                    // if isincombat is true send a message to the player & damager that they are no longer in combat however do not spam the player with the message only send it once if the player is in the world "World" do not do anything
                    if (isInCombat && player.getWorld().getName().equals("LifeSteal")) {
                        player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "[Meadow] " + ChatColor.RESET + ChatColor.GREEN + "You are no longer in combat");
                        damager.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "[Meadow] " + ChatColor.RESET + ChatColor.GREEN + "You are no longer in combat");
                        isInCombat = false;
                    }
                }
            }, 200L);
            // Check if & damager are in the world "LifeSteal" if they are send a message to the player & damager that they are in combat
            if (player.getWorld().getName().equals("LifeSteal")) {
                player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "[Meadow] " + ChatColor.RESET + ChatColor.RED + "You are in combat and cannot log out for 10 seconds");
                damager.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "[Meadow] " + ChatColor.RESET + ChatColor.RED + "You are in combat and cannot log out for 10 seconds");
            }
        }

        // if the player is in the world "world" do not use the combat logger and do not send the player a message
        if (event.getEntity().getWorld().getName().equals("world")) {
            return;
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
    // If the player leaves the server while in combat get their inventory and drop it on the ground and then when they log back in clear their inventory and teleport them to spawn in the world with the name "world"
    @EventHandler
    public void onPlayerQuit(org.bukkit.event.player.PlayerQuitEvent event) {
        if (isInCombat) {
            event.getPlayer().getInventory().clear();
            event.getPlayer().getWorld().dropItemNaturally(event.getPlayer().getLocation(), event.getPlayer().getInventory().getItemInMainHand());
            event.getPlayer().getWorld().dropItemNaturally(event.getPlayer().getLocation(), event.getPlayer().getInventory().getItemInOffHand());
            event.getPlayer().getWorld().dropItemNaturally(event.getPlayer().getLocation(), event.getPlayer().getInventory().getHelmet());
            event.getPlayer().getWorld().dropItemNaturally(event.getPlayer().getLocation(), event.getPlayer().getInventory().getChestplate());
            event.getPlayer().getWorld().dropItemNaturally(event.getPlayer().getLocation(), event.getPlayer().getInventory().getLeggings());
            event.getPlayer().getWorld().dropItemNaturally(event.getPlayer().getLocation(), event.getPlayer().getInventory().getBoots());
            event.getPlayer().teleport(event.getPlayer().getServer().getWorld("world").getSpawnLocation());
            // Kill the player
            event.getPlayer().setHealth(0);
            // When the rejoin the server tell them they left while in combat and that their inventory was dropped on the ground
            event.getPlayer().sendMessage(ChatColor.RED + "You left while in combat and your inventory was dropped on the ground");

        }
    }
}


