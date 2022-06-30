package net.meadownetwork.lifesteal.Listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.net.http.WebSocket;

public class SpawnArmorStandListener implements Listener {
    @EventHandler
    public void onSpawnArmorStand(org.bukkit.event.entity.EntitySpawnEvent event) {
        if (event.getEntity().getType() == EntityType.ARMOR_STAND) {
            event.getEntity().setGravity(false);
        }
    }
}
