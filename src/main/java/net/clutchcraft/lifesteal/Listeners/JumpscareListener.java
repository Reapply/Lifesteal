package net.clutchcraft.lifesteal.Listeners;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;


public class JumpscareListener implements Listener {
    @EventHandler
    public void onJumpscare(BlockBreakEvent event) {
        // When the player breaks stone have a 1% chance of spawning a skeleton

        if (event.getBlock().getType() == Material.STONE) {
            // 10% chance
            //if (Math.random() < 0.1) {




            if (Math.random() < 0.1) {
                // spawn a skeleton on the player
                event.getPlayer().getWorld().spawnEntity(event.getPlayer().getLocation(), EntityType.SKELETON);

                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_GHAST_SCREAM, 1, 1);
                // Spawn a cirlce of particals around the skeleton
                for (int i = 2; i < 360; i++) {
                    event.getBlock().getWorld().spawnParticle(Particle.DRIP_LAVA, event.getBlock().getLocation().add(Math.cos(i) * 1, 2, Math.sin(i) * 1), 1);
                }
                // Play a sound at the location of the stone

            }
        }
    }
}
        /*if (event.getBlock().getType() == Material.STONE) {
            if (Math.random() < 0.05) {
                event.getBlock().getWorld().spawnEntity(event.getBlock().getLocation(), EntityType.SKELETON);*/




