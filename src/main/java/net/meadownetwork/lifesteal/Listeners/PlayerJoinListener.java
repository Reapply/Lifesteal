package net.meadownetwork.lifesteal.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + ChatColor.STRIKETHROUGH + "---------------------------------------------");
        player.sendMessage("");
        player.sendMessage("                      " + ChatColor.GOLD + "" + ChatColor.BOLD + ChatColor.UNDERLINE + "Meadow" + ChatColor.WHITE + ChatColor.UNDERLINE + ChatColor.BOLD + "Network");
        player.sendMessage("");
        player.sendMessage("           " + ChatColor.YELLOW + "" + ChatColor.BOLD + "STORE: " + ChatColor.WHITE + "store.meadownetwork.net");
        player.sendMessage("            " + ChatColor.WHITE + "" + ChatColor.BOLD + "DISCORD: " + ChatColor.WHITE + "discord.gg/MeadowNetwork");
        player.sendMessage("           " + ChatColor.GOLD + "" + ChatColor.BOLD + "TWITTER: " + ChatColor.WHITE + "twitter.com/MeadowNetwork");
        player.sendMessage("");
        player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + ChatColor.STRIKETHROUGH + "---------------------------------------------");
        // If the player has never joined before announce them to the server
        if (player.hasPlayedBefore()) {
            player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Welcome back " + ChatColor.RESET + ChatColor.BOLD + player.getDisplayName());
        } else {
            Bukkit.broadcastMessage("" + ChatColor.GOLD + "" + ChatColor.BOLD + player.getDisplayName() + " has joined the server for the first time!");
        }
        // Disable the default bukkit join message
        event.setJoinMessage(null);

    }


}
