package net.meadownetwork.lifesteal.Crates;

import net.meadownetwork.lifesteal.Utils.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;

public class CrateGiveCommand implements CommandExecutor {
    // When the player executes the command hearitemgiver give them the item stack CommonHeart
    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        // When the player dose /customheartgiver give them the item stack CommonHeart
        // If the player dose /crategive common give them a common crate if they do / crategive rare give them a rare crate
        if (cmd.getName().equalsIgnoreCase("crategive")) {
            if (sender.hasPermission("lifesteal.admin")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("common")) {
                        sender.sendMessage(ChatColor.BLUE + "" + "You have been given a Common Crate!");
                        // add the common crate to the player's inventory
                        ((org.bukkit.entity.Player) sender).getInventory().addItem(ItemManager.commoncrate);
                    } else if (args[0].equalsIgnoreCase("rare")) {
                        sender.sendMessage(ChatColor.BLUE + "" + "You have been given a Rare Crate!");
                        // add the rare crate to the player's inventory
                        ((org.bukkit.entity.Player) sender).getInventory().addItem(ItemManager.rarecrate);
                    } else {
                        sender.sendMessage(ChatColor.RED + "Invalid crate type");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Invalid crate type");
                }
            } else {
                sender.sendMessage("You do not have permission to use this command!");
            }
        }
        return true;
    }
}
        /*if (cmd.getName().equalsIgnoreCase("crategive")) {
            if (sender.hasPermission("lifesteal.admin")) {
                sender.sendMessage(ChatColor.GRAY + "" + "You have been given a Common Crate!");
                // add the common heart to the player's inventory
                ((org.bukkit.entity.Player) sender).getInventory().addItem(ItemManager.commoncrate);
            } else {
                sender.sendMessage("You do not have permission to use this command!");
            }
        }
        return true;
    }*/
