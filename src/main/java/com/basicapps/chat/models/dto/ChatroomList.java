package com.basicapps.chat.models.dto;

import com.basicapps.chat.models.Chatroom;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class ChatroomList {
    @JsonProperty("chats")
    private Set<Chatroom> chatroomSet;

    public ChatroomList() {
    }

    public ChatroomList(Set<Chatroom> chatroomSet) {
        this.chatroomSet = chatroomSet;
    }

    public Set<Chatroom> getChatroomSet() {
        return chatroomSet;
    }

    public void setChatroomSet(Set<Chatroom> chatroomSet) {
        this.chatroomSet = chatroomSet;
    }
}
