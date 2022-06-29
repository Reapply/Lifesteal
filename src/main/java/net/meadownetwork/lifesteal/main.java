package net.meadownetwork.lifesteal;

import net.meadownetwork.lifesteal.AdminCommands.PlayerInfoCommand;
import net.meadownetwork.lifesteal.AdminCommands.SetSpawnCommand;
import net.meadownetwork.lifesteal.AdminCommands.SetSpawnListener;
import net.meadownetwork.lifesteal.Commands.FlyCommand;
import net.meadownetwork.lifesteal.Commands.SpawnCommand;
import net.meadownetwork.lifesteal.Listeners.PlayerJoinListener;
import net.meadownetwork.lifesteal.StaffCommands.GamemodeCommand;
import net.meadownetwork.lifesteal.AdminCommands.ResetHealthCommand;
import net.meadownetwork.lifesteal.Crates.CrateGiveCommand;
import net.meadownetwork.lifesteal.Crates.CrateGiverListener;
import net.meadownetwork.lifesteal.CustomHeartItems.CustomHeartAdders;
import net.meadownetwork.lifesteal.CustomHeartItems.HeartItemGiver;
import net.meadownetwork.lifesteal.DiscordReportSystem.CommandReport;
import net.meadownetwork.lifesteal.Listeners.JumpscareListener;
import net.meadownetwork.lifesteal.Listeners.PlayerDeathListener;
import net.meadownetwork.lifesteal.Listeners.ServerListListener;
import net.meadownetwork.lifesteal.MaintenanceMode.MaintenanceModeCommand;
import net.meadownetwork.lifesteal.MaintenanceMode.MaintenanceModeTab;
import net.meadownetwork.lifesteal.RTP.CommandRtp;
import net.meadownetwork.lifesteal.RTP.TeleportUtils;
import net.meadownetwork.lifesteal.StaffCommands.VanishCommand;
import net.meadownetwork.lifesteal.Utils.ItemManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin {


  @Override
  public void onEnable() {
    // Plugin startup logic
    getLogger().info("Lifesteal enabled!");

    TeleportUtils yeet = new TeleportUtils(this);
    ItemManager.init();

    // Register Player Death Event
    getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
    getServer().getPluginManager().registerEvents(new ServerListListener(), this);
    getServer().getPluginManager().registerEvents(new CustomHeartAdders(), this);
    getServer().getPluginManager().registerEvents(new JumpscareListener(), this);
    getServer().getPluginManager().registerEvents(new CrateGiverListener(), this);
    getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
    getServer().getPluginManager().registerEvents(new SetSpawnListener(), this);


    //register command to reset the players health to 20
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




    getConfig().options().copyDefaults();
    saveDefaultConfig();
  }



  @Override
  public void onDisable() {
    // Plugin shutdown logic
    getLogger().info("Lifesteal disabled!");
  }
}

