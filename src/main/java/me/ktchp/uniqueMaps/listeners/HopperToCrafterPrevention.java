package me.ktchp.uniqueMaps.listeners;

import me.ktchp.uniqueMaps.util.MapUtil;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.ItemStack;

public class HopperToCrafterPrevention implements Listener {

    @EventHandler
    public void onHopperMove(InventoryMoveItemEvent event) {
        if (event.getDestination().getType() != org.bukkit.event.inventory.InventoryType.CRAFTER) return;

        ItemStack item = event.getItem();
        if (item == null || item.getType() != Material.FILLED_MAP) return;

        if (MapUtil.isSignedMap(item)) {
            event.setCancelled(true);
        }
    }
}