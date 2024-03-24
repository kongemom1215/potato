package com.unity.potato.controller;

import com.unity.potato.domain.user.UserInfoRepository;
import com.unity.potato.dto.request.EmailAuthRequest;
import com.unity.potato.dto.response.Result;
import com.unity.potato.service.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/signup")
public class SignupController {

    @Autowired
    private MailService mailService;

    @Autowired
    private UserInfoRepository userInfoRepository;

    /**
        회원가입 이메일 인증 코드 발송
     */
    @PostMapping("/send-email-auth")
    public ResponseEntity<?> sendEmailAuth(@RequestParam("inputEmail") String inputEmail) throws IOException {
        try {
            if(mailService.getEmailTryCount(inputEmail) > 10){
                return ResponseEntity.ok(new Result("9998","인증 시도 횟수가 10회 넘었습니다. 내일 다시 시도하시길 바랍니다."));
            }
            mailService.sendCodeMail(inputEmail);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new Result("Error : " + e.getMessage()));
        }

        return ResponseEntity.ok(new Result("0000","이메일 코드 전송 성공"));
    }

    /**
     * 회원가입 인증 메일 코드 확인
     */
    @PostMapping("/email-auth")
    public ResponseEntity<?> emailAuth(@RequestBody EmailAuthRequest request) {
        try {
            if(userInfoRepository.existsByUserEmail(request.getEmail())) {
                return ResponseEntity.ok(new Result("9997","이미 가입되어있는 이메일입니다."));
            }
            if(mailService.getEmailCodeTryCount(request) > 10){
                return ResponseEntity.ok(new Result("9998","코드 인증 시도 횟수가 10회 넘었습니다. 내일 다시 시도하시길 바랍니다."));
            }
            if(mailService.isCodeVerified(request)){
                return ResponseEntity.ok(new Result("0000","ok"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(new Result("9999","만료됐거나 올바르지 않은 코드입니다."));
    }
}
