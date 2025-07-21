package me.ktchp.uniqueMaps.listeners;

import me.ktchp.uniqueMaps.util.MapUtil;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CrafterCopyPrevention implements Listener {

    @EventHandler
    public void onCrafterClick(InventoryClickEvent event) {
        if (event.getInventory().getType() != InventoryType.CRAFTER) return;

        Inventory clickedInventory = event.getClickedInventory();
        if (clickedInventory == null) return;

        int rawSlot = event.getRawSlot();
        boolean isCrafterSlot = rawSlot < event.getInventory().getSize();

        ItemStack cursor = event.getCursor();
        ItemStack current = event.getCurrentItem();

        if (isCrafterSlot && cursor != null && cursor.getType() == Material.FILLED_MAP && MapUtil.isSignedMap(cursor)) {
            event.setCancelled(true);
            event.getWhoClicked().sendMessage("§cYou can't insert signed maparts into a Crafter.");
            return;
        }

        if (event.getAction().toString().contains("MOVE") && current != null &&
                current.getType() == Material.FILLED_MAP && MapUtil.isSignedMap(current)) {
            event.setCancelled(true);
            event.getWhoClicked().sendMessage("§cYou can't insert signed maparts into a Crafter.");
        }
    }

    @EventHandler
    public void onCrafterDrag(InventoryDragEvent event) {
        if (event.getInventory().getType() != InventoryType.CRAFTER) return;

        ItemStack dragged = event.getOldCursor();
        if (dragged == null || dragged.getType() != Material.FILLED_MAP) return;
        if (!MapUtil.isSignedMap(dragged)) return;

        for (int slot : event.getRawSlots()) {
            if (slot < event.getInventory().getSize()) {
                event.setCancelled(true);
                event.getWhoClicked().sendMessage("§cYou can't insert signed maparts into a Crafter.");
                return;
            }
        }
    }
}
