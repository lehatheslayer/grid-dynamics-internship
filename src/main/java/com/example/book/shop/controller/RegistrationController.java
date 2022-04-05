package com.example.book.shop.controller;

import com.example.book.shop.dto.UserDto;
import com.example.book.shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Map<String, Object> model) {
        model.put("userForm", new UserDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String register(UserDto userDto, Map<String, Object> model) {
        model = userService.isAbleToRegisterUser(userDto, model);
        if (model.containsKey("error")) {
            return "redirect:/registration";
        }

        userService.registerUser(userDto);
        return "redirect:/login";
    }
}
