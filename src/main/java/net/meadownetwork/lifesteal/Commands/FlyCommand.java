package net.meadownetwork.lifesteal.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;

public class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        // make fly toggleable
        if (sender instanceof org.bukkit.entity.Player) {
            org.bukkit.entity.Player player = (org.bukkit.entity.Player) sender;
            if (player.hasPermission("lifesteal.mod")) {
                if (args.length == 0) {
                    if (player.getAllowFlight()) {
                        player.setAllowFlight(false);
                        player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD +  "[Meadow] " + ChatColor.RESET + ChatColor.RED + "You are no longer flying");
                    } else {
                        player.setAllowFlight(true);
                        player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD +  "[Meadow] " + ChatColor.RESET + ChatColor.GREEN + "You are now flying");
                    }
                } else {
                    org.bukkit.entity.Player target = player.getServer().getPlayer(args[0]);
                    if (target != null) {
                        if (target.getAllowFlight()) {
                            target.setAllowFlight(false);
                            target.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD +  "[Meadow] " + ChatColor.RESET + player.getDisplayName() + " has stopped you from flying");
                        } else {
                            target.setAllowFlight(true);
                            target.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD +  "[Meadow] " + ChatColor.RESET + player.getDisplayName() + " has allowed you to fly");
                        }
                    } else {
                        player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD +  "[Meadow] " + ChatColor.RESET + "Player not found");
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
