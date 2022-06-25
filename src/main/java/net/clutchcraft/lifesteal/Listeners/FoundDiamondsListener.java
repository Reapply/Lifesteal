package net.clutchcraft.lifesteal.Listeners;

import net.clutchcraft.lifesteal.main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.HashSet;
import java.util.Set;


public class FoundDiamondsListener implements Listener {
    // When the player mines diamond blocks tell all players with the permission "lifesteal.founddiamonds" that they found diamonds
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() == Material.DIAMOND_ORE) {
            Player player = event.getPlayer();
            // Only send the message if the player has the permission
            if (player.hasPermission("lifesteal.founddiamonds")) {
                // Get all players with the permission
                Set<Player> players = new HashSet<Player>();
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.hasPermission("lifesteal.founddiamonds")) {
                        players.add(p);
                    }
                }
                // Send the message to all players with the permission
                for (Player p : players) {
                    p.sendMessage(ChatColor.GREEN + player.getName() + " found diamonds!");
                }
            }

        }
    }
}


