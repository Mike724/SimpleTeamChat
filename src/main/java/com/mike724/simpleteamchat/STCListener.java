package com.mike724.simpleteamchat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class STCListener implements Listener {

    /**
     * Handles the event fired when a player sends a chat message
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event) {
        //Return if event was cancelled
        if (event.isCancelled()) {
            return;
        }

        //Get the things we will need
        Player p = event.getPlayer();
        Scoreboard main = Bukkit.getScoreboardManager().getMainScoreboard();
        Team t = main.getPlayerTeam(p);
        if (t == null) {
            return;
        }
        SimpleTeamChat stc = SimpleTeamChat.getInstance();
        STCSettings settings = stc.getSettings();

        //Enabled for both player and team?
        if (settings.getEnabledPlayers().contains(p.getName())) {
            if (settings.getEnabledTeams().contains(t.getName())) {
                //Clear recipients
                event.getRecipients().clear();

                //Add recipients (team players) that are ONLINE (not offline)
                ArrayList<Player> teamPlayers = new ArrayList<>();
                for (Player somePlayer : Bukkit.getOnlinePlayers()) {
                    if (main.getPlayerTeam(somePlayer) == t) {
                        teamPlayers.add(somePlayer);
                    }
                }
                event.getRecipients().addAll(teamPlayers);

                //Add prefix and suffix
                if (settings.isAlterEnabled()) {
                    String format = event.getFormat();
                    String prefix = settings.getPrefix().replace("$TEAM", t.getDisplayName());
                    String suffix = settings.getSuffix().replace("$TEAM", t.getDisplayName());
                    format = prefix + format + suffix;
                    event.setFormat(format);
                }
            }
        }
    }
}
