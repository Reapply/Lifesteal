package net.meadownetwork.lifesteal.MaintenanceMode;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;

public class MaintenanceModeCommand implements CommandExecutor {
    // When the player executes the command maintenancemode set the server to maintence mode
    // If a player is not opped they will not be able to join the server
    // Make the command toggleable with the /maintenancemode command
    // Kick any Player that is not opped from the server
    // If the player dose /maintenancemode disable the maintenance mode will be disabled
    // If the player dose /maintenancemode enable the maintenance mode will be enabled
    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("maintenancemode")) {
            if (sender.hasPermission("lifesteal.maintenancemode")) {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "Usage: /maintenancemode <enable/disable>");
                } else if (args[0].equalsIgnoreCase("enable")) {
                    Bukkit.getServer().setWhitelist(true);
                    sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "[Meadow] " + ChatColor.RESET + ChatColor.GREEN + "Maintenance mode has been enabled!");
                } else if (args[0].equalsIgnoreCase("disable")) {
                    Bukkit.getServer().setWhitelist(false);
                    sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "[Meadow] " + ChatColor.RED + "Maintenance mode has been disabled!");
                    // When disabled change the motd to the default motd
                } else {
                    sender.sendMessage(ChatColor.RED + "Usage: /maintenancemode <enable/disable>");
                }
            } else {
                sender.sendMessage("You do not have permission to use this command!");
            }
        }
        return true;
    }
}

