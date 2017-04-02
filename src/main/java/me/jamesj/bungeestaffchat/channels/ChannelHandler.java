package me.jamesj.bungeestaffchat.channels;

import me.jamesj.bungeestaffchat.BungeeStaffChat;
import me.jamesj.bungeestaffchat.channels.impl.*;
import me.jamesj.bungeestaffchat.utils.DiscUtils;

import java.io.*;
import java.util.*;

/**
 * Created by James on 31/03/2017.
 * (c) JamesJ, or respective owners, 2017
 */
public class ChannelHandler {

    private final Map<String, IChannel> channelMap;
    private List<IChannel> channels;
    private final File folder;

    public ChannelHandler(BungeeStaffChat bungeeStaffChat) {
        this.channelMap = new HashMap<>();
        this.channels = new ArrayList<>();
        this.folder = bungeeStaffChat.getDataFolder();
        if (!folder.exists()) {
            folder.mkdir();
        }
        for (File file : folder.listFiles()) {
            if(!file.getName().endsWith(".json")) continue;
            String json = getFileData(file);
            JsonChannel jsonChannel = bungeeStaffChat.getGson().fromJson(json, JsonChannel.class);
            channelMap.put(jsonChannel.getId().toLowerCase(), jsonChannel);
            jsonChannel.getAliases().forEach(s -> channelMap.put(s, jsonChannel));
            channels.add(jsonChannel);

            bungeeStaffChat.getProxy().getPluginManager()
                    .registerCommand(bungeeStaffChat, new ChannelCommand(jsonChannel));
        }

    }

    public Map<String, IChannel> getChannelMap() {
        return channelMap;
    }

    public List<IChannel> getChannels() {
        return channels;
    }

    private String getFileData(File file) {
        String json = "";
        try {
            json = DiscUtils.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public IChannel getChannel(String id) {
        return channelMap.get(id.toLowerCase());
    }

}
