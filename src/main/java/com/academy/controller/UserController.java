package com.academy.controller;

import com.academy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @GetMapping(value = "/users")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }
}
