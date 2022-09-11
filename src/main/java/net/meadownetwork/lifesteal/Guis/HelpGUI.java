package net.meadownetwork.lifesteal.Guis;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class HelpGUI implements CommandExecutor {

    @Override
    public boolean onCommand(org.bukkit.command.CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if (commandSender instanceof Player player) {
            Inventory helpGUI = Bukkit.createInventory(player, 9, ChatColor.GOLD.toString() + "" + ChatColor.BOLD + "Help Menu");
            // This inventory will be used to display some information about the server and how to play

            // Set the items in the inventory
            helpGUI.setItem(0, new ItemStack(org.bukkit.Material.DIAMOND_SWORD));
            helpGUI.setItem(1, new ItemStack(org.bukkit.Material.DIAMOND_SWORD));

            player.openInventory(helpGUI);
        }
        return true;
    }
}
