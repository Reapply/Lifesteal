package net.clutchcraft.lifesteal.utils;

import net.clutchcraft.lifesteal.main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BlockManager {
    public static ItemStack CommonHeart;

    public static void init() {
        createCommonHeart();
    }

    // Create the common heart item stack
    private static void createCommonHeart() {
        ItemStack item = new ItemStack(Material.BEDROCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Common Heart");
        List<String> lore = new ArrayList<>();
        lore.add("");
        // Add to the lore that  it adds 1 heart to the player
        lore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "Statistics");
        lore.add("");
        lore.add(ChatColor.GREEN + "" + ChatColor.BOLD + " ▏" + ChatColor.RESET + " Added Hearts" + ChatColor.GRAY + ":" + ChatColor.WHITE + " 1");

        meta.setLore(lore);
        meta.addEnchant(Enchantment.PROTECTION_FIRE, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        CommonHeart = item;
    }
}
