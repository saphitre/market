package com.example.market.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @PreAuthorize("permitAll()")
    @GetMapping("/")
    public String getAllProducts(){
        return "getAllProducts";
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }
}
