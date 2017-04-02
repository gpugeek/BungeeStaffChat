package me.jamesj.bungeestaffchat.channels.impl;

import com.google.common.base.Joiner;
import me.jamesj.bungeestaffchat.channels.IChannel;
import net.md_5.bungee.api.*;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * @author JamesJ
 * @version 1.0
 * @since 27/03/2017
 */

public class ChannelCommand extends Command {

    private final IChannel channel;
    public ChannelCommand(IChannel channel){
        super(channel.getId(), channel.getPermission(), channel.getAliases().toArray(new String[channel.getAliases().size()]));
        this.channel = channel;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            if (args.length == 0) {
                sender.sendMessage(new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', channel.getUsage())).create());
                return;
            } else {
                String message = Joiner.on(" ").join(args);
                channel.sendMessage(((ProxiedPlayer)sender), message);
            }
        } else {
            sender.sendMessage(new ComponentBuilder("Console cannot use this command").color(ChatColor.RED).create());
        }
    }
}
