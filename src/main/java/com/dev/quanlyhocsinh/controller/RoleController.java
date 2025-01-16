package com.dev.quanlyhocsinh.controller;

import com.dev.quanlyhocsinh.model.Role;
import com.dev.quanlyhocsinh.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String getAllRoles(Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        return "/qlrole/roles";
    }

    @GetMapping("/{id}")
    public String getRoleById(@PathVariable Long id, Model model) {
        model.addAttribute("role", roleService.findById(id));
        return "/qlrole/roles";
    }

    @PostMapping("/add")
    public String addRole(@ModelAttribute Role role, RedirectAttributes redirectAttributes) {
        try {
            roleService.save(role);
            redirectAttributes.addFlashAttribute("addSuccess", "Thêm Role thành công!");
        } catch (IllegalArgumentException e) {
            // Nếu role đã tồn tại, gửi thông báo lỗi
            redirectAttributes.addFlashAttribute("addError", e.getMessage());
        }
        return "redirect:/roles";
    }

    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable ("id") Long id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("deleteSuccess", "Xóa Role thành công!");
        roleService.deleteById(id);
        return "redirect:/roles";
    }
}

