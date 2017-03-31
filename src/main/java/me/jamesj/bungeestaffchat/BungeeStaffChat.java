package me.jamesj.bungeestaffchat;

import com.google.gson.Gson;
import com.imaginarycode.minecraft.redisbungee.RedisBungee;
import lombok.Getter;
import me.jamesj.bungeestaffchat.channels.ChannelHandler;
import me.jamesj.bungeestaffchat.redis.RedisListener;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeStaffChat extends Plugin {

    @Getter
    private static BungeeStaffChat instance;

    @Getter
    private final Gson gson = new Gson();
    @Getter
    private String proxyId;
    @Getter
    private ChannelHandler channelHandler;

    @Override
    public void onEnable() {
        instance = this;

        RedisBungee.getApi().registerPubSubChannels("bungeestaffchat-redis");
        this.proxyId = RedisBungee.getApi().getServerId();

        getProxy().getPluginManager().registerListener(this, new RedisListener(this));

        this.channelHandler = new ChannelHandler(this);
    }

    @Override
    public void onDisable() {
        instance = null;
        RedisBungee.getApi().unregisterPubSubChannels("bungeestaffchat-redis");
        // Plugin shutdown logic
    }
}
