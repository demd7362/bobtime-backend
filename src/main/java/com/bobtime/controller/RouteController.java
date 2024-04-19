package com.bobtime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RouteController {

    @GetMapping("/")
    public String index(){
        return "forward:/bobtime-web/index.html";
    }
}
