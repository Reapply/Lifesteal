package net.meadownetwork.lifesteal.AdminCommands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;

public class SetSpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if (sender instanceof org.bukkit.entity.Player) {

            if (sender.hasPermission("lifesteal.admin")) {
                // When a player executes this command set the server spawn point to the player's location
                org.bukkit.entity.Player player = (org.bukkit.entity.Player) sender;
                player.getWorld().setSpawnLocation(player.getLocation());
                player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD +  "[Meadow] " + ChatColor.RESET + ChatColor.GREEN + "Spawn point set!");
            } else {
                sender.sendMessage("You do not have permission to use this command");
            }

        }
        return true;

    }
}