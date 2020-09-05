package com.basicapps.chat.services;

import com.basicapps.chat.configurations.additional.UserDetailsImpl;
import com.basicapps.chat.exceptions.exceptions.WrongDataFormatException;
import com.basicapps.chat.models.Chatroom;
import com.basicapps.chat.models.Message;
import com.basicapps.chat.models.User;
import com.basicapps.chat.repositories.ChatroomRepository;
import com.basicapps.chat.repositories.MessageRepository;
import com.basicapps.chat.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ChatroomService {
    private MessageRepository messageRepository;
    private ChatroomRepository chatroomRepository;
    private UserRepository userRepository;

    @Autowired
    public ChatroomService(MessageRepository messageRepository, ChatroomRepository chatroomRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.chatroomRepository = chatroomRepository;
        this.userRepository = userRepository;
    }

    public Set<Message> loadMessagesByChatId(String id) {
        long dbID = Long.parseLong(id);

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> user = userRepository.findById(userDetails.getUser().getId());

        if (!user.isPresent()) {
            throw new WrongDataFormatException("user_id");
        }

        Optional<Chatroom> chatroom = chatroomRepository.findChatroomById(dbID);

        if (!chatroom.isPresent()) {
            throw new WrongDataFormatException("chatroom_id");
        }

        Chatroom chatroomEntity = chatroom.get();

        if (!chatroomEntity.getRecipients().contains(user.get())) {
            throw new WrongDataFormatException("chatroom_id");
        }
        Optional<Set<Message>> messages = messageRepository.findAllByChatroomId(dbID);

        return messages.orElseGet(HashSet::new);
    }
}
