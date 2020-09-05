package com.basicapps.chat.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
public class Message {
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @JsonProperty("chatroom_id")
    @ManyToOne
//    @Column(name = "chatroom_id")
    private Chatroom chatroom;

    @JsonProperty("sender_id")
    @ManyToOne
//    @Column(name = "sender_id")
    private User sender;

    @JsonProperty("text")
    @Column(name = "text")
    private String messageText;

    @JsonProperty("sent_at")
    @Column(name = "sent_at")
    private Date sentAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Chatroom getChatroom() {
        return chatroom;
    }

    public void setChatroom(Chatroom chatroomId) {
        this.chatroom = chatroomId;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User senderId) {
        this.sender = senderId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Date getSentAt() {
        return sentAt;
    }

    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
    }
}
