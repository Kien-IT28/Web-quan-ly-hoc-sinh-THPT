package com.dev.quanlyhocsinh.controller;



import com.dev.quanlyhocsinh.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class AuthController {
    private final AccountService accountService;

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    // Hiển thị trang quên mật khẩu
    @GetMapping("/forgot-password")
    public String showForgotPassword(){
        return "forgot-password";
    }

    // Hiển thị trang reset mật khẩu
    @GetMapping("/reset-password")
    public String showResetPasswordPage() {
        return "reset-password";
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam("email") String email, Model model) {
        // Nhận kết quả từ service
        String result = accountService.forgotPassword(email);

        if (result.equals("Đã gửi mã OTP tới email của bạn!")) {
            model.addAttribute("successMessage", result);
            return "forgot-password"; // Quay lại trang quên mật khẩu với thông báo thành công
        } else {
            model.addAttribute("errorMessage", result);
            return "forgot-password";
        }
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam("email") String email,
                                @RequestParam("otp") String otp,
                                @RequestParam("newPassword") String newPassword,
                                Model model) {
        // Gọi service để xử lý đặt lại mật khẩu
        String result = accountService.resetPassword(email, otp, newPassword);

        if (result.equals("Đổi mật khẩu thành công!")) { // Sửa so sánh cho đúng chuỗi trả về
            model.addAttribute("successMessage", result); // Thêm successMessage
        } else {
            model.addAttribute("errorMessage", result); // Thêm errorMessage
        }

        return "reset-password"; // Trả về trang reset-password
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
