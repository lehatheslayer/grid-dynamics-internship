package com.example.book.shop.service;

import com.example.book.shop.dto.UserDto;
import com.example.book.shop.entity.user.Role;
import com.example.book.shop.entity.user.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class UserServiceHelper {
    private final PasswordEncoder passwordEncoder;

    public UserServiceHelper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User buildUserObject(UserDto userDto) {
        return User.newBuilder()
                .setFirstName(userDto.getFirstName())
                .setSecondName(userDto.getSecondName())
                .setLogin(userDto.getLogin())
                .setPassword(passwordEncoder.encode(userDto.getPassword()))
                .setEmail(userDto.getEmail())
                .setActive(true)
                .setRoles(new HashSet<>() {{ add(Role.USER); }})
                .build();
    }
}
