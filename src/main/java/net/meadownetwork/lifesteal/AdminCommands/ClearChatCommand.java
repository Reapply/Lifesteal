package net.meadownetwork.lifesteal.AdminCommands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;

public class ClearChatCommand implements CommandExecutor {
    // Register command to clear the chat
    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("clearchat")) {
            if (sender.hasPermission("lifesteal.admin")) {
                for (int i = 0; i < 100; i++) {
                    //Brodcast a blank message to clear the chat
                    org.bukkit.Bukkit.broadcastMessage("");
                }
                //Brodcast a message to tell the players the chat has been cleared
                org.bukkit.Bukkit.broadcastMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "[Meadow] " + ChatColor.RESET + ChatColor.WHITE + "Chat has been cleared!");
            } else {
                sender.sendMessage("You do not have permission to use this command!");
            }
        }
        return true;
    }
}