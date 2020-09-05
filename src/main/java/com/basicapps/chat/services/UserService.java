package com.basicapps.chat.services;

import com.basicapps.chat.configurations.additional.UserDetailsImpl;
import com.basicapps.chat.exceptions.exceptions.InvalidCredentialsException;
import com.basicapps.chat.exceptions.exceptions.UserAlreadyExistsException;
import com.basicapps.chat.exceptions.exceptions.WrongDataFormatException;
import com.basicapps.chat.models.Chatroom;
import com.basicapps.chat.models.User;
import com.basicapps.chat.models.dto.TokenResponse;
import com.basicapps.chat.repositories.ChatroomRepository;
import com.basicapps.chat.repositories.UserRepository;
import com.basicapps.chat.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private UserRepository userRepository;
    private ChatroomRepository chatroomRepository;
    private JwtTokenUtil jwtTokenUtil;
    private PasswordEncoder bCryptPasswordEncoder;
    private AuthenticationManager authenticationManager;

    @Autowired
    public UserService(UserRepository userRepository,
                       ChatroomRepository chatroomRepository,
                       JwtTokenUtil jwtTokenUtil,
                       PasswordEncoder bCryptPasswordEncoder,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.chatroomRepository = chatroomRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public void signUp(User user) {
        if (user.getUsername() == null) {
            throw new WrongDataFormatException("username");
        }
        if (user.getUserInfo() == null) {
            throw new WrongDataFormatException("info");
        }

        if (user.getUserInfo().getFirstName() == null) {
            throw new WrongDataFormatException("first_name");
        }
        if (user.getUserInfo().getLastName() == null) {
            throw new WrongDataFormatException("last_name");
        }
        if (user.getUserInfo().getEmail() == null) {
            throw new WrongDataFormatException("email");
        }
        if (user.getPassword() == null) {
            throw new WrongDataFormatException("password");
        }

        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException("username");
        }
        userOptional = userRepository.findByUserInfoEmail(user.getUserInfo().getEmail());
        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException("email");
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public TokenResponse signIn(User user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),
                    user.getPassword()));
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException();
        }

        String token = jwtTokenUtil.generateToken(user);
        return new TokenResponse(token, jwtTokenUtil.getExpiration(token), null);
    }

    public Set<Chatroom> getChats() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userOptional = userRepository.findById(userDetails.getUser().getId());


        if (!userOptional.isPresent()) {
            throw new InvalidCredentialsException();
        }

        User user = userOptional.get();

        return user.getChatrooms();
    }

    public Chatroom createChatroom(List<String> usernames) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findByUsername(userDetails.getUsername());

        if (!user.isPresent()) {
            throw new InvalidCredentialsException();
        }

        Set<User> users = new HashSet<>();
        users.add(user.get());

        for (String username : usernames) {
            Optional<User> addedUserOptional = userRepository.findByUsername(username);
            addedUserOptional.ifPresent(users::add);
        }

        Chatroom chatroom = new Chatroom();
        chatroom.setRecipients(users);

        return chatroomRepository.save(chatroom);
    }

    public Chatroom getChat(String chatId) {
        Optional<Chatroom> chat = chatroomRepository.findChatroomById(Long.parseLong(chatId));

        if (!chat.isPresent()) {
            throw new InvalidCredentialsException();
        }

        return chat.get();
    }

}
