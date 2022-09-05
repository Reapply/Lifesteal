package net.meadownetwork.lifesteal.ClearLag;

import net.meadownetwork.lifesteal.Utils.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;

public class clearlagcommand implements CommandExecutor {
    // When the player executes the command clear lag clear all the entities
    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        // When the player dose /clearlag clear all the entities
        if (cmd.getName().equalsIgnoreCase("clearlag")) {
            if (sender.hasPermission("lifesteal.clearlag")) {
                sender.sendMessage("Clearing all entities please wait...");
                // Wait 5 seconds
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Brodcast to the server a admin has cleared the lag
                org.bukkit.Bukkit.broadcastMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "[Meadow] " + ChatColor.RESET + ChatColor.WHITE + sender.getName() + " has cleared the lag!");
                // Display how many entities were cleared
                sender.sendMessage("Cleared " + org.bukkit.Bukkit.getWorld("world").getEntities().size() + " entities!");



                for (org.bukkit.entity.Entity entity : ((org.bukkit.entity.Player) sender).getWorld().getEntities()) {
                    if (!(entity instanceof org.bukkit.entity.Player)) {
                        entity.remove();
                    }
                    if (entity.getCustomName() != null) {
                        entity.remove();
                    }
                }
            } else {
                sender.sendMessage("You do not have permission to use this command!");
            }
            // If the enity has a custom name don't remove it

        }
        return true;
    }
}

