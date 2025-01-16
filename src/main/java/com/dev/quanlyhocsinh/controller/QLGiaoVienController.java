package com.dev.quanlyhocsinh.controller;

import com.dev.quanlyhocsinh.model.Account;
import com.dev.quanlyhocsinh.service.QLGiaoVienService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QLGiaoVienController {

    final QLGiaoVienService qlGiaoVienService;

    @GetMapping("/account-teacher")
    public String showAccountTeacher(Model model) {
        model.addAttribute("accounts", qlGiaoVienService.getAllAccount());
        return "/qlgiaovien/account-teacher";
    }

    @GetMapping("/account-teacher/delete/{id}")
    public String deleteAccount(@PathVariable Long id) {
        qlGiaoVienService.deleteAccountById(id);
        return "redirect:/account-teacher";
    }

    @PostMapping("/account-teacher/add")
    public String addAccount(@ModelAttribute Account account) {
        qlGiaoVienService.addAccount(account);
        return "redirect:/account-teacher";
    }

    @PostMapping("/account-teacher/update")
    public String updateAccount(@ModelAttribute Account account) {
        // Lấy thông tin tài khoản hiện tại từ cơ sở dữ liệu
        Account existingAccount = qlGiaoVienService.getAccountById(account.getId()).orElse(null);
        if (existingAccount != null) {
            // Cập nhật thông tin
            existingAccount.setUsername(account.getUsername());
            existingAccount.setSDT(account.getSDT());
            existingAccount.setEmail(account.getEmail());
            // Lưu lại tài khoản
            qlGiaoVienService.saveAccTeacher(existingAccount);
        }
        return "redirect:/account-teacher";
    }

    @GetMapping("thongtin")
    public String showThongTinGV(){
        return "qlgiaovien/thongtin-gv";
    }

}

