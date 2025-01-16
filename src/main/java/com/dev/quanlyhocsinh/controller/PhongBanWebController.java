package com.dev.quanlyhocsinh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// @RequestMapping("/phongbans")
@Controller
public class PhongBanWebController {

    @GetMapping("/phongbans")
    public String showPhongBanList() {
        return "PhongBan/list-PhongBan"; // Trả về tên template Thymeleaf
    }
}
