package me.jamesj.bungeestaffchat;

import com.google.common.io.ByteStreams;
import com.google.gson.Gson;
import com.imaginarycode.minecraft.redisbungee.RedisBungee;
import lombok.Getter;
import me.jamesj.bungeestaffchat.channels.ChannelHandler;
import me.jamesj.bungeestaffchat.redis.RedisListener;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.*;

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
        File lockFile = new File(getDataFolder(), "_v.lock");
        if (!lockFile.exists()) {
            getDataFolder().mkdir();
            try {
                new File(getDataFolder(), "default.json").createNewFile();
                InputStream is = getResourceAsStream("default.json");
                OutputStream os = new FileOutputStream(new File(getDataFolder(), "default.json"));
                ByteStreams.copy(is, os);
                lockFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        getProxy().getScheduler().runAsync(this, () -> {
            RedisBungee.getApi().registerPubSubChannels("bungeestaffchat-redis");
        });

        this.proxyId = RedisBungee.getApi().getServerId();

        getProxy().getPluginManager().registerListener(this, new RedisListener(this));

        this.channelHandler = new ChannelHandler(this);
    }

    @Override
    public void onDisable() {
        instance = null;
        getProxy().getScheduler().runAsync(this, () -> {
            RedisBungee.getApi().unregisterPubSubChannels("bungeestaffchat-redis");
        });
    }
}
