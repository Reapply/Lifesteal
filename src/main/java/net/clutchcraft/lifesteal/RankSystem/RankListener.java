package net.clutchcraft.lifesteal.RankSystem;

import net.clutchcraft.lifesteal.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;


public class RankListener implements Listener {

    private main Main;

    public RankListener(main Main) {
        this.Main = Main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPlayedBefore()) {
            Main.getRankManager().setRank(player.getUniqueId(), Rank.PLAYER, true);
        }

        Main.getNameTagManager().setNametags(player);
        Main.getNameTagManager().newTag(player);

        PermissionAttachment attachment;
        if (Main.getRankManager().getPerms().containsKey(player.getUniqueId())) {
            attachment = Main.getRankManager().getPerms().get(player.getUniqueId());
        } else {
            attachment = player.addAttachment(Main);
            Main.getRankManager().getPerms().put(player.getUniqueId(), attachment);
        }
        for (String perm : Main.getRankManager().getRank(player.getUniqueId()).getPermissions()) {
            attachment.setPermission(perm, true);
        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Main.getNameTagManager().removeTag(event.getPlayer());
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);
        Player player = event.getPlayer();

        Bukkit.broadcastMessage(Main.getRankManager().getRank(player.getUniqueId()).getDisplay() +  player.getName() + ": " + ChatColor.WHITE + event.getMessage());
    }
}
