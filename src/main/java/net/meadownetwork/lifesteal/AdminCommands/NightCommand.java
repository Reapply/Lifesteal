package net.meadownetwork.lifesteal.AdminCommands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;

public class NightCommand implements CommandExecutor {
    // When the player executes the command night set the time to night
    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        // When the player dose /night set the time to night
        if (cmd.getName().equalsIgnoreCase("night")) {
            if (sender.hasPermission("lifesteal.time")) {
                sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "[Meadow] " + ChatColor.RESET + ChatColor.WHITE + "Time set to night!");
                // set the time to night
                ((org.bukkit.entity.Player) sender).getWorld().setTime(13000);
            } else {
                sender.sendMessage("You do not have permission to use this command!");
            }
        }
        return true;
    }
}
