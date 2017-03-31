package me.jamesj.bungeestaffchat.channels.impl;

import lombok.AllArgsConstructor;
import me.jamesj.bungeestaffchat.channels.IChannel;
import net.md_5.bungee.api.ChatColor;

import java.util.Set;

/**
 * @author JamesJ
 * @version 1.0
 * @since 27/03/2017
 */

@AllArgsConstructor
public class JsonChannel implements IChannel {
    
    private final String id;
    private final Boolean joinable;
    private final String format;
    
    @Override
    public String getId() {
        return id;
    }
    
    @Override
    public String format(String message, String sender) {
        String proxyId = "TODO";
        return ChatColor.translateAlternateColorCodes('&', format
                        .replace("{message}", message)
                        .replace("{name}", sender)
                        .replace("{proxy}", proxyId)
        );
    }
    
    @Override
    public Boolean isJoinable() {
        return joinable;
    }
    
    @Override
    public Set<String> getReceivers() {
        return null;
    }
}
