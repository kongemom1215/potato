package com.unity.potato.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/intro")
    public String goHomePage(){
        return "intro";
    }

    @RequestMapping("/community/main")
    public String goMainPage(){
        return "communityMain";
    }

}
