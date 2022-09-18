package net.meadownetwork.lifesteal.RTP;

import net.meadownetwork.lifesteal.main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Random;

public class TeleportUtils {

    //Get an instance of the main class so we can access config
    static main plugin;

    public TeleportUtils(main plugin) {
        this.plugin = plugin;
    }

    //List of all the unsafe blocks
    public static HashSet<Material> bad_blocks = new HashSet<>();

    static {
        bad_blocks.add(Material.LAVA);
        bad_blocks.add(Material.FIRE);
        bad_blocks.add(Material.CACTUS);
    }

    public static Location generateLocation(Player player) {
        //Generate Random Location
        Random random = new Random();

        int x = 0;
        int z = 0;
        int y = 0;

        if (plugin.getConfig().getBoolean("world-border")) { //If they want to limit the distance
            x = random.nextInt(plugin.getConfig().getInt("border"));
            z = random.nextInt(plugin.getConfig().getInt("border"));
            y = 150;
        } else if (!plugin.getConfig().getBoolean("world-border")) { //If they dont
            x = random.nextInt(25000); //25000 is default
            z = random.nextInt(25000);
            y = 150;
        }
        // make this world use the lifesleal world "//Location randomLocation = new Location(player.getWorld(""), x, y, z);"
        Location randomLocation = new Location(player.getServer().getWorld("LifeSteal"), x, y, z);
        y = randomLocation.getWorld().getHighestBlockYAt(randomLocation);
        randomLocation.setY(y);

        return randomLocation;
    }

    public static Location findSafeLocation(Player player) {

        Location randomLocation = generateLocation(player);

        while (!isLocationSafe(randomLocation)) {
            //Keep looking for a safe location
            randomLocation = generateLocation(player);
        }
        return randomLocation;
    }

    public static boolean isLocationSafe(Location location) {

        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        //Get instances of the blocks around where the player would spawn (MAKE THESE ALL THE LIFESTEAL WORLD ONLY)
        //Block block1 = location.getWorld(lifesteal).getBlockAt(x, y, z);
        //        Block block2 = location.getWorld(lifesteal).getBlockAt(x, y + 1, z);
        //        Block block3 = location.getWorld().getBlockAt(x, y + 2, z)
        Block block = Bukkit.getWorld("LifeSteal").getBlockAt(x, y, z);
        Block below = Bukkit.getWorld("LifeSteal").getBlockAt(x, y - 1, z);
        Block above = Bukkit.getWorld("LifeSteal").getBlockAt(x, y + 1, z);

        //Check to see if the surroundings are safe or not
        return !(bad_blocks.contains(below.getType())) || (block.getType().isSolid()) || (above.getType().isSolid());
    }

    // Only teleport the player to the world "LifeSteal" if they are in the world "world"
    public static void teleportPlayer(Player player) {
        if (player.getWorld().getName().equalsIgnoreCase("LifeSteal")) {
            player.teleport(findSafeLocation(player));
        }
    }

}
