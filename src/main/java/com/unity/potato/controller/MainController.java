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

    @RequestMapping("/community/notLogin/main")
    public String goRequireLoginMainPage(){
        return "requireLoginMain";
    }

    @RequestMapping("/community/login")
    public String goLoginPage(){
        return "login";
    }

    @RequestMapping("/community/login/findPwd")
    public String goFindPasswordPage(){
        return "findPassword";
    }

    @RequestMapping("/community/login/signup")
    public String goSignupPage(){
        return "signup";
    }

    @RequestMapping("/community/board/free")
    public String goFreeBoard(){
        return "freeBoard";
    }
}
