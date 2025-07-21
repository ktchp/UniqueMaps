package me.ktchp.uniqueMaps;

import me.ktchp.uniqueMaps.commands.SignMapCommand;
import me.ktchp.uniqueMaps.commands.UniqueMapsTabCompleter;
import me.ktchp.uniqueMaps.commands.UniqueMapsCommand;
import me.ktchp.uniqueMaps.commands.UnsignMapCommand;
import me.ktchp.uniqueMaps.listeners.*;
import me.ktchp.uniqueMaps.util.MapUtil;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class UniqueMaps extends JavaPlugin {

    public static final @NotNull NamespacedKey SIGNED_KEY = MapUtil.getKey();
    private static UniqueMaps instance;

    private static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[91m";
    public static final String ANSI_DARK_RED = "\u001B[2;31m";
    public static final String ANSI_LIME = "\u001B[92m";
    private static final String ANSI_RESET = "\u001B[0m";

    @Override
    public void onEnable() {
        instance = this;

        getServer().getPluginManager().registerEvents(new MapLockPreventer(), this);
        getServer().getPluginManager().registerEvents(new MapCopyPrevention(), this);
        getServer().getPluginManager().registerEvents(new CrafterCopyPrevention(), this);
        getServer().getPluginManager().registerEvents(new DropperToCrafterPrevention(), this);
        getServer().getPluginManager().registerEvents(new HopperToCrafterPrevention(), this);
        getServer().getPluginManager().registerEvents(new CartographyCopyPrevention(), this);

        String asciiArt = "\n" +
                "  _    _      \n"
                + " | |  | |                          \n"
                + " | |  | |_ __ ___   __ _ _ __  ___ \n"
                + " | |  | | '_ ` _ \\ / _` | '_ \\/ __|\n"
                + " | |__| | | | | | | (_| | |_) \\__ \\\n"
                + "  \\____/|_| |_| |_|\\__,_| .__/|___/\n"
                + "                        | |        \n"
                + "                        |_|        \n";

        getLogger().info(ANSI_YELLOW + asciiArt + ANSI_RESET);
        getLogger().info(ANSI_LIME + "UniqueMaps plugin enabled!" + ANSI_RESET);
        getLogger().info(ANSI_RED + "made by ktchp" + ANSI_RESET);

        getCommand("signmap").setExecutor(new SignMapCommand());
        getCommand("unsignmap").setExecutor(new UnsignMapCommand());
        getCommand("uniquemaps").setExecutor(new UniqueMapsCommand(this));
        getCommand("umaps").setTabCompleter(new UniqueMapsTabCompleter());
    }

    @Override
    public void onDisable() {
        String asciiArt = "\n" +
                "  _    _      \n"
                + " | |  | |                          \n"
                + " | |  | |_ __ ___   __ _ _ __  ___ \n"
                + " | |  | | '_ ` _ \\ / _` | '_ \\/ __|\n"
                + " | |__| | | | | | | (_| | |_) \\__ \\\n"
                + "  \\____/|_| |_| |_|\\__,_| .__/|___/\n"
                + "                        | |        \n"
                + "                        |_|        \n";

        getLogger().info(ANSI_YELLOW + asciiArt + ANSI_RESET);
        getLogger().info(ANSI_DARK_RED + "UniqueMaps plugin disabled!" + ANSI_RESET);
        getLogger().info(ANSI_RED + "made by ktchp" + ANSI_RESET);
    }

    public static UniqueMaps getInstance() {
        return instance;
    }
}
