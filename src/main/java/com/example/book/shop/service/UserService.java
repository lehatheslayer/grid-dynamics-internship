package com.example.book.shop.service;

import com.example.book.shop.dto.UserDto;
import com.example.book.shop.entity.user.User;
import com.example.book.shop.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService implements UserDetailsService {
    private static final String LOGIN_EXISTS_MESSAGE = "User with such login already exists";
    private static final String EMAIL_EXISTS_MESSAGE = "User with such email already exists";

    private final UserRepository userRepository;
    private final UserServiceHelper userServiceHelper;

    public UserService(UserRepository userRepository, UserServiceHelper userServiceHelper) {
        this.userRepository = userRepository;
        this.userServiceHelper = userServiceHelper;
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User registerUser(UserDto userDto) {
        return userRepository.save(userServiceHelper.buildUserObject(userDto));
    }

    public Map<String, Object> isAbleToRegisterUser(UserDto userDto, Map<String, Object> model) {
        final String login = userDto.getLogin();
        if (findByLogin(login) != null) {
            model.put("error", LOGIN_EXISTS_MESSAGE);
            return model;
        }

        final String email = userDto.getEmail();
        if (findByEmail(email) != null) {
            model.put("error", EMAIL_EXISTS_MESSAGE);
            return model;
        }

        return model;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }
}
