package net.meadownetwork.lifesteal.StaffCommands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;

public class VanishCommand implements CommandExecutor {
    // if the player executes the command vanish they will be able to go invisible
    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if (sender instanceof org.bukkit.entity.Player) {
            org.bukkit.entity.Player player = (org.bukkit.entity.Player) sender;

            // Make a command that is toggled on and off when the player executes the command
            // Gamemode dose not matter
            // Make the player invisible using player.setinvisible
            // If the player executes the command again they will be visible again
            if (player.hasPermission("lifesteal.helper")) {
                if (args.length == 0) {
                    if (player.isInvisible()) {
                        player.setInvisible(false);
                        player.setAllowFlight(false);
                        player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD +  "[Meadow] " + ChatColor.RESET + ChatColor.RED + "You are no longer in vanish");
                    } else {
                        player.setInvisible(true);
                        player.setAllowFlight(true);
                        player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD +  "[Meadow] " + ChatColor.RESET + ChatColor.GREEN + "You are now in vanish");
                        // Remove the player from the tab list
                    }
                } else {
                    org.bukkit.entity.Player target = player.getServer().getPlayer(args[0]);
                    if (target != null) {
                        if (target.isInvisible()) {
                            target.setInvisible(false);
                            player.setAllowFlight(false);
                            target.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD +  "[Meadow] " + player.getDisplayName() + " has made you visible");
                        } else {
                            target.setInvisible(true);
                            player.setAllowFlight(true);
                            target.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD +  "[Meadow] " + player.getDisplayName() + " has made you invisible");
                        }
                    } else {
                        player.sendMessage("Player not found");
                    }
                }
            } else {
                player.sendMessage("You do not have permission to use this command");
            }

        }
        return true;
    }
}