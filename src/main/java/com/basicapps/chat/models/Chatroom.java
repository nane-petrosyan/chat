package com.basicapps.chat.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "chatrooms")
public class Chatroom {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToMany
    private Set<User> recipients;

    @OneToMany(mappedBy = "chatroom")
    @OrderBy("sentAt DESC ")
    private Set<Message> messages = new HashSet<>();

    public Chatroom() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<User> getRecipients() {
        return recipients;
    }

    public void setRecipients(Set<User> reciepents) {
        this.recipients = reciepents;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }
}
