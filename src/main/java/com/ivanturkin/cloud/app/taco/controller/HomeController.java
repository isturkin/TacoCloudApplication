package com.ivanturkin.cloud.app.taco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class HomeController {

    public String home() {
        return "home";
    }
}
