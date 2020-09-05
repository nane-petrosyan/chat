package com.basicapps.chat.repositories;

import com.basicapps.chat.models.Chatroom;
import com.basicapps.chat.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Set;

public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {
//    Optional<Set<Chatroom>> findChatroomsByRecipientsContains(Set<User> users);
    Optional<Set<Chatroom>> findChatroomsByRecipientsContaining(User user);
    Optional<Chatroom> findChatroomById(Long id);
}
