package me.jamesj.bungeestaffchat.redis;

import lombok.*;

/**
 * Created by James on 31/03/2017.
 * (c) JamesJ, or respective owners, 2017
 */
@AllArgsConstructor
@Getter
public class RedisMessage {
    private final String sender;
    private final String channel;
    private final String message;
    private final String proxyId;
    private final String serverId;
}
