package com.minazg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class welcome {

    @RequestMapping("/welcome")
    public String welcome(Model model) {
        return "welcome";
    }
}