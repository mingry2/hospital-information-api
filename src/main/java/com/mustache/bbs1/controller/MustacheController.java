package com.mustache.bbs1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MustacheController {

    @GetMapping("/hi")
    public String mustacheCon(Model model){
        model.addAttribute("username","minkyoung");
        return "greetings";
    }

    @GetMapping("/hi/{id}")
    public String mustacheCon1(@PathVariable String id, Model model){
        model.addAttribute("username","minkyoung");
        model.addAttribute("id",id);
        return "greetings";
    }

}
