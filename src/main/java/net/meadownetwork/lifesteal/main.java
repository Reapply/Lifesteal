package net.meadownetwork.lifesteal;

import net.meadownetwork.lifesteal.AdminCommands.*;
import net.meadownetwork.lifesteal.ClearLag.clearlagcommand;
import net.meadownetwork.lifesteal.Commands.*;
import net.meadownetwork.lifesteal.Crates.RareCrateListener;
import net.meadownetwork.lifesteal.Listeners.*;
import net.meadownetwork.lifesteal.StaffCommands.GamemodeCommand;
import net.meadownetwork.lifesteal.Crates.CrateGiveCommand;
import net.meadownetwork.lifesteal.Crates.CommonCrateListener;
import net.meadownetwork.lifesteal.CustomHeartItems.CustomHeartAdders;
import net.meadownetwork.lifesteal.CustomHeartItems.HeartItemGiver;
import net.meadownetwork.lifesteal.DiscordReportSystem.CommandReport;
import net.meadownetwork.lifesteal.MaintenanceMode.MaintenanceModeCommand;
import net.meadownetwork.lifesteal.MaintenanceMode.MaintenanceModeTab;
import net.meadownetwork.lifesteal.RTP.CommandRtp;
import net.meadownetwork.lifesteal.RTP.TeleportUtils;
import net.meadownetwork.lifesteal.StaffCommands.VanishCommand;
import net.meadownetwork.lifesteal.Utils.CombatLogger;
import net.meadownetwork.lifesteal.Utils.ItemManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin {


  @Override
  public void onEnable() {
    // Plugin startup logic
    getLogger().info("Lifesteal enabled!");

    TeleportUtils yeet = new TeleportUtils(this);
    ItemManager.init();

    getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
    getServer().getPluginManager().registerEvents(new ServerListListener(), this);
    getServer().getPluginManager().registerEvents(new CustomHeartAdders(), this);
    getServer().getPluginManager().registerEvents(new JumpscareListener(), this);
    getServer().getPluginManager().registerEvents(new CommonCrateListener(), this);
    getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
    getServer().getPluginManager().registerEvents(new SetSpawnListener(), this);
    getServer().getPluginManager().registerEvents(new SpawnArmorStandListener(), this);
    getServer().getPluginManager().registerEvents(new RareCrateListener(), this);
    getServer().getPluginManager().registerEvents(new CombatLogger(), this);
    getServer().getPluginManager().registerEvents(new MuteChatCommand(), this);

    getCommand("resethealth").setExecutor(new ResetHealthCommand());
    getCommand("gm").setExecutor(new GamemodeCommand());
    getCommand("fly").setExecutor(new FlyCommand());
    getCommand("report").setExecutor(new CommandReport());
    getCommand("rtp").setExecutor(new CommandRtp());
    getCommand("customheartgiver").setExecutor(new HeartItemGiver());
    getCommand("maintenancemode").setExecutor(new MaintenanceModeCommand());
    getCommand("maintenanceMode").setTabCompleter(new MaintenanceModeTab());
    getCommand("crategive").setExecutor(new CrateGiveCommand());
    getCommand("vanish").setExecutor(new VanishCommand());
    getCommand("setspawn").setExecutor(new SetSpawnCommand());
    getCommand("spawn").setExecutor(new SpawnCommand());
    getCommand("playerinfo").setExecutor(new PlayerInfoCommand());
    getCommand("discord").setExecutor(new DiscordCommand());
    getCommand("clearlag").setExecutor(new clearlagcommand());
    getCommand("day").setExecutor(new DayCommand());
    getCommand("night").setExecutor(new NightCommand());
    getCommand("msg").setExecutor(new MsgCommand());
    getCommand("mutechat").setExecutor(new MuteChatCommand());
    getCommand("sethealth").setExecutor(new SetHealthCommand());
    getCommand("clearchat").setExecutor(new ClearChatCommand());
    getCommand("plugins").setExecutor(new PluginCommand());

    getConfig().options().copyDefaults();
    saveDefaultConfig();
  }



  @Override
  public void onDisable() {
    // Plugin shutdown logic
    getLogger().info("Lifesteal disabled!");
  }
}

