package net.clutchcraft.lifesteal.MaintenanceMode;

import com.google.common.base.Strings;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaintenanceModeTab implements TabCompleter {


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {


        if (args.length == 1) {

            return StringUtil.copyPartialMatches(args[0], Arrays.asList("disable", "enable"), new ArrayList<>());

        } else {
            return new ArrayList<>();
        }
    }
}



