package me.ktchp.uniqueMaps.util;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.persistence.PersistentDataType;
import java.util.*;

public class MapUtil {

    public static boolean isSignedMap(org.bukkit.inventory.ItemStack map) {
        if (!(map.getItemMeta() instanceof MapMeta meta)) return false;
        return meta.getPersistentDataContainer().has(getKey(), PersistentDataType.STRING);
    }

    public static boolean canCopy(Player player, MapMeta meta) {
        String signer = meta.getPersistentDataContainer().get(getKey(), PersistentDataType.STRING);
        return signer != null && signer.equalsIgnoreCase(player.getName());
    }


    public static void signMap(MapMeta meta, UUID signerUUID, Player player) {
        meta.getPersistentDataContainer().set(getKey(), PersistentDataType.STRING, signerUUID.toString());

        List<String> lore = meta.hasLore() ? new ArrayList<>(meta.getLore()) : new ArrayList<>();
        String signedLine = ChatColor.AQUA + "Signed by: " + player.getName();

        if (lore.stream().noneMatch(line -> line.contains("Signed by:"))) {
            lore.add(signedLine);
        }

        meta.setLore(lore);

        player.sendMessage(ChatColor.GREEN + "The map has been signed.");

        player.getWorld().spawnParticle(
                Particle.ENCHANT,
                player.getLocation().add(0, 1.2, 0),
                75,
                0.3, 0.5, 0.3,
                0.05
        );

        player.playSound(
                player.getLocation(),
                Sound.UI_CARTOGRAPHY_TABLE_TAKE_RESULT,
                SoundCategory.PLAYERS,
                1.0f,
                1.0f
        );
    }


    public static void unsignMap(MapMeta meta, Player player) {
        meta.getPersistentDataContainer().remove(getKey());

        if (meta.hasLore()) {
            List<String> lore = new ArrayList<>(meta.getLore());
            lore.removeIf(line -> line.contains("Signed by:"));
            if (lore.isEmpty()) {
                meta.setLore(null);
            } else {
                meta.setLore(lore);
            }
        }

        player.sendMessage(ChatColor.GREEN + "The map has been unsigned.");

        player.getWorld().spawnParticle(
                Particle.FLAME,
                player.getLocation().add(0, 1.2, 0),
                50,
                0.4, 0.5, 0.4,
                0.01
        );

        player.playSound(
                player.getLocation(),
                Sound.BLOCK_CHAIN_BREAK,
                SoundCategory.PLAYERS,
                1.0f,
                1.0f
        );
    }


    public static UUID getSigner(MapMeta meta) {
        String uuidStr = meta.getPersistentDataContainer().get(getKey(), PersistentDataType.STRING);
        if (uuidStr == null) return null;

        try {
            return UUID.fromString(uuidStr);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static NamespacedKey getKey() {
        return new NamespacedKey("uniquemaps", "signedby");
    }

    private static final Set<UUID> bypassPlayers = new HashSet<>();

    public static boolean hasBypass(UUID uuid) {
        return bypassPlayers.contains(uuid);
    }

    public static boolean toggleBypass(UUID uuid) {
        if (bypassPlayers.contains(uuid)) {
            bypassPlayers.remove(uuid);
            return false;
        } else {
            bypassPlayers.add(uuid);
            return true;
        }
    }
}
