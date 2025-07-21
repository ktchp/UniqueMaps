package me.ktchp.uniqueMaps.listeners;

import me.ktchp.uniqueMaps.util.MapUtil;
import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.block.data.Directional;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.ItemStack;

public class DropperToCrafterPrevention implements Listener {
    @EventHandler
    public void onDropperDispense(BlockDispenseEvent event) {
        Block block = event.getBlock();
        if (block.getType() != Material.DROPPER) return;

        ItemStack item = event.getItem();
        if (item == null || item.getType() != Material.FILLED_MAP) return;

        if (!MapUtil.isSignedMap(item)) return;

        Directional directional = (Directional) block.getBlockData();
        BlockFace facing = directional.getFacing();
        Block target = block.getRelative(facing);

        if (target.getType() == Material.CRAFTER) {
            event.setCancelled(true);
        }
    }

}
