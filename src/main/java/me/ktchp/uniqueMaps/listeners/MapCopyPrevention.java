package me.ktchp.uniqueMaps.listeners;

import me.ktchp.uniqueMaps.util.MapUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;

import java.util.UUID;

public class MapCopyPrevention implements Listener {

    @EventHandler
    public void onPrepareItemCraft(PrepareItemCraftEvent event) {
        if (!(event.getView().getPlayer() instanceof Player player)) return;

        CraftingInventory inv = event.getInventory();
        InventoryType type = event.getView().getType();

        if (type != InventoryType.CRAFTING && type != InventoryType.WORKBENCH) return;

        ItemStack result = inv.getResult();
        if (result == null || result.getType() != Material.FILLED_MAP) return;

        for (ItemStack item : inv.getMatrix()) {
            if (item == null || item.getType() != Material.FILLED_MAP) continue;
            if (!MapUtil.isSignedMap(item)) continue;

            MapMeta meta = (MapMeta) item.getItemMeta();
            UUID signer = MapUtil.getSigner(meta);
            if (signer == null || !signer.equals(player.getUniqueId())) {
                inv.setResult(null);
                return;
            }
        }
    }
}
