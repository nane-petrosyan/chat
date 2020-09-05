package com.basicapps.chat.controllers.http;

import com.basicapps.chat.models.Chatroom;
import com.basicapps.chat.models.User;
import com.basicapps.chat.models.dto.ChatroomList;
import com.basicapps.chat.models.dto.TokenResponse;
import com.basicapps.chat.models.dto.UsernameList;
import com.basicapps.chat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        userService.signUp(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenResponse> signIn(@RequestBody User user) {
        TokenResponse token = userService.signIn(user);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping("/chats")
    public ResponseEntity<ChatroomList> chats() {
        return new ResponseEntity<>(new ChatroomList(userService.getChats()), HttpStatus.OK);
    }

    @PostMapping("/chat")
    public ResponseEntity<Chatroom> chats(@RequestBody UsernameList usernames) {

        return new ResponseEntity<>(userService.createChatroom(usernames.getUsernames()), HttpStatus.CREATED);
    }

    @GetMapping("/chat")
    public ResponseEntity<Chatroom> chats(@RequestParam String id) {
        return new ResponseEntity<>(userService.getChat(id), HttpStatus.OK);
    }


}
