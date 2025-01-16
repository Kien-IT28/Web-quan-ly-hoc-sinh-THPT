package com.dev.quanlyhocsinh.controller;

import com.dev.quanlyhocsinh.model.ChuyenMon;
import com.dev.quanlyhocsinh.service.ChuyenMonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/chuyenmon")
public class ChuyenMonController {

    @Autowired
    private ChuyenMonService chuyenMonService;

    @GetMapping
    public String viewChuyenMon(Model model) {
        model.addAttribute("chuyenMons", chuyenMonService.findAll());
        return "chuyenmon/index";
    }

    @PostMapping
    public String addChuyenMon(@ModelAttribute ChuyenMon chuyenMon, RedirectAttributes redirectAttributes) {
        try {
            chuyenMonService.save(chuyenMon);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm môn học thành công!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/chuyenmon";
    }

    @PostMapping("/update")
    public String updateChuyenMon(@ModelAttribute ChuyenMon chuyenMon, RedirectAttributes redirectAttributes) {
        try {
            chuyenMonService.save(chuyenMon);
            redirectAttributes.addFlashAttribute("successMessage", "Chỉnh sửa môn học thành công!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/chuyenmon";
    }

    @GetMapping("/delete/{id}")
    public String deleteChuyenMon(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        chuyenMonService.deleteById(id);
        redirectAttributes.addFlashAttribute("deleteSuccess", "Xóa môn học thành công!");
        return "redirect:/chuyenmon";
    }
}
