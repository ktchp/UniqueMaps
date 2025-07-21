package me.ktchp.uniqueMaps.commands;

import me.ktchp.uniqueMaps.util.MapUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class UniqueMapsCommand implements CommandExecutor {

    private final JavaPlugin plugin;

    public UniqueMapsCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(ChatColor.GOLD + "------[ UniqueMaps Help ]------");
            sender.sendMessage(ChatColor.YELLOW + "Player Commands:");
            sender.sendMessage(ChatColor.GRAY + " - /signmap" + ChatColor.WHITE + " : Sign the map in your main hand.");
            sender.sendMessage(ChatColor.GRAY + " - /unsignmap" + ChatColor.WHITE + " : Unsign your signed map.");
            sender.sendMessage("");
            sender.sendMessage(ChatColor.YELLOW + "Admin Commands:");
            sender.sendMessage(ChatColor.GRAY + " - /umaps reload" + ChatColor.WHITE + " : Reload the plugin.");
            sender.sendMessage(ChatColor.GRAY + " - /umaps bypass" + ChatColor.WHITE + " : Toggle bypass mode (OP/staff only).");
            sender.sendMessage("");
            sender.sendMessage(ChatColor.DARK_GRAY + "Aliases: /umaps, /uniquemaps");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "reload" -> {
                if (!sender.isOp() && !sender.hasPermission("uniquemaps.reload")) {
                    sender.sendMessage(ChatColor.RED + "You don't have permission to reload this plugin.");
                    return true;
                }

                plugin.reloadConfig();
                sender.sendMessage(ChatColor.GREEN + "UniqueMaps config reloaded.");
                return true;
            }

            case "bypass" -> {
                if (!(sender instanceof Player player)) {
                    sender.sendMessage(ChatColor.RED + "Only players can use this command.");
                    return true;
                }

                if (!player.isOp() && !player.hasPermission("uniquemaps.bypass")) {
                    player.sendMessage(ChatColor.RED + "You don't have permission to toggle bypass mode.");
                    return true;
                }

                boolean enabled = MapUtil.toggleBypass(player.getUniqueId());
                player.sendMessage(ChatColor.AQUA + "[UniqueMaps] " +
                        "Bypass mode has been " + (enabled ? ChatColor.GREEN + "enabled" : ChatColor.RED + "disabled") + ChatColor.AQUA + ".");
                return true;
            }

            default -> {
                sender.sendMessage(ChatColor.RED + "Unknown subcommand. Use /umaps help for a list of commands.");
                return true;
            }
        }
    }
}
