package com.example.book.shop.user.service;

import com.example.book.shop.entity.user.Role;
import com.example.book.shop.entity.user.User;
import com.example.book.shop.repository.UserRepository;
import com.example.book.shop.service.UserService;

import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findUserByLoginTest() {
        Mockito.when(userRepository.findByLogin(Mockito.anyString()))
                .thenReturn(new User(1L,
                        "Almas",
                        "Turganbiev",
                        "krutoy",
                        "password",
                        "almik@mail.ru",
                        true,
                        new HashSet<>() {{
                            add(Role.USER);
                        }})
                );

        final User user = userService.findByLogin("krutoy");

        assertEquals("Almas", user.getFirstName());
        assertEquals("Turganbiev", user.getSecondName());
        assertEquals("almik@mail.ru", user.getEmail());
    }
}
