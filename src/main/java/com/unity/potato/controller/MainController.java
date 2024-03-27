package com.unity.potato.controller;

import com.unity.potato.util.LoginUtil;
import com.unity.potato.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.unity.potato.util.constants.EmailConstants.EMAIL_VERIFICATION_ID;

@Controller
public class MainController {

    @Autowired
    private RedisUtil redisUtil;
    private final LoginUtil loginUtil = LoginUtil.getInstance();
    @RequestMapping("/intro")
    public String goHomePage(){
        return "intro";
    }

    @RequestMapping("/community/main")
    public String goMainPage(){
        if(loginUtil.isNotLogin()){
            return "requireLoginMain";
        } else {
            return "communityMain";
        }
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

    @RequestMapping("/community/login/signupForm")
    public String goSignupForm(@RequestParam String uuid, @RequestParam String email){
        System.out.println("uuid2 : " + uuid);
        if(uuid.equals(redisUtil.getValue(EMAIL_VERIFICATION_ID+email))){
            return "signupForm";
        }

        return "400Page";
    }
}
