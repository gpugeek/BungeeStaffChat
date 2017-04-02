package me.jamesj.bungeestaffchat.channels;

import com.imaginarycode.minecraft.redisbungee.RedisBungee;
import me.jamesj.bungeestaffchat.BungeeStaffChat;
import me.jamesj.bungeestaffchat.redis.RedisMessage;
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
    String getUsage();

    default void sendMessage(ProxiedPlayer proxiedPlayer, String message){
        BungeeStaffChat bungeeStaffChat = BungeeStaffChat.getInstance();
        if(proxiedPlayer == null || message == null || message.isEmpty()) return;
        String playerName = proxiedPlayer.getName();
        RedisMessage redisMessage = new RedisMessage(playerName, getId(), message, bungeeStaffChat.getProxyId(), proxiedPlayer.getServer().getInfo().getName());

        String json = bungeeStaffChat.getGson().toJson(redisMessage);
        RedisBungee.getApi().sendChannelMessage("bungeestaffchat-redis", json);
    }

}
