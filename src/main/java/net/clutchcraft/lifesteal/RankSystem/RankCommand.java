package net.clutchcraft.lifesteal.RankSystem;

import net.clutchcraft.lifesteal.main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.StructureType;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public class RankCommand implements CommandExecutor {

    private main Main;

    public RankCommand(main Main) {
        this.Main = Main;
    }
    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.isOp()) {
                if (args.length == 2) {
                    if (Bukkit.getOfflinePlayer(args[0]) != null) {
                        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

                        for (Rank rank : Rank.values()) {
                            if (rank.name().equalsIgnoreCase(args[1])) {
                                Main.getRankManager().setRank(target.getUniqueId(), rank, false);
                                player.sendMessage("Set " + target.getName() + "'s rank to " + rank.getDisplay());
                                if (target.isOnline()) {
                                    Player targetPlayer = target.getPlayer();
                                    targetPlayer.sendMessage("Your rank has been set to " + rank.getDisplay());
                                }


                                return false;
                            }
                        }

                        player.sendMessage("Invalid rank");
                    } else {
                        player.sendMessage("Player not found");
                    }
                } else {
                    player.sendMessage("Usage: /rank <player> <rank>");
                }
            } else {
                player.sendMessage("You must be an OP to use this command");
            }

        }

        return true;
    }

}
