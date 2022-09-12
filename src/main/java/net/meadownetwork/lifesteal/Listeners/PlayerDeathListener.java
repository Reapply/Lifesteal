package net.meadownetwork.lifesteal.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerDeathListener implements Listener {

  @EventHandler
  public void onDeath(org.bukkit.event.entity.PlayerDeathEvent event) {
    // Get the killer and the player
    org.bukkit.entity.Player player = event.getEntity();
    org.bukkit.entity.Player killer = player.getKiller();

    // If the player was killed by a killer remove 1 heart from the player and give the killer 1 heart using the attribute system
    if (killer != null) {
      player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() - 2);
      killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() + 2);
    } else {
      // If the player was killed by something else remove 1 heart from the player
      player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() - 2);
    }
    // if their is no killer send a message saying they died
    if (killer != null) {
      killer.sendMessage(org.bukkit.ChatColor.GOLD + "" + org.bukkit.ChatColor.BOLD + "[Meadow] " + org.bukkit.ChatColor.RESET + org.bukkit.ChatColor.GREEN + "You killed " + player.getDisplayName() + "! You now have " + killer.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).getBaseValue() / 2 + " hearts!");
    } else {
      player.sendMessage(org.bukkit.ChatColor.GOLD + "" + org.bukkit.ChatColor.BOLD + "[Meadow] " + org.bukkit.ChatColor.RESET + org.bukkit.ChatColor.RED + "You died! You now have " + player.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).getBaseValue() / 2 + " hearts!");
    }
    if (killer != null) {
      Bukkit.broadcastMessage(org.bukkit.ChatColor.RED + "" + org.bukkit.ChatColor.BOLD + "[\uD83D\uDDE1] " + org.bukkit.ChatColor.RED + player.getDisplayName() + " was killed by " + killer.getDisplayName() + "! They now have " + player.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).getBaseValue() / 2 + " hearts!");
    } else {
      Bukkit.broadcastMessage(org.bukkit.ChatColor.RED + "" + org.bukkit.ChatColor.BOLD + "[\uD83D\uDDE1] " + org.bukkit.ChatColor.RED + player.getDisplayName() +"" + ChatColor.RED+ " died! They now have " + player.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).getBaseValue() / 2 + " hearts!");
    }
    // if the player has 5 hearts dont remove any more hearts
    if (player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() <= 10) {
      player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(10);
    }
    // If they have more then 20 hearts dont add any more hearts
    if (killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() >= 40) {
      killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
    }
    if (killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() <= 10) {
      killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(10);
    }
    if (player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() <= 40) {
      player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
    }
  }
}
