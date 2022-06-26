package net.clutchcraft.lifesteal;

import net.clutchcraft.lifesteal.Commands.FlyCommand;
import net.clutchcraft.lifesteal.Commands.GamemodeCommand;
import net.clutchcraft.lifesteal.Commands.ResetHealthCommand;
import net.clutchcraft.lifesteal.Crates.CrateGiveCommand;
import net.clutchcraft.lifesteal.Crates.CrateGiverListener;
import net.clutchcraft.lifesteal.CustomHeartItems.CustomHeartAdders;
import net.clutchcraft.lifesteal.CustomHeartItems.HeartItemGiver;
import net.clutchcraft.lifesteal.DiscordReportSystem.CommandReport;
import net.clutchcraft.lifesteal.Listeners.JumpscareListener;
import net.clutchcraft.lifesteal.Listeners.PlayerDeathListener;
import net.clutchcraft.lifesteal.Listeners.ServerListListener;
import net.clutchcraft.lifesteal.MaintenanceMode.MaintenanceModeCommand;
import net.clutchcraft.lifesteal.MaintenanceMode.MaintenanceModeTab;
import net.clutchcraft.lifesteal.RTP.CommandRtp;
import net.clutchcraft.lifesteal.RTP.TeleportUtils;
import net.clutchcraft.lifesteal.Utils.ItemManager;
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




    getConfig().options().copyDefaults();
    saveDefaultConfig();
  }



  @Override
  public void onDisable() {
    // Plugin shutdown logic
    getLogger().info("Lifesteal disabled!");
  }
}

