package com.mike724.simpleteamchat;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.MetricsLite;

import java.util.HashSet;
import java.util.List;

public class SimpleTeamChat extends JavaPlugin {

    private static SimpleTeamChat instance;
    private STCSettings settings;

    /**
     * Enables the plugin (sets everything up).
     */
    @Override
    public void onEnable() {
        instance = this;
        FileConfiguration config = this.getConfig();
        config.options().copyDefaults(true);
        this.saveConfig();

        //Config
        List<String> teams = config.getStringList("enabledTeams");
        boolean alter = config.getBoolean("alter.enable");
        String prefix = config.getString("alter.prefix");
        String suffix = config.getString("alter.suffix");
        settings = new STCSettings(new HashSet<String>(teams), alter, prefix, suffix);

        this.getServer().getPluginManager().registerEvents(new STCListener(), this);
        this.getCommand("teamchat").setExecutor(new STCCommands());

        try {
            MetricsLite metrics = new MetricsLite(this);
            metrics.start();
        } catch (Exception ex) {
            //Continue silently
        }
    }

    /**
     * Disables the plugin safely
     */
    @Override
    public void onDisable() {
        super.onDisable();
    }

    /**
     * Gets the plugin's settings object
     *
     * @return the STCSettings instance
     */
    public STCSettings getSettings() {
        return settings;
    }

    /**
     * Gets the plugin itself
     *
     * @return the plugin object
     */
    public static SimpleTeamChat getInstance() {
        return instance;
    }

}
