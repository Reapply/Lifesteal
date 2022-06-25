package net.clutchcraft.lifesteal.CustomHeartItems;

import net.clutchcraft.lifesteal.utils.BlockManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CustomHeartAdders implements Listener {
    @EventHandler



    public void onItemClick(PlayerInteractEvent event) {
        // When the player right clicks on a bedrock with the item lore of "Common Heart" add 1 heart to the player
        // If the item is null return
        if (event.getItem() == null) {
            return;
        }
        if (event.getItem().getItemMeta().getLore() == null) {
            return;
        }
        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore().contains(BlockManager.CommonHeart.getItemMeta().getLore().get(0))) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                event.getPlayer().getWorld().spawnParticle(Particle.DRIP_LAVA, event.getPlayer().getLocation().add(0, 2, 0), 1);
                event.getPlayer().getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).setBaseValue(event.getPlayer().getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).getBaseValue() + 1);
                // Remove the item from the player's inventory
                event.getPlayer().getInventory().removeItem(event.getItem());
            }
        }

    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockPlace(BlockPlaceEvent event) {
        // If they place bedrock
        if (event.getBlock().getType() == Material.BEDROCK) {
            event.setCancelled(true);


        }
    }
}

