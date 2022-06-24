package net.clutchcraft.lifesteal.RankSystem;

import org.bukkit.ChatColor;
// Import bungee color
import net.md_5.bungee.api.ChatMessageType;

public enum Rank {

    OWNER(ChatColor.DARK_RED + "[Owner] ", new String[]{"lifesteal.gamemode"}),

    ADMIN(ChatColor.RED + "[Admin] ", new String[]{"lifesteal.gamemode"}),

    MOD(ChatColor.LIGHT_PURPLE + "[Mod] ", new String[]{}),

    VIP(ChatColor.GOLD + "[VIP] ", new String[]{}),

    PLAYER(ChatColor.GRAY + "[Player] ", new String[]{});

    private String display;
    private String[] permissions;

    Rank(String display, String[] permissions) {
        this.display = display;
        this.permissions = permissions;
    }

    public String getDisplay() {
        return display;
    }

    public String[] getPermissions() {
        return permissions;
    }
}


