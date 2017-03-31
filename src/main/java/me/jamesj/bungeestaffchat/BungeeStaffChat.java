package me.jamesj.bungeestaffchat;

import com.google.gson.Gson;
import com.imaginarycode.minecraft.redisbungee.RedisBungee;
import lombok.Getter;
import me.jamesj.bungeestaffchat.redis.RedisListener;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeStaffChat extends Plugin {

    @Getter
    private static BungeeStaffChat instance;

    @Getter
    private final Gson gson = new Gson();

    @Override
    public void onEnable() {
        instance = this;

        RedisBungee.getApi().registerPubSubChannels("bungeestaffchat-redis");

        getProxy().getPluginManager().registerListener(this, new RedisListener(this));
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        instance = null;
        // Plugin shutdown logic
    }
}
