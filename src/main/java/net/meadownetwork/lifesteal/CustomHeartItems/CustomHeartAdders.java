package net.meadownetwork.lifesteal.CustomHeartItems;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class CustomHeartAdders implements Listener {


    @EventHandler(priority = EventPriority.HIGHEST)
    public void OnPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null
                    && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(ChatColor.GOLD + "Ability:")) {
                // Give the player a heart
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() + 2.0);
                // Play a villager sound
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                player.getInventory().remove(player.getInventory().getItemInMainHand());
                // Dont allow the player to place the poppy
                event.setCancelled(true);
                // if they have more then 20 heart attributes dont add any more hearts
                if (player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() > 40.0) {
                    player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40.0);
                    player.sendMessage(ChatColor.RED + "You have reached the max amount of hearts!");
                    // Dont use the poppy
                    event.setCancelled(true);

                }
                // Spawn a circle of particles
                // if they have more then 20 heart attributes dont add any more hearts

                for (double i = 0; i < 360; i += 1) {
                    Location loc = player.getLocation();
                    loc.add(Math.cos(i) * 1.5, 0, Math.sin(i) * 1.5);
                    player.getWorld().spawnParticle(Particle.HEART, loc, 1);
                }
                if (player.getInventory().getItemInMainHand().getAmount() > 1) {
                    player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
                } else {
                    player.getInventory().remove(player.getInventory().getItemInMainHand());
                }


            }
        }
    }
}

