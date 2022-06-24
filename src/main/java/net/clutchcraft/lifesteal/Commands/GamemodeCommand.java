package net.clutchcraft.lifesteal.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;

public class GamemodeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (sender instanceof org.bukkit.entity.Player) {
            org.bukkit.entity.Player player = (org.bukkit.entity.Player) sender;
            //Let the user be able to use the command on other players
            //Check if the user has permission to use the command


            if (player.hasPermission("lifesteal.gamemode")) {
                // If the player types /gm <gamemode> then set the gamemode to the specified gamemode
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("0")) {
                        player.setGameMode(org.bukkit.GameMode.SURVIVAL);
                        player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD +  "[Meadow] " + ChatColor.RESET + ChatColor.GREEN + "Your gamemode has been set to survival");

                    } else if (args[0].equalsIgnoreCase("s")) {
                        player.setGameMode(org.bukkit.GameMode.SURVIVAL);
                        player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD +  "[Meadow] " + ChatColor.RESET + ChatColor.GREEN + "Your gamemode has been set to survival");

                    } else if (args[0].equalsIgnoreCase("1")) {
                        player.setGameMode(org.bukkit.GameMode.CREATIVE);
                        player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD +  "[Meadow] " + ChatColor.RESET + ChatColor.GREEN + "Your gamemode has been set to creative");

                    } else if (args[0].equalsIgnoreCase("c")) {
                        player.setGameMode(org.bukkit.GameMode.CREATIVE);
                        player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD +  "[Meadow] " + ChatColor.RESET + ChatColor.GREEN + "Your gamemode has been set to creative");

                    } else if (args[0].equalsIgnoreCase("2")) {
                        player.setGameMode(org.bukkit.GameMode.ADVENTURE);
                        player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD +  "[Meadow] " + ChatColor.RESET + ChatColor.GREEN + "Your gamemode has been set to adventure");

                    } else if (args[0].equalsIgnoreCase("3")) {
                        player.setGameMode(org.bukkit.GameMode.SPECTATOR);
                        player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD +  "[Meadow] " + ChatColor.RESET + ChatColor.GREEN + "Your gamemode has been set to spectator");

                    } else {
                        player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD +  "[Meadow] " + ChatColor.RESET + ChatColor.RED + "Invalid gamemode");
                    }
                } else {
                    player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD +  "[Meadow] " + ChatColor.RESET + ChatColor.RED + "Please specify a gamemode");


                }

            } else {
                player.sendMessage(ChatColor.RED + "You do not have permission to use this command");
            }
        }
        return true;
    }

}
