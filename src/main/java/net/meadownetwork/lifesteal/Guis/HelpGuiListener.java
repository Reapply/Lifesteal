package net.meadownetwork.lifesteal.Guis;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.net.http.WebSocket;

public class HelpGuiListener implements Listener {
    @EventHandler
    public void onInventoryClick(org.bukkit.event.inventory.InventoryClickEvent event) {
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD.toString() + "" + ChatColor.BOLD + "Help Menu")) {
            event.setCancelled(true);
        }
    }
}
