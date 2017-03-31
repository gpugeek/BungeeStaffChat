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

    private final BungeeStaffChat bungeeStaffChat;

    public ChannelHandler(BungeeStaffChat bungeeStaffChat) {
        this.bungeeStaffChat = bungeeStaffChat;
        this.channelMap = new HashMap<>();
        this.channels = new ArrayList<>();
        this.folder = new File(bungeeStaffChat.getDataFolder(), "channels" + File.separator);
        if (!folder.exists()) {
            folder.mkdir();
        }
        for (File file : folder.listFiles()) {
            String json = getFileData(file);
            JsonChannel jsonChannel = bungeeStaffChat.getGson().fromJson(json, JsonChannel.class);
            channelMap.put(jsonChannel.getId().toLowerCase(), jsonChannel);
            jsonChannel.getAliases().forEach(s -> channelMap.put(s, jsonChannel));
            channels.add(jsonChannel);

            bungeeStaffChat.getProxy().getPluginManager()
                    .registerCommand(bungeeStaffChat, new ChannelCommand(jsonChannel.getId(), jsonChannel.getPermission(), jsonChannel.getAliases().toArray(new String[jsonChannel.getAliases().size()])));
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
