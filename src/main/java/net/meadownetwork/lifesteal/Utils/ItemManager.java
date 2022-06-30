package net.meadownetwork.lifesteal.Utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

// make 2 item stacks for the crates

    public static ItemStack commonheart;
    public static ItemStack commoncrate;
    public static  ItemStack rarecrate;


    public static void init() {
        createCommonHeart();
        createcrate();
        createcrarecrate();


    }

    private static void createCommonHeart() {
        ItemStack item = new ItemStack(Material.POPPY, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.GRAY + "[Common] " + ChatColor.RESET + "Heart Replenisher");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GOLD + "Item Ability:");
        lore.add(ChatColor.WHITE + "You will replenish 1" + ChatColor.RED + "❤");
        meta.setLore(lore);
        // add the enchantment to the item
        meta.addEnchant(Enchantment.ARROW_FIRE, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        commonheart = item;
    }


    private static void createcrate() {
        ItemStack item = new ItemStack(Material.CHEST, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.GRAY + "[Common] " + ChatColor.RESET + "Crate");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "[Common] ❤" + ChatColor.WHITE + "Open to receive one of these items:");
        lore.add(ChatColor.RED + "");
        lore.add(" - " + ChatColor.GRAY + "[Common] " + ChatColor.WHITE + "Heart Replenisher");
        lore.add(" - " + ChatColor.WHITE + "20" + ChatColor.AQUA + " Diamonds ");
        lore.add(" - " + ChatColor.WHITE + "1" + ChatColor.BLUE + " [Rare] Crate");
        meta.addEnchant(Enchantment.ARROW_FIRE, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(lore);
        item.setItemMeta(meta);
        commoncrate = item;


    }

    private static void createcrarecrate() {
        ItemStack item = new ItemStack(Material.CHEST, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.BLUE + "[Rare] " + ChatColor.RESET + "Crate");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.BLUE + "[Rare] ❤" + ChatColor.WHITE + "Open to receive one of these items:");
        lore.add(ChatColor.RED + "");
        lore.add(" - " + ChatColor.GRAY + "[Common] " + ChatColor.WHITE + "Heart Replenisher");
        lore.add(" - " + ChatColor.WHITE + "20" + ChatColor.AQUA + " Diamonds ");
        meta.addEnchant(Enchantment.ARROW_FIRE, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(lore);
        item.setItemMeta(meta);
        rarecrate = item;
    }
}
