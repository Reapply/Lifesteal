package net.clutchcraft.lifesteal;

import net.clutchcraft.lifesteal.Commands.FlyCommand;
import net.clutchcraft.lifesteal.Commands.GamemodeCommand;
import net.clutchcraft.lifesteal.Commands.ResetHealthCommand;
import net.clutchcraft.lifesteal.Listeners.PlayerDeathListener;
import net.clutchcraft.lifesteal.Listeners.ScoreboardListener;
import net.clutchcraft.lifesteal.Listeners.ServerListListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin {

  @Override
  public void onEnable() {
    // Plugin startup logic
    getLogger().info("Lifesteal enabled!");
    // Register Player Death Event
    getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
    getServer().getPluginManager().registerEvents(new ServerListListener(), this);
    getServer().getPluginManager().registerEvents(new ScoreboardListener(), this);
    //register command to reset the players health to 20
    getCommand("resethealth").setExecutor(new ResetHealthCommand());
    getCommand("gm").setExecutor(new GamemodeCommand());
    getCommand("fly").setExecutor(new FlyCommand());
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
    getLogger().info("Lifesteal disabled!");
  }
}
