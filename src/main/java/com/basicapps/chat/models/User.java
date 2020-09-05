package com.basicapps.chat.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @JsonProperty("username")
    @Column(name = "username", unique = true)
    private String username;

    @JsonProperty("info")
    private UserInfo userInfo;

    @JsonProperty("password")
    @Column(name = "password")
    @NotNull
    private String password;

    @JsonIgnore
    @Column(name = "role")
    private final String role = "USER";

    @OneToMany(mappedBy = "sender")
    private Set<Message> messagesSent = new HashSet<>();

    @ManyToMany(mappedBy = "recipients")
    @JsonIgnore
    private Set<Chatroom> chatrooms = new HashSet<>();

    public String getRole() {
        return role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Message> getMessagesSent() {
        return messagesSent;
    }

    public void setMessagesSent(Set<Message> messages) {
        this.messagesSent = messages;
    }

    public Set<Chatroom> getChatrooms() {
        return chatrooms;
    }

    public void setChatrooms(Set<Chatroom> chatrooms) {
        this.chatrooms = chatrooms;
    }
}
