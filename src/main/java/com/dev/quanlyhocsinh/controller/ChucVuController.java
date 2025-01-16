package com.dev.quanlyhocsinh.controller;

import com.dev.quanlyhocsinh.model.ChucVu;
import com.dev.quanlyhocsinh.service.ChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/chucvu")
public class ChucVuController {

    @Autowired
    private ChucVuService chucVuService;

    @GetMapping
    public String viewChucVu(Model model) {
        List<ChucVu> chucVus = chucVuService.getAllChucVu();
        model.addAttribute("chucVus", chucVus);
        return "chucvu/index";
    }

    @PostMapping
    public String addChucVu(@ModelAttribute ChucVu chucVu) {
        chucVu.setNgayTao(new Date());
        chucVuService.saveChucVu(chucVu);
        return "redirect:/chucvu";
    }

    @GetMapping("/delete/{id}")
    public String deleteChucVu(@PathVariable("id") Long id) {
        chucVuService.deleteChucVu(id);
        return "redirect:/chucvu";
    }

    @PostMapping("/update")
    public String updateChucVu(@ModelAttribute ChucVu chucVu) {
        ChucVu existingChucVu = chucVuService.getChucVuById(chucVu.getId()).orElse(null);
        if (existingChucVu != null) {
            existingChucVu.setTenChucVu(chucVu.getTenChucVu());
            existingChucVu.setNgaySua(new Date());
            chucVuService.saveChucVu(existingChucVu); // Use service method to save
        }
        return "redirect:/chucvu";
    }
}
