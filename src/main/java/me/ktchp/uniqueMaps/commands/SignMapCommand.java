package me.ktchp.uniqueMaps.commands;

import me.ktchp.uniqueMaps.util.MapUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;

public class SignMapCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType().isAir() || !(item.getItemMeta() instanceof MapMeta meta)) {
            player.sendMessage(ChatColor.RED + "You must hold a map in your main hand to sign it.");
            return true;
        }

        if (MapUtil.isSignedMap(item)) {
            player.sendMessage(ChatColor.YELLOW + "This map is already signed.");
            return true;
        }

        MapUtil.signMap(meta, player.getUniqueId(), player);
        item.setItemMeta(meta);
        return true;
    }
}