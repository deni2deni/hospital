package com.academy.controller;

import com.academy.model.entity.User;
import com.academy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
        User user = userService.buildUser(name, role);
        userService.save(user);
        return "user_created";
    }
}
