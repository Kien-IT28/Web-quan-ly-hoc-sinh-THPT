package com.dev.quanlyhocsinh.controller;

import com.dev.quanlyhocsinh.model.LopHoc;
import com.dev.quanlyhocsinh.service.LopHocService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/qllophoc")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class QLLopHoc {

    final LopHocService lopHocService;

    // View the list of LopHoc
    @GetMapping("/lophoc")
    public String viewLopHoc(Model model) {
        // Add list of classes to the model
        model.addAttribute("lopHocs", lopHocService.findAll());
        return "qllophoc/lophoc";
    }

    // Thêm lớp học mới
    @PostMapping("/add")
    public String addLopHoc(@ModelAttribute LopHoc lopHoc, RedirectAttributes redirectAttributes) {
        try {
            // Cố gắng lưu lớp học mới
            lopHocService.add(lopHoc);
            // Thêm thông báo thành công
            redirectAttributes.addFlashAttribute("successMessage", "Thêm lớp học thành công!");
        } catch (IllegalArgumentException e) {
            // Thêm thông báo lỗi nếu lớp học đã tồn tại
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        // Chuyển hướng đến trang danh sách để hiển thị lớp học đã cập nhật
        return "redirect:/qllophoc/lophoc";
    }

    @PostMapping("/edit")
    public String editLopHoc(@ModelAttribute LopHoc lopHoc, RedirectAttributes redirectAttributes) {
        try {
            lopHocService.edit(lopHoc);
            redirectAttributes.addFlashAttribute("successMessage", "Chỉnh sửa lớp học thành công!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/qllophoc/lophoc";
    }

    @GetMapping("/delete/{id}")
    public String deleteLop(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            lopHocService.deleteLopHoc(id);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa lớp học thành công!");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/qllophoc/lophoc";
    }

}
