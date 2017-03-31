package me.jamesj.bungeestaffchat.channels;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.*;

/**
 * @author JamesJ
 * @version 1.0
 * @since 27/03/2017
 */

public interface IChannel {
    
    String getId();
    String getFormat();
    String getPermission();
    String format(String message, String sender, String proxyId, String serverId);
    Set<ProxiedPlayer> getReceivers();
    List<String> getAliases();

}
