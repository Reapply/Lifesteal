package net.meadownetwork.lifesteal.RTP;

import net.meadownetwork.lifesteal.Utils.CombatLogger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRtp implements CommandExecutor {
    // When the player types /rtp, they will be teleported to a random position in the world above the grass

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {

                //Safe Location that has been generated
                Location randomLocation = TeleportUtils.findSafeLocation(player);
                // If the player is in the world "LifeSteal" teleport them to the random location if they are not in the world "LifeSteal" teleport them to the world "LifeSteal"
                if (player.getWorld().getName().equals("LifeSteal")) {
                    player.teleport(randomLocation);
                } else {
                    // Send them a message saying "Looks like you are not in the world "LifeSteal" so we have teleported you to the correct world" please execute the command again
                    player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "[Meadow] " + ChatColor.RESET + ChatColor.RED + "Looks like you are not in the world " + ChatColor.GOLD + "LifeSteal" + ChatColor.RED + " so we have teleported you to the correct world, please execute the command again");
                    //player.teleport(Bukkit.getWorld("LifeSteal").getSpawnLocation(randomLocation));
                    player.teleport(Bukkit.getWorld("LifeSteal").getSpawnLocation());

                }


            }
        }

        return true;
    }
}

