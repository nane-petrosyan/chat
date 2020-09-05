package com.basicapps.chat.configurations.additional;

import com.basicapps.chat.exceptions.exceptions.InvalidCredentialsException;
import com.basicapps.chat.models.User;
import com.basicapps.chat.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsProvider implements UserDetailsService {
    final
    UserRepository userRepository;

    public UserDetailsProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }

        return new UserDetailsImpl(userOptional.get());
    }
}
