package net.meadownetwork.lifesteal.Guis;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.net.http.WebSocket;

public class HelpGuiListener implements Listener {
    @EventHandler
    public void onInventoryClick(org.bukkit.event.inventory.InventoryClickEvent event) {
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD.toString() + "" + ChatColor.BOLD + "Help Menu")) {
            // If the player clicks the honey bottle in the inventory make them open a link to join the discord
            if (event.getCurrentItem().getType().equals(org.bukkit.Material.HONEY_BOTTLE)) {
                event.getWhoClicked().closeInventory();
                event.getWhoClicked().sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "https://discord.gg/MeadowNetwork");
            }
        }
    }
}
