package com.mike724.simpleteamchat;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused")
public class STCSettings {

    private Set<String> enabledTeams;
    private Set<String> enabledPlayers;
    private boolean shouldAlter;
    private String prefix;
    private String suffix;

    /**
     * Class constructor
     *
     * @param enabledTeams teams the plugin is enabled for (config.yml)
     * @param shouldAlter  whether or not the plugin should format chat messages
     * @param prefix       chat prefix string
     * @param suffix       chat suffix string
     */
    public STCSettings(Set<String> enabledTeams, boolean shouldAlter, String prefix, String suffix) {
        this.enabledTeams = enabledTeams;
        this.enabledPlayers = new HashSet<>();
        this.shouldAlter = shouldAlter;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    /**
     * Gets the enabled teams
     *
     * @return a Set of String objects representing team names
     */
    public Set<String> getEnabledTeams() {
        return enabledTeams;
    }

    /**
     * Gets the enabled players
     *
     * @return a Set of String objects representing player names
     */
    public Set<String> getEnabledPlayers() {
        return enabledPlayers;
    }

    /**
     * Gets whether or not chat messages should be formatted
     *
     * @return boolean (true = alter)
     */
    public boolean isAlterEnabled() {
        return shouldAlter;
    }

    /**
     * Gets the chat prefix string
     *
     * @return the prefix string
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Gets the chat suffix string
     *
     * @return the suffix string
     */
    public String getSuffix() {
        return suffix;
    }
}
