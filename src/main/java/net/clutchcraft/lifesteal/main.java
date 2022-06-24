package net.clutchcraft.lifesteal;

import net.clutchcraft.lifesteal.Commands.FlyCommand;
import net.clutchcraft.lifesteal.Commands.GamemodeCommand;
import net.clutchcraft.lifesteal.Commands.ResetHealthCommand;
import net.clutchcraft.lifesteal.Listeners.PlayerDeathListener;
import net.clutchcraft.lifesteal.Listeners.ServerListListener;
import net.clutchcraft.lifesteal.RankSystem.NameTagManager;
import net.clutchcraft.lifesteal.RankSystem.RankCommand;
import net.clutchcraft.lifesteal.RankSystem.RankListener;
import net.clutchcraft.lifesteal.RankSystem.RankManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin {


  private RankManager rankManager;
  private NameTagManager nameTagManager;


  @Override
  public void onEnable() {
    // Plugin startup logic
    getLogger().info("Lifesteal enabled!");
    // Register Player Death Event
    getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
    getServer().getPluginManager().registerEvents(new ServerListListener(), this);

    //register command to reset the players health to 20
    getCommand("resethealth").setExecutor(new ResetHealthCommand());
    getCommand("gm").setExecutor(new GamemodeCommand());
    getCommand("fly").setExecutor(new FlyCommand());


    // Rank Manager
    getCommand("rank").setExecutor(new RankCommand(this));
    getServer().getPluginManager().registerEvents(new RankListener(this), this);
    rankManager = new RankManager(this);
    // Name Tag Manager
    nameTagManager = new NameTagManager(this);
  }
  public RankManager getRankManager() {
    return rankManager;
  }
    public NameTagManager getNameTagManager() {
        return nameTagManager;
    }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
    getLogger().info("Lifesteal disabled!");
  }
}
