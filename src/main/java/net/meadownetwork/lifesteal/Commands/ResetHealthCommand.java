package net.meadownetwork.lifesteal.Commands;

import org.bukkit.command.CommandExecutor;

public class ResetHealthCommand implements CommandExecutor {

  // Register command to reset the players health to 20
  @Override
  public boolean onCommand(
    org.bukkit.command.CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
    if (sender instanceof org.bukkit.entity.Player) {
      org.bukkit.entity.Player player = (org.bukkit.entity.Player) sender;
      //Let the user be able to use the command on other players
      //Check if the user has permission to use the command


      if (player.hasPermission("lifesteal.resethealth")) {
        if (args.length == 0) {
          player.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).setBaseValue(20.0);
          player.sendMessage("Your health has been reset to 20");
        } else {
          org.bukkit.entity.Player target = player.getServer().getPlayer(args[0]);
          if (target != null) {
            target.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).setBaseValue(20.0);
            // Send the target a message saying who reset their health
            target.sendMessage(player.getDisplayName() + " has reset your health");
          } else {
            player.sendMessage("Player not found");
          }
        }
        //player.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).setBaseValue(20.0);
        //player.setHealth(20.0);
        //player.setGameMode(org.bukkit.GameMode.SURVIVAL);
      } else {
        player.sendMessage("You do not have permission to use this command");
      }
    }
    return true;
  }
}
