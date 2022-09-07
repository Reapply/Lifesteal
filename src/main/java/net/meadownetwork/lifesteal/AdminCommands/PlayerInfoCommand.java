package net.meadownetwork.lifesteal.AdminCommands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;

public class PlayerInfoCommand implements CommandExecutor {
    // When the player executes this command, display the target the player used the command on
    // Send the player a message saying Here is the following players info:

    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if (sender instanceof org.bukkit.entity.Player) {
            org.bukkit.entity.Player player = (org.bukkit.entity.Player) sender;
            if (player.hasPermission("lifesteal.admin")) {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "[Meadow] " + ChatColor.RESET + ChatColor.GREEN + "You must specify a player");
                } else {
                    org.bukkit.entity.Player target = player.getServer().getPlayer(args[0]);
                    if (target != null) {
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + ChatColor.RESET + ChatColor.GREEN + "Here is " + target.getPlayer().getName() + "'s info:");
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + ChatColor.STRIKETHROUGH + "---------------------------------------------");
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "! " + ChatColor.RESET + ChatColor.GREEN + "Name: " + target.getDisplayName());
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "! " + ChatColor.RESET + ChatColor.GREEN + "UUID: " + target.getUniqueId());
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "! " + ChatColor.RESET + ChatColor.GREEN + "Health: " + target.getHealth());
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "! " + ChatColor.RESET + ChatColor.GREEN + "Food: " + target.getFoodLevel());
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "! " + ChatColor.RESET + ChatColor.GREEN + "Gamemode: " + target.getGameMode());
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "! " + ChatColor.RESET + ChatColor.GREEN + "Location: " + // Get the players x, y, and z coordinates
                                target.getLocation().getBlockX() + ", " + target.getLocation().getBlockY() + ", " + target.getLocation().getBlockZ());
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "! " + ChatColor.RESET + ChatColor.GREEN + "Is Online: " + target.isOnline());
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "! " + ChatColor.RESET + ChatColor.GREEN + "Is Op: " + target.isOp());
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "! " + ChatColor.RESET + ChatColor.GREEN + "Is Invulnerable: " + target.isInvulnerable());
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "! " + ChatColor.RESET + ChatColor.GREEN + "IP Address: " + target.getAddress());
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + ChatColor.STRIKETHROUGH + "---------------------------------------------");
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "! " + ChatColor.RESET + ChatColor.DARK_RED + "NOTE: LEAKING ANY OF THIS INFO WILL RESULT IN A DEMOTION AND BLACKLIST FROM THE SERVER");
                    } else {
                        player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "[Meadow] " + ChatColor.RESET + ChatColor.GREEN + "Player not found");
                        // If the player dose not have permission to use this command, send them a message saying they do not have permission
                    }
                }
            } else {
                player.sendMessage(ChatColor.RED + "You do not have permission to use this command");
            }
        } else {
            sender.sendMessage("You must be a player to use this command");
        }
        return true;
    }
}


