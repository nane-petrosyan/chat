package com.basicapps.chat.controllers.ws;

import com.basicapps.chat.models.Message;
import com.basicapps.chat.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ChatMessaging {
    private MessageRepository messageRepository;

    @Autowired
    public ChatMessaging(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @MessageMapping("/receive/{id}")
    @SendTo("/receive/{id}")
    public void receiveMessage(@PathVariable String id, @Payload Message message) {
        messageRepository.save(message);
    }
}
