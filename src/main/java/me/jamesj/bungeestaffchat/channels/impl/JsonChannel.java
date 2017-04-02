package me.jamesj.bungeestaffchat.channels.impl;

import lombok.AllArgsConstructor;
import me.jamesj.bungeestaffchat.channels.IChannel;
import net.md_5.bungee.api.*;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.*;

/**
 * @author JamesJ
 * @version 1.0
 * @since 27/03/2017
 */

@AllArgsConstructor
public class JsonChannel implements IChannel {
    
    private final String id;
    private final List<String> aliases;

    private final String format;
    private final String permission;
    private final String usage;
    
    @Override
    public String getId() {
        return id;
    }
    
    @Override
    public String format(String message, String sender, String proxyId, String serverId) {
        return ChatColor.translateAlternateColorCodes('&', getFormat()
                        .replace("{message}", message)
                        .replace("{name}", sender)
                        .replace("{server}", serverId)
                        .replace("{proxy}", proxyId)
        );
    }

    @Override
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public String getFormat() {
        return format;
    }

    @Override
    public Set<ProxiedPlayer> getReceivers() {
        Collection<ProxiedPlayer> proxiedPlayers = ProxyServer.getInstance().getPlayers();
        Set<ProxiedPlayer> players = new HashSet<>();
        proxiedPlayers.forEach(proxiedPlayer -> {if(proxiedPlayer.hasPermission(getPermission())) players.add(proxiedPlayer);});
        return players;
    }

    @Override
    public String getPermission() {
        return permission;
    }

    @Override
    public String getUsage() {
        return usage;
    }
}
