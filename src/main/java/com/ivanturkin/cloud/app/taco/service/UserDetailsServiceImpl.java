package com.ivanturkin.cloud.app.taco.service;

import com.ivanturkin.cloud.app.taco.domain.User;
import com.ivanturkin.cloud.app.taco.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (nonNull(user)) {
            return user;
        }
        throw new UsernameNotFoundException("Username '" + username + "' not found");
    }
}
