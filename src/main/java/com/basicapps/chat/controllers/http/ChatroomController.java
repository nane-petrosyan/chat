package com.basicapps.chat.controllers.http;

import com.basicapps.chat.services.ChatroomService;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatroomController {
    private ChatroomService chatroomService;

    @Autowired
    public ChatroomController(ChatroomService chatroomService) {
        this.chatroomService = chatroomService;
    }

    @GetMapping("/chatroom")
    public ResponseEntity getMessages(@RequestParam(required = true) String id) {
        return new ResponseEntity(chatroomService.loadMessagesByChatId(id), HttpStatus.OK);
    }
}
