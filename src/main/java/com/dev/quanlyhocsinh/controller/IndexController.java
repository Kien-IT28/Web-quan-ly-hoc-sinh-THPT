package com.dev.quanlyhocsinh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/gioithieu")
    public String showFormThongTin() {
        return "/index/gioithieu";
    }

    @GetMapping("/tuyensinh")
    public String showFormTuyenSinh(){
        return "/index/tuyensinh";
    }

    @GetMapping("/thongbao")
    public String showFormThongBao(){
        return "/index/thongbao";
    }

    @GetMapping("/loading")
    public String showLoadingPage(){
        return "/index/loading";
    }
}
