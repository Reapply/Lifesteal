package net.meadownetwork.lifesteal.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListListener implements Listener {
    @EventHandler
    public void onServerList(org.bukkit.event.server.ServerListPingEvent event) {
        // Get the number of players and set the max players to that number
       // event.setMaxPlayers(event.getNumPlayers() + 1);
        // Set the motd to "Lifesteal"
       // event.setMotd(ChatColor.YELLOW + "" + ChatColor.BOLD + "  ❀Meadow Network❀   " + ChatColor.RED + ChatColor.BOLD + "\n  →Alpha←");
        if (Bukkit.hasWhitelist()) {
            // Set the MOTD to maintenance mode enabled
            event.setMotd(ChatColor.YELLOW + "" + ChatColor.BOLD + "  ❀Meadow Network❀   " + ChatColor.RED + ChatColor.BOLD + "\n  →Alpha←" + ChatColor.RED + ChatColor.BOLD + "  →Maintenance Mode Enabled←");
            event.setMaxPlayers(event.getNumPlayers() + 1);

        } else {
            // Set the MOTD to maintenance mode disabled
            event.setMotd(ChatColor.YELLOW + "" + ChatColor.BOLD + "  ❀Meadow Network❀   " + ChatColor.RED + ChatColor.BOLD + "\n  →Alpha←" );
            event.setMaxPlayers(event.getNumPlayers() + 1);



        }
    }
}
