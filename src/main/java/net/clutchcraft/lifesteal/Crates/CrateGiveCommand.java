package net.clutchcraft.lifesteal.Crates;

import net.clutchcraft.lifesteal.Utils.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;

public class CrateGiveCommand implements CommandExecutor {
    // When the player executes the command hearitemgiver give them the item stack CommonHeart
    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        // When the player dose /customheartgiver give them the item stack CommonHeart
        if (cmd.getName().equalsIgnoreCase("crategive")) {
            if (sender.hasPermission("lifesteal.crategive")) {
                sender.sendMessage(ChatColor.GRAY + "" + "You have been given a Common Crate!");
                // add the common heart to the player's inventory
                ((org.bukkit.entity.Player) sender).getInventory().addItem(ItemManager.commoncrate);
            } else {
                sender.sendMessage("You do not have permission to use this command!");
            }
        }
        return true;
    }
}
