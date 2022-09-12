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
        // if the player has never joined before, teleport them to the spawn point but if they have joined before, don't teleport them
        if (player.hasPlayedBefore()) {
            return;
        } else {
            player.teleport(player.getWorld().getSpawnLocation());
        }
    }
// If the player dies teleport them to the spawn point set by the admin that used /setspawn

    @EventHandler
// When a player dies or joins the server, get the spawn point set by the admin that used /setspawn
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        player.teleport(player.getWorld().getSpawnLocation());
    }
}