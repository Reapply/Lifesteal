package net.meadownetwork.lifesteal.AdminCommands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;

public class DayCommand implements CommandExecutor {
    // When the player executes the command day set the time to day
    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        // When the player dose /day set the time to day
        if (cmd.getName().equalsIgnoreCase("day")) {
            if (sender.hasPermission("lifesteal.time")) {
                sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "[Meadow] " + ChatColor.RESET + ChatColor.WHITE + "Time set to day!");
                // set the time to day
                ((org.bukkit.entity.Player) sender).getWorld().setTime(1000);
            } else {
                sender.sendMessage("You do not have permission to use this command!");
            }
        }
        return true;
    }
}
