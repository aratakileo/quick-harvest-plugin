package pextystudios.quickharvest;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class QuickHarvest extends JavaPlugin {
    private static QuickHarvest instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static QuickHarvest getInstance() {
        return instance;
    }
}
