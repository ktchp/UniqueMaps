package me.ktchp.uniqueMaps.listeners;

import me.ktchp.uniqueMaps.util.MapUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareInventoryResultEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;

import java.util.UUID;

public class CartographyCopyPrevention implements Listener {

    @EventHandler
    public void onPrepareCartographyResult(PrepareInventoryResultEvent event) {
        InventoryView view = event.getView();
        if (!(view.getPlayer() instanceof Player player)) return;
        if (view.getType() != InventoryType.CARTOGRAPHY) return;

        ItemStack inputMap = view.getItem(0);
        if (inputMap == null || inputMap.getType() != Material.FILLED_MAP) return;
        if (!MapUtil.isSignedMap(inputMap)) return;

        UUID signer = MapUtil.getSigner((MapMeta) inputMap.getItemMeta());
        if (signer == null) return;

        if (!signer.equals(player.getUniqueId())) {
            view.setItem(2, null);
        }
    }

    @EventHandler
    public void onCartographyClick(InventoryClickEvent event) {
        InventoryView view = event.getView();
        if (!(view.getPlayer() instanceof Player player)) return;
        if (view.getType() != InventoryType.CARTOGRAPHY) return;
        if (event.getSlotType() != InventoryType.SlotType.RESULT) return;

        ItemStack result = event.getCurrentItem();
        if (result == null || result.getType() != Material.FILLED_MAP) return;
        if (!MapUtil.isSignedMap(result)) return;

        UUID signer = MapUtil.getSigner((MapMeta) result.getItemMeta());
        if (signer == null) return;

        if (!signer.equals(player.getUniqueId())) {
            event.setCancelled(true);
            player.sendMessage("§cYou cannot copy someone else's signed map.");
        }
    }
}