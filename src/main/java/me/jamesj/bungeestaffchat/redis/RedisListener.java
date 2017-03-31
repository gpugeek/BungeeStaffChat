package me.jamesj.bungeestaffchat.redis;

import com.google.gson.Gson;
import com.imaginarycode.minecraft.redisbungee.events.PubSubMessageEvent;
import me.jamesj.bungeestaffchat.BungeeStaffChat;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Created by James on 31/03/2017.
 * (c) JamesJ, or respective owners, 2017
 */
public class RedisListener implements Listener {

    private final BungeeStaffChat bungeeStaffChat;
    private final Gson gson;
    public RedisListener(BungeeStaffChat staffChat){
        this.bungeeStaffChat = staffChat;
        this.gson = bungeeStaffChat.getGson();
    }

    @EventHandler
    public void onRedisMessage(PubSubMessageEvent event){
        if(event.getChannel().equalsIgnoreCase("bungeestaffchat-redis")){
            String msg = event.getMessage();
            RedisMessage message = gson.fromJson(msg, RedisMessage.class);

        }
    }

}
