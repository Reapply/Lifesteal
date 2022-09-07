package net.meadownetwork.lifesteal.AdminCommands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


public class MuteChatCommand implements CommandExecutor, Listener {
    boolean muted = false;

    @Override
    // If a admin dose /mutechat it will mute the chat for all players except for admins with the permission node meadow.chatmutebyass
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("mutechat")) {
            if (sender.hasPermission("meadow.chatmute")) {
                if (muted == false) {
                    muted = true;
                    sender.sendMessage(ChatColor.RED + "Chat has been muted!");
                    // Brodcast to all players that the chat has been muted
                    org.bukkit.Bukkit.broadcastMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "[Meadow] " + ChatColor.RESET + ChatColor.RED + "Chat has been muted by " + sender.getName());
                } else {
                    muted = false;
                    sender.sendMessage(ChatColor.GREEN + "Chat has been unmuted!");
                    // Brodcast to all players that the chat has been unmuted
                    org.bukkit.Bukkit.broadcastMessage(ChatColor.GOLD +  "" + ChatColor.BOLD + "[Meadow] " + ChatColor.RESET + ChatColor.GREEN + "Chat has been unmuted by " + sender.getName());
                }
            } else {
                sender.sendMessage(ChatColor.RED + "You do not have permission to mute the chat!");
            }

        }
        return true;
    }
    @EventHandler
    // If the chat is muted and a player dose not have the permission node meadow.chatmutebyass they will not be able to chat
    public void onChat(org.bukkit.event.player.AsyncPlayerChatEvent event) {
        if (muted == true) {
            if (!event.getPlayer().hasPermission("meadow.chatmutebyass")) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.RED + "Chat is muted!");
            }
        }
    }
}