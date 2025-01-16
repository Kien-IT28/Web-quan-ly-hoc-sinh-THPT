package com.dev.quanlyhocsinh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QLHocSinhController {

    @GetMapping("/hocsinh")
    public String showHocSinh(){
        return "/qlhocsinh/hocsinh";
    }

    @GetMapping("/diem")
    public String showDiem(){
        return "/qlhocsinh/diem";
    }
}
