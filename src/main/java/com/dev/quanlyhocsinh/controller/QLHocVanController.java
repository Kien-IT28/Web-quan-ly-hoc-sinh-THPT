package com.dev.quanlyhocsinh.controller;

import com.dev.quanlyhocsinh.model.ChungChi;
import com.dev.quanlyhocsinh.service.QLHocVanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chungchi")
public class QLHocVanController {

    @Autowired
    private QLHocVanService qlHocVanService;

    @GetMapping
    public String viewChungChi(Model model) {
        model.addAttribute("chungChis", qlHocVanService.findAll());  // Display the list of certificates
        model.addAttribute("chungChi", new ChungChi()); // Initialize an empty ChungChi object
        return "qlhocvan/chungchi"; // Return the HTML page
    }

    @PostMapping
    public String addChungChi(@Valid ChungChi chungChi, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("chungChis", qlHocVanService.findAll());
            return "qlhocvan/chungchi";  // If validation errors exist, return to the form
        }

        try {
            qlHocVanService.save(chungChi);  // Try to save the certificate
            model.addAttribute("successMessage", "Chứng chỉ đã được thêm thành công!");  // Success message
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());  // Error message if certificate already exists
        }

        model.addAttribute("chungChis", qlHocVanService.findAll());
        return "qlhocvan/chungchi";  // Return the form with the list of certificates
    }

    @GetMapping("/edit/{id}")
    public String editChungChi(@PathVariable("id") Long id, Model model) {
        ChungChi chungChi = qlHocVanService.findById(id);
        model.addAttribute("chungChi", chungChi);
        return "redirect:/chungchi";  // Redirect back to the list page
    }

    @GetMapping("/delete/{id}")
    public String deleteChungChi(@PathVariable("id") Long id) {
        qlHocVanService.deleteById(id);
        return "redirect:/chungchi";  // Redirect after deleting
    }
}
