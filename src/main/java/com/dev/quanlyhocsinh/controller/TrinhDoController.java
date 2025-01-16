package com.dev.quanlyhocsinh.controller;

import com.dev.quanlyhocsinh.model.TrinhDo;
import com.dev.quanlyhocsinh.service.TrinhDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/trinhdo")
public class TrinhDoController {

    @Autowired
    private TrinhDoService trinhDoService;

    @GetMapping
    public String viewTrinhDos(Model model) {
        List<TrinhDo> trinhDos = trinhDoService.getAllTrinhDos();
        model.addAttribute("trinhDos", trinhDos);
        return "trinhdo/index";
    }

    @PostMapping
    public String addTrinhDo(@ModelAttribute TrinhDo trinhDo) {
        trinhDoService.createTrinhDo(trinhDo);
        return "redirect:/trinhdo";
    }

    @GetMapping("/delete/{id}")
    public String deleteTrinhDo(@PathVariable("id") Long id) {
        trinhDoService.deleteTrinhDo(id);
        return "redirect:/trinhdo";
    }

    @PostMapping("/update")
    public String updateTrinhDo(@ModelAttribute TrinhDo trinhDo) {
        trinhDoService.updateTrinhDo(trinhDo.getId(), trinhDo);
        return "redirect:/trinhdo";
    }
}
