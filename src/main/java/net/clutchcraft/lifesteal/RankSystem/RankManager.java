package net.clutchcraft.lifesteal.RankSystem;

import net.clutchcraft.lifesteal.main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class RankManager {

    private main Main;

    private File file;
    private YamlConfiguration config;

    private HashMap<UUID, PermissionAttachment> perms = new HashMap<>();

    public RankManager(main Main) {
        this.Main = Main;
        if (!Main.getDataFolder().exists()) {
            Main.getDataFolder().mkdir();
        }

        file = new File(Main.getDataFolder(), "ranks.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void setRank(UUID uuid, Rank rank, boolean firstjoin) {
        if (Bukkit.getOfflinePlayer(uuid).isOnline() && !firstjoin) {
            Player player = Bukkit.getPlayer(uuid);
            PermissionAttachment attachment;
            if (perms.containsKey(uuid)) {
                attachment = perms.get(uuid);
            } else {
                attachment = player.addAttachment(Main);
                perms.put(uuid, attachment);
            }

            for (String perm : getRank(uuid).getPermissions()) {
                if (player.hasPermission(perm)) {
                    attachment.unsetPermission(perm);
                }
            }
            for (String perm : rank.getPermissions()) {
                attachment.setPermission(perm, true);
            }
        }

        config.set(uuid.toString(), rank.name());
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (Bukkit.getOfflinePlayer(uuid).isOnline()) {
            Player player = Bukkit.getPlayer(uuid);
            Main.getNameTagManager().removeTag(player);
            Main.getNameTagManager().newTag(player);

        }

    }

    public Rank getRank(UUID uuid) {
        return Rank.valueOf(config.getString(uuid.toString()));


    }

    public HashMap<UUID, PermissionAttachment> getPerms() {
        return perms;
    }
}








