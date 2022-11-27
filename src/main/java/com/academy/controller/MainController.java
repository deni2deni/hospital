package com.academy.controller;

import com.academy.dto.UserCreateDto;
import com.academy.model.entity.Role;
import com.academy.service.UserService;
import com.academy.utils.DateFormatUtil;
import com.academy.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private final SecurityUtil securityUtil;

    @GetMapping
    public String userCreation(Model model) {
        return "main";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/registration")
    public String showRegistrationPage(Model model) {
        model.addAttribute("userCreateDto", new UserCreateDto());
        model.addAttribute("roles", Role.values());
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registerUser(@ModelAttribute UserCreateDto userCreateDto, Model model) {
        userService.registerUser(userCreateDto);
        return "redirect:/redirect";
    }

    @GetMapping(value = "/redirect")
    public String redirect() {
        if (securityUtil.hasRole(Role.ROLE_USER)) {
            return "redirect:/patientPage";
        }
        if ((securityUtil.hasRole(Role.ROLE_NURSE)) || securityUtil.hasRole(Role.ROLE_DOCTOR)) {
            return "redirect:/doctorPage";
        }

        if (securityUtil.hasRole(Role.ROLE_ADMIN)) {
            return "redirect:/adminPage";
        }
        return "/error";
    }
}
