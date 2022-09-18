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

            // Set the items in the inventory to a Honey Bottle and set the lore to display some information
            ItemStack honeyBottle = new ItemStack(org.bukkit.Material.HONEY_BOTTLE);
            org.bukkit.inventory.meta.ItemMeta honeyBottleMeta = honeyBottle.getItemMeta();
            honeyBottleMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Help Menu");
            // Set the lore of the honey to be a "Click here if you have any questions about the server"
            honeyBottleMeta.setLore(java.util.Arrays.asList(ChatColor.GOLD + "Click here if you have any questions about the server"));

            honeyBottle.setItemMeta(honeyBottleMeta);

            // Set the item in the inventory to the honey bottle in the middle of the inventory
            helpGUI.setItem(4, honeyBottle);

            player.openInventory(helpGUI);
        }
        return true;
    }
}
