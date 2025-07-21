package me.ktchp.uniqueMaps.listeners;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.CartographyInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MapLockPreventer implements Listener {

    @EventHandler
    public void onCartographyClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();

        if (!(inventory instanceof CartographyInventory cartographyInventory)) return;
        if (event.getSlotType() != InventoryType.SlotType.RESULT) return; // Only block clicking the result slot

        ItemStack secondItem = cartographyInventory.getItem(1);

        if (secondItem != null && secondItem.getType() == Material.GLASS_PANE) {
            event.setCancelled(true);
            event.getWhoClicked().sendMessage(Component.text("§cLocking maps is disabled."));
        }
    }
}
