package com.unity.potato.controller;

import com.unity.potato.domain.history.EmailCertHistory;
import com.unity.potato.domain.history.EmailCertHistoryRepository;
import com.unity.potato.domain.user.UserInfoRepository;
import com.unity.potato.dto.response.MessageResponse;
import com.unity.potato.service.user.MailService;
import com.unity.potato.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/signup")
public class SignupController {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private EmailCertHistoryRepository emailCertHistoryRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    /**
        회원가입 이메일 인증 코드 발송
        author : joohye
     */
    @PostMapping("/send-email-auth")
    public ResponseEntity<?> sendEmailAuth(@RequestParam("inputEmail") String inputEmail) throws IOException {
        try {
            //코드 전송
            //EmailCertHistory emailCertHistory = userService.sendAuthEmail(inputEmail);

            mailService.sendCodeMail(inputEmail);

            //이력 저장
            /*if(emailCertHistory != null){
                emailCertHistoryRepository.save(emailCertHistory);
            } else {
                return ResponseEntity.badRequest().body(new MessageResponse("Error : 메일 전송 실패"));
            }*/
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new MessageResponse("Error : " + e.getMessage()));
        }

        return ResponseEntity.ok(new MessageResponse("send email successfully!"));
    }

    /*@PostMapping("/email-auth")
    public ResponseEntity<?> emailAuth(@RequestBody ){
        if(userInfoRepository.existsByUserEmail(inputEmail)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error : 이미 가입되어있는 이메일입니다."));
        }
    }*/
}
