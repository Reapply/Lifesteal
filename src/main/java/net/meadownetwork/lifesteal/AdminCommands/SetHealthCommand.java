package net.meadownetwork.lifesteal.AdminCommands;

import org.bukkit.command.CommandExecutor;

public class SetHealthCommand implements CommandExecutor {
    // When the player executes the command sethealth (Player) (Health) set the health of the player
    // make players names tabbable
    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        // When the player dose /sethealth (Player) (Health) set the health attribute of the player
        if (cmd.getName().equalsIgnoreCase("sethealth")) {
            if (sender.hasPermission("lifesteal.admin")) {
                if (args.length == 2) {
                    org.bukkit.entity.Player target = org.bukkit.Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        try {
                            double health = Double.parseDouble(args[1]);
                            if (health > 0) {
                                target.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
                                sender.sendMessage("Set " + target.getName() + "'s health to " + health);
                            } else {
                                sender.sendMessage("Health must be greater than 0!");
                            }
                        } catch (NumberFormatException e) {
                            sender.sendMessage("Health must be a number!");
                        }
                    } else {
                        sender.sendMessage("Player not found!");
                    }
                } else {
                    sender.sendMessage("Usage: /sethealth (Player) (Health)");
                }
            } else {
                sender.sendMessage("You do not have permission to use this command!");
            }


        }
        return true;
    }
}

