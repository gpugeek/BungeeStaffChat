package me.jamesj.bungeestaffchat.channels.impl;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * @author JamesJ
 * @version 1.0
 * @since 27/03/2017
 */

public class ChannelCommand extends Command {
    
    public ChannelCommand(String command, String permission, String... aliases){
        super(command, permission, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer){

        } else {

        }
    }
}
