package net.clutchcraft.lifesteal.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ServerListListener implements Listener {
    @EventHandler
    public void onServerList(org.bukkit.event.server.ServerListPingEvent event) {
        // Get the number of players and set the max players to that number
        event.setMaxPlayers(event.getNumPlayers() +1 );
        // Set the motd to "Lifesteal"
        event.setMotd(ChatColor.YELLOW + "" + ChatColor.BOLD + "  ❀Meadow Network❀   " + ChatColor.RED + ChatColor.BOLD + "\n  →Alpha←");






    }
}
