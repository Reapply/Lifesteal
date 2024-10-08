package net.meadownetwork.lifesteal.DiscordReportSystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CommandReport implements CommandExecutor {
    private final CooldownManager cooldownManager = new CooldownManager();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if ((s.equalsIgnoreCase("report")) || (s.equalsIgnoreCase("helpop"))) {
            String message = "";
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;

                if (strings.length <= 0) {
                    player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "[Meadow]: " + ChatColor.GRAY + "Usage: " + ChatColor.GOLD + "/" + s.toString() + " <player> [reason]");
                    return true;
                } else if (strings.length == 1) {
                    player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "[Meadow]: " + ChatColor.GRAY + "Please provide a reason.");
                    return true;
                } else if (strings.length >= 2) {
                    if (Bukkit.getPlayerExact(strings[0]) == null) {
                        player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "[Meadow]: " + ChatColor.RED + "The player " + ChatColor.GOLD + strings[0] + ChatColor.RED + " cannot be found.");
                        return true;
                    }
                    if (Bukkit.getPlayerExact(strings[0]) == player) {
                        player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "[Meadow]: " + ChatColor.RED + "You cannot report yourself!");
                        return true;
                    }

                    long timeLeft = System.currentTimeMillis() - cooldownManager.getCooldown(player.getUniqueId());
                    if(TimeUnit.MILLISECONDS.toSeconds(timeLeft) >= CooldownManager.DEFAULT_COOLDOWN){
                        cooldownManager.setCooldown(player.getUniqueId(), (int) System.currentTimeMillis());
                    }else{
                        player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "[Meadow]: " + ChatColor.RED + "Please wait " + ChatColor.GOLD + (TimeUnit.MILLISECONDS.toSeconds(timeLeft) - CooldownManager.DEFAULT_COOLDOWN) + " seconds" + ChatColor.RED + " before reporting anyone again.");
                    }

                    for (int i = 1; i < strings.length; i++) {
                        message = message + strings[i] + " ";
                    }
                    Player target = Bukkit.getPlayerExact(strings[0]);

                    Bukkit.broadcast(ChatColor.RED + "" + ChatColor.BOLD + "Report [Meadow]: " + ChatColor.GREEN + player.getName() + ChatColor.GRAY + " has reported " + ChatColor.RED + target.getName() + ChatColor.GRAY + " for " + ChatColor.GOLD + "'" + message + "'", "report.see");

                    // This dosent even work so dont try it :PPPPP
                    DiscordWebhook webhook = new DiscordWebhook("https://discord.com/api/webhooks/989772109989441537/u82J93Bw3raCjSPLaMbeyzxV8M4may-R1YX9VFhKfI15r1SywoZa8V");
                    webhook.setAvatarUrl("https://i.imgur.com/oaJxP4v.gif");
                    webhook.setUsername("Meadow Reports");
                    webhook.addEmbed(new DiscordWebhook.EmbedObject()
                            .setTitle("A new report has been submitted!")
                            .setDescription(message)
                            .setColor(Color.YELLOW)
                            .setThumbnail("https://minotar.net/avatar/" + target.getUniqueId() + "/100.png")
                            .setFooter("Reported by " + player.getName(), "https://minotar.net/avatar/" + player.getUniqueId() + "/100.png")
                            .setAuthor(target.getName(), null, "https://minotar.net/avatar/" + target.getUniqueId() + "/100.png"));

                    try {
                        webhook.execute();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    commandSender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Report [Meadow]: " + ChatColor.GREEN + "Your report has been successfully submitted, thank you for your patience!");

                    return true;
                }
            } else {
                commandSender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "[Meadow]: " + ChatColor.GRAY + "Only players can use this command");
                return true;
            }
        }
        return true;
    }
}

