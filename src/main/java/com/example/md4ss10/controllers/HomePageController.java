package com.example.md4ss10.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("index")
public class HomePageController {
    @GetMapping()
    public String test(){
        System.out.println("test");
        return "index";
    }
}
