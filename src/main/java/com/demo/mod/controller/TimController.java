package com.demo.mod.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/page")
public class TimController {
    @RequestMapping("/index")
    public String toIndex(){
        return "index";
    }
    @RequestMapping("/login")
    public String signIn(){
        return "login";
    }
    @RequestMapping("/zw")
    public String zw(){
        return "zw";
    }
    @RequestMapping("/person")
    public String person(){
        return "person";
    }
    @RequestMapping("/dep")
    public String dep(){
        return "dep";
    }
    @RequestMapping("/zzdj")
    public String zzdj(){
        return "zzdj";
    }
}
