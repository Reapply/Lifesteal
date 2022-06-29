package net.meadownetwork.lifesteal.AdminCommands;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class SetSpawnListener implements Listener {
    @EventHandler
    // When a player dies or joins the server, get the spawn point set by the admin that used /setspawn
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        // Get the player that joined the server
        Player player = event.getPlayer();
        // Get the spawn point set by the admin that used /setspawn
        player.teleport(player.getWorld().getSpawnLocation());
        // Only allow players to spawn in that location

    }
// If the player dies teleport them to the spawn point set by the admin that used /setspawn

    @EventHandler
// When a player dies or joins the server, get the spawn point set by the admin that used /setspawn
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        player.teleport(player.getWorld().getSpawnLocation());
    }
}