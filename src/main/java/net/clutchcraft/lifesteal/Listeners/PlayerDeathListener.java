package net.clutchcraft.lifesteal.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerDeathListener implements Listener {

  @EventHandler
  public void onPlayerDeath(PlayerDeathEvent event) {
    Player player = event.getEntity().getPlayer();
    // get if the player respawns
    // if the players health is -0 or less, then they died
    if (player.getHealth() <= 0) {
      player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD +  "[Meadow] " + ChatColor.RESET + ChatColor.RED + "You died! " + event.getEntity().getKiller().getDisplayName() + " killed you!");
      player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() - 2.0); // Has to be 2.0 because 1.0 only removes half a heart
      player.getWorld().strikeLightning(player.getLocation());
      // if a player is stuck by lightning, do not damage them

      //If the players max health is below 0 set the player into spectator mode
      double value =
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() - 1 - player.getHealth();
      if (value < 0) {
          player.setGameMode(org.bukkit.GameMode.SPECTATOR);
      }


      // If the players max health is above 50 then dont add any more hearts after kills
      if (player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() > 50) {
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50.0);
      }
    }


    // If a player kills another player give that player 1 heart and tell the player who they killed and tell them they gained 1 heart
    if (player.getKiller() instanceof Player) {
      Player killer = player.getKiller();
      killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() + 2.0);
      killer.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD +  "[Meadow] " + ChatColor.RESET + ChatColor.GREEN + "You killed " + player.getDisplayName() + " and gained 1 heart!");
      killer.setHealth(killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
    }
    // if the player dies by fall damage remove 1 heart
    if (player.getLastDamageCause().getCause() == org.bukkit.event.entity.EntityDamageEvent.DamageCause.FALL) {
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() - 2.0);
    }
  }
}
