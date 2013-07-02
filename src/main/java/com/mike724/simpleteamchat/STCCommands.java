package com.mike724.simpleteamchat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class STCCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("teamchat")) {
                if (args.length == 1) {
                    boolean wantsOn = args[0].equalsIgnoreCase("on");
                    boolean wantsOff = args[0].equalsIgnoreCase("off");
                    if (!(wantsOn || wantsOff)) {
                        //Maybe they want help?
                        if (args[0].equalsIgnoreCase("help")) {
                            sender.sendMessage(ChatColor.BLUE + "" + ChatColor.UNDERLINE + "SimpleTeamChat commands:");
                            sender.sendMessage(ChatColor.ITALIC + "/teamchat on  |  Enables team chat");
                            sender.sendMessage(ChatColor.ITALIC + "/teamchat off  |  Disables team chat");
                            return true;
                        }
                        sender.sendMessage(ChatColor.RED + "Invalid state, either \"on\" or \"off\".");
                        return true;
                    }
                    STCSettings settings = SimpleTeamChat.getInstance().getSettings();
                    String name = p.getName();
                    boolean alreadyOn = settings.getEnabledPlayers().contains(name);
                    if (wantsOn) {
                        if (alreadyOn) {
                            sender.sendMessage(ChatColor.YELLOW + "Team chat is already on.");
                            return true;
                        }
                        settings.getEnabledPlayers().add(name);
                        sender.sendMessage(ChatColor.GREEN + "Team chat is now on.");
                    } else {
                        if (!alreadyOn) {
                            sender.sendMessage(ChatColor.YELLOW + "Team chat is already off.");
                            return true;
                        }
                        settings.getEnabledPlayers().remove(p.getName());
                        sender.sendMessage(ChatColor.GREEN + "Team chat is now off.");
                    }
                    return true;
                } else {
                    sender.sendMessage(ChatColor.RED + "Invalid arguments, see /" + s + " help.");
                    return true;
                }
            }
        }
        return false;
    }
}
