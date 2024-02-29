package com.unity.potato.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String goMainPage(){
        return "main_test";
    }

    @RequestMapping("/potato")
    public String goPotato(){
        return "potato";
    }

}
