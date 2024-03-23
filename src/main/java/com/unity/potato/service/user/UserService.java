package com.unity.potato.service.user;

import com.unity.potato.domain.history.EmailCertHistory;
import com.unity.potato.util.HtmlUtil;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
public class UserService implements UserServiceImpl{

    @Autowired
    private HtmlUtil htmlUtil;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public EmailCertHistory sendAuthEmail(String inputEmail) throws IOException {
        EmailCertHistory emailCertHistory = new EmailCertHistory(inputEmail);

        try {
            // 이메일 전송
            String host = "gamzazoa@kakao.com";
            String subject = "[감자조아] 이메일 인증 코드 발급";
            String content = htmlUtil.readHtmlFile();
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom(host, "감자조아");
            helper.setTo(inputEmail);
            helper.setSubject(subject);

            String randomCode = generateRandom4DigitNumber();
            content= content.replace("[[code]]", randomCode);
            emailCertHistory.setCertCd(randomCode);

            helper.setText(content, true);

            mailSender.send(message);

            //만료 시간 계산
            LocalDateTime currentTime = LocalDateTime.now();
            LocalDateTime expireTime = currentTime.plusMinutes(30);
            emailCertHistory.setExpireDt(expireTime);

        } catch(Exception e){
            System.out.println("erorr : " + e.getMessage());
            return null;
        }


        return emailCertHistory;
    }

    public static String generateRandom4DigitNumber() {
        return UUID.randomUUID().toString().substring(0, 4);
    }
}
