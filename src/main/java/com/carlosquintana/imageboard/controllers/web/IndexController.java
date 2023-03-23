package com.carlosquintana.imageboard.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("test", "testing");
        return "index";
    }
}
