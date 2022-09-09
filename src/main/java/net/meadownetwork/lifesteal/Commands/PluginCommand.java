package net.meadownetwork.lifesteal.Commands;

import org.bukkit.command.CommandExecutor;

public class PluginCommand implements CommandExecutor {
    // if a player dose /plugins they will be given a hello message
    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("plugins")) {
            sender.sendMessage("Our core is developed by MeadowNetwork's developers!");
        }
        return true;
    }
}
