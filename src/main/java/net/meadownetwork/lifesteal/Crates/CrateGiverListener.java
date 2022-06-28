package net.meadownetwork.lifesteal.Crates;

import net.meadownetwork.lifesteal.Utils.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CrateGiverListener implements Listener {

    @EventHandler
    public void OnPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null
                    && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(ChatColor.RED + "❤" + ChatColor.WHITE + "Open to receive one of these items:")) {
                event.setCancelled(true);
                player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_CHIME, 20.0f, 1.0f);
                if (Math.random() < 0.5) {
                    player.getInventory().addItem(new ItemStack(Material.DIAMOND, 25));
                    player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Meadow" + ChatColor.WHITE + ": " + ChatColor.GRAY + "You have been given 25 diamonds!");
                } else {
                    // Have a 50% chance to give the player a Common Heart
                    player.getInventory().addItem(ItemManager.commonheart);
                    player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Meadow" + ChatColor.RESET + ChatColor.WHITE + ": " + ChatColor.GRAY + "You have been given a Common Heart!");
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