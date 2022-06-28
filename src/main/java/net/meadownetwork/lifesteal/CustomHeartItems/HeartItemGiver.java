package net.meadownetwork.lifesteal.CustomHeartItems;

import net.meadownetwork.lifesteal.Utils.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;

public class HeartItemGiver implements CommandExecutor {
    // When the player executes the command hearitemgiver give them the item stack CommonHeart
    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        // When the player dose /customheartgiver give them the item stack CommonHeart
        if (cmd.getName().equalsIgnoreCase("customheartgiver")) {
            if (sender.hasPermission("lifesteal.heartitemgiver.customheartgiver")) {
                sender.sendMessage(ChatColor.BLUE + "" + "You have been given a Common Heart!");
                // add the common heart to the player's inventory
                ((org.bukkit.entity.Player) sender).getInventory().addItem(ItemManager.commonheart);
            } else {
                sender.sendMessage("You do not have permission to use this command!");
            }
        }
        return true;
    }
}
