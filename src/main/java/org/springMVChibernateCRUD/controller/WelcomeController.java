package org.springMVChibernateCRUD.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/","/index"})
public class WelcomeController {

//     WELCOME PAGE
    @Value("${msg.title}")
    private String titleString;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", titleString);
        return "users/index";
    }
}
