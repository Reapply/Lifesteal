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
                    player.teleport(Bukkit.getWorld("LifeSteal").getSpawnLocation());
                    // then wait 10 seconds to detect if the player is in the correct world and then teleport them to the random location
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("LifeSteal"), new Runnable() {
                        @Override
                        public void run() {
                            if (player.getWorld().getName().equals("LifeSteal")) {
                                player.teleport(randomLocation);
                            }
                        }
                    }, 100L);

                }


            }
        }

        return true;
    }
}

