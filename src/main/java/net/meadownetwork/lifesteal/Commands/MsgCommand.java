package net.meadownetwork.lifesteal.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;

public class MsgCommand implements CommandExecutor {
    // When a player dose /msg (player) (message) send a private message to the player they specified
    // If the player dose not specify a player or a message send them a message telling them how to use the command
    // If the player dose not specify a message send them a message telling them how to use the command
    // Make it so they can tab complete the players name



    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if (sender instanceof org.bukkit.entity.Player) {
            org.bukkit.entity.Player player = (org.bukkit.entity.Player) sender;
            if (args.length == 0) {
                player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD +  "[Meadow] " + ChatColor.RESET + ChatColor.RED + "You must specify a player to send a message to!");
            } else if (args.length == 1) {
                player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD +  "[Meadow] " + ChatColor.RESET + ChatColor.RED + "You must specify a message to send!");
            } else {
                org.bukkit.entity.Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD +  "[Meadow] " + ChatColor.RESET + ChatColor.RED + "That player is not online!");
                } else {
                    StringBuilder message = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        message.append(args[i]).append(" ");
                    }
                    target.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD +  "[Meadow] " + ChatColor.RESET + ChatColor.GREEN + "From " + player.getDisplayName() + ": " + message);
                    player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD +  "[Meadow] " + ChatColor.RESET + ChatColor.GREEN + "To " + target.getDisplayName() + ": " + message);
                }
            }
        } else {
            sender.sendMessage("You must be a player to use this command");
        }
        return true;
    }
}
