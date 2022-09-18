package net.meadownetwork.lifesteal.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import net.meadownetwork.lifesteal.Utils.CombatLogger;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        // If the player dose /spawn teleport them to the servers spawn point
        // If the player is fighting a player they will not be teleported to the spawn point

        if (sender instanceof org.bukkit.entity.Player) {
            org.bukkit.entity.Player player = (org.bukkit.entity.Player) sender;
            // Teleport the player to the world with the name "world" and get the spawn point of that world and teleport the player to that location
            player.teleport(player.getServer().getWorld("world").getSpawnLocation());

            // If the player is in combat do not teleport them to the spawn point
            if (CombatLogger.isInCombat) {
                player.sendMessage(ChatColor.RED + "You are in combat and cannot teleport to spawn");
            }


            player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD +  "[Meadow] " + ChatColor.RESET + ChatColor.GREEN + "You have been teleported to spawn!");
        } else {
            sender.sendMessage("You must be a player to use this command");
        }
        return true;
    }
}
