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

    // If killer is null return
    if (killer == null) {
      // remove the 1 heart from the player
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() - 2.0);
        // send the player a message saying they lost a heart and how many hearts they have left
        player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "[Meadow] " + ChatColor.RESET + ChatColor.RED + "You have lost a heart! You now have " + ChatColor.GOLD + (player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() / 2) + ChatColor.RED + " hearts left!");
    }


    if (player.getHealth() <= 0) {
      if (killer != null) {
        killer.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).setBaseValue(killer.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).getBaseValue() + 2.0);
        player.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).setBaseValue(player.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).getBaseValue() - 2.0);
      }
      // If the player has over 50 hearts do not give them any more hearts
      if (player.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).getBaseValue() > 50) {
        player.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).setBaseValue(50.0);
      }
      // Send a message to the player saying they died and that they now have "x" hearts
      player.sendMessage(org.bukkit.ChatColor.GOLD + "" + org.bukkit.ChatColor.BOLD + "[Meadow] " + org.bukkit.ChatColor.RESET + org.bukkit.ChatColor.RED + "You died! You now have " + player.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).getBaseValue() / 2 + " hearts!");
      // Send a message to the killer saying they killed the player and that they now have "x" hearts
      killer.sendMessage(org.bukkit.ChatColor.GOLD + "" + org.bukkit.ChatColor.BOLD + "[Meadow] " + org.bukkit.ChatColor.RESET + org.bukkit.ChatColor.GREEN + "You killed " + player.getName() + "! You now have " + killer.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).getBaseValue() / 2 + " hearts!");
    }
    // when the players hearts hits 0, execute the console command "kill <player>"
    if (player.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).getBaseValue() <= 0) {
      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tempban " + player.getName() + " 2h");
      // If the server cannot ban the player, then send a message to the console
      if (Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tempban " + player.getName() + " 2h") == false) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Could not ban " + player.getName() + "!");
      }
      // if Killer = null then send a message to the console saying that the player died and remove 1 heart

    }
  }
}
