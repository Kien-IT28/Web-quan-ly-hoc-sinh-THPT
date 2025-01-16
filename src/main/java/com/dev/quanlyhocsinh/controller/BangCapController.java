package com.dev.quanlyhocsinh.controller;

import com.dev.quanlyhocsinh.model.BangCap;
import com.dev.quanlyhocsinh.service.BangCapService;
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
@RequestMapping("/bangcap")
public class BangCapController {

    @Autowired
    private BangCapService bangCapService;

    @GetMapping
    public String viewBangCaps(Model model) {
        model.addAttribute("bangCaps", bangCapService.findAll());
        return "bangcap/index";
    }

    @PostMapping
    public String addBangCap(@Valid BangCap bangCap, BindingResult result, Model model) {
        if (result.hasErrors()){
            model.addAttribute("bangCaps", bangCapService.findAll());
            return "bangcap/index";
        }
        bangCapService.save(bangCap);
        return "redirect:/bangcap";
    }

    @PostMapping("/update")
    public String updateBangCap(@Valid BangCap bangCap, BindingResult result, Model model) {
        if (result.hasErrors()){
            model.addAttribute("bangCaps", bangCapService.findAll());
            return "bangcap/index";
        }
        bangCapService.save(bangCap);
        return "redirect:/bangcap";
    }

    @GetMapping("/delete/{id}")
    public String deleteBangCap(@PathVariable("id") Long id) {
        bangCapService.deleteById(id);
        return "redirect:/bangcap";
    }
}
