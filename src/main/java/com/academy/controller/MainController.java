package com.academy.controller;

import com.academy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;

    @GetMapping
    public String userCreation(Model model) {
        return "main";
    }

    @PostMapping
    public String createNewUser(@RequestParam String name, @RequestParam Integer role) {
        userService.createNewUser(name, role);
        return "user_created";
    }

    @GetMapping(value = "/test")
    public String test(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "redirect:/patients";
    }

    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }
}
