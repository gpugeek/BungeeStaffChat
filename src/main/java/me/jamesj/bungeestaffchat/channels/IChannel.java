package me.jamesj.bungeestaffchat.channels;

import java.util.Set;

/**
 * @author JamesJ
 * @version 1.0
 * @since 27/03/2017
 */

public interface IChannel {
    
    String getId();
    Boolean isJoinable();
    String format(String message, String sender);
    Set<String> getReceivers();
    
}
