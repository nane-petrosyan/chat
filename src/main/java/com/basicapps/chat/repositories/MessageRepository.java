package com.basicapps.chat.repositories;

import com.basicapps.chat.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Optional<Set<Message>> findAllByChatroomId(Long id);
}
