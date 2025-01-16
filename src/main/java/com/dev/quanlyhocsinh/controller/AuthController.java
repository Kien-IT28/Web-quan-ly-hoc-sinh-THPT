package com.dev.quanlyhocsinh.controller;



import com.dev.quanlyhocsinh.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class AuthController {
    private final AccountService accountService;

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/forgot-password")
    public String forgotpassword(){
        return "/forgot-password";
    }

    @GetMapping("/home")
    public String test(){
        return "/home";
    }

    @GetMapping("/403")
    public String accesdenied(){
        return "403";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @GetMapping("/index")
    public String showFormIndex(){
        return "/index";
    }

}
