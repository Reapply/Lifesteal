This was orginaly for my server but i got bored so stop THIS WILL NOT BE UPDATED

# Lifesteal Plugin 🩸

The Lifesteal Plugin is a powerful and feature-rich Bukkit plugin developed for the Meadow Network Minecraft server. It brings an immersive and thrilling gameplay experience with its innovative mechanics and extensive functionality.

## 🌟 Key Features

✨ **Lifesteal Mechanics:** Engage in intense player-vs-player combat where players gain health by dealing damage to opponents and lose health when they are defeated. This unique mechanic adds an exciting dynamic to battles, encouraging strategic gameplay and rewarding skilled fighters.

🔒 **Maintenance Mode:** Ensure the smooth operation of your server with the built-in maintenance mode feature. Activate maintenance mode to temporarily restrict player access, allowing server administrators to perform important maintenance tasks, updates, or fixes without interruptions.

🌍 **Random Teleportation:** Discover new horizons with the random teleportation feature. Players can instantly teleport to a random location within the world, leading to exciting adventures and unexpected encounters. Explore uncharted territories and find hidden treasures in the vast Minecraft landscape.

👑 **Staff Commands:** Empower your staff members with a range of powerful commands to efficiently manage the server. Staff members with the appropriate permissions can access commands such as freeze/unfreeze players, change gamemodes, toggle invisibility, grant special tools, and more.

❤️ **Custom Heart Items:** Unleash the power of unique custom heart items that provide special abilities related to health management. These heart items add an extra layer of strategy and customization to gameplay, allowing players to tailor their experience to their playstyle.

🎁 **Crates:** Reward your players with exciting loot crates. Players can obtain common and rare crates containing valuable items, rare artifacts, and exclusive rewards. Enhance the sense of achievement and anticipation, creating a thrilling and rewarding progression system for your community.

📚 **Help Command:** Provide comprehensive assistance to players with the interactive help command. Accessible information and commands guide players through the plugin's features, ensuring they can fully enjoy and engage with the unique gameplay mechanics and functionalities.

## 💡 Prerequisites

To use the Lifesteal Plugin, you need the following:

- Java 8 or higher
- Bukkit or Spigot server

## 🚀 Installation

1. Download the latest version of the Lifesteal Plugin JAR file from the [Releases](https://github.com/meadow-network/Lifesteal/releases) page.
2. Place the downloaded JAR file into the `plugins` folder of your Bukkit or Spigot server.
3. Restart the server to apply the plugin.

## ⚙️ Configuration

The plugin provides extensive configuration options that can be customized in the `config.yml` file located in the `plugins/Lifesteal` directory. Explore the configuration file to adjust various settings, including lifesteal mechanics, crate rewards, teleportation behavior, staff permissions, and more.

## 📖 Commands and Permissions

The Lifesteal Plugin introduces a wide range of commands and permissions to manage and interact with its features. The following table outlines the available commands, their descriptions, and the associated permissions required:

| Command          | Description                                          | Permission              |
|------------------|------------------------------------------------------|-------------------------|
| `/lifesteal`     | Main command for the Lifesteal Plugin                 | `lifesteal.plugin`      |
| `/rtp`           | Teleport to a random location                         | `lifesteal.rtp`         |
| `/maintenancemode`| Enable or disable maintenance mode                    | `lifesteal.maintenancemode` |
| `/freeze`        | Freeze or unfreeze a player                           | `lifesteal.freeze`      |
| `/gamemode`      | Change the gamemode of a player                       | `lifesteal.gamemode`    |
| `/vanish`        | Toggle invisibility for a player                      | `lifesteal.vanish`      |
| `/compass`       | Get a Staff Compass for navigation                    | `lifesteal.staff`       |
| `/crategive`     | Give a crate to a player                              | `lifesteal.crategive`   |
| `/setspawn`      | Set the spawn location                                | `lifesteal.setspawn`    |
| `/spawn`         | Teleport to the spawn location                        | `lifesteal.spawn`       |
| `/resethealth`   | Reset a player's health to full                       | `lifesteal.resethealth` |
| `/fly`           | Enable or disable flight mode                         | `lifesteal.fly`         |
| `/report`        | Report an issue or player to the staff                | `lifesteal.report`      |
| `/customheartgiver` | Give custom heart items to players                   | `lifesteal.customheartgiver` |
| `/help`          | Get detailed help and information about the plugin    | `lifesteal.help`        |

Note: Some commands are restricted to players with specific permissions to ensure proper server management and prevent abuse.

## 🎉 Event Listeners

The Lifesteal Plugin includes an extensive collection of event listeners that handle specific actions and behaviors, enriching the gameplay experience and ensuring smooth operation. The following event listeners are implemented:

- **PlayerDeathListener:** Handles player death events and lifesteal mechanics.
- **ServerListListener:** Modifies the server list MOTD and player count during maintenance mode.
- **CustomHeartAdders:** Adds custom abilities to heart items.
- **JumpscareListener:** Triggers a jumpscare effect when a player is killed.
- **PlayerJoinListener:** Welcomes players on join and announces first-time players.
- **SetSpawnListener:** Handles setting the spawn location.
- **SpawnArmorStandListener:** Disables gravity for armor stands spawned in the world.
- **RareCrateListener:** Listens for interactions with rare crates.
- **CommonCrateListener:** Listens for interactions with common crates.

## 🤝 Contributing

Contributions are highly appreciated! If you find a bug or have a suggestion for improvement, please open an issue in the GitHub repository. You can also submit pull requests to contribute code enhancements or fixes.

## 📝 License

This project is licensed under the MIT License, granting you the freedom to use, modify, and distribute the plugin.

## 📞 Contact

For any inquiries or questions regarding the Lifesteal Plugin, feel free to contact our support team at support@meadownetwork.com. We are available to assist you and provide further information.

Thank you for choosing the Lifesteal Plugin! Enjoy the exciting gameplay and captivating features it brings to your Minecraft server.
