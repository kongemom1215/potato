package com.unity.potato.service.user;

import com.unity.potato.util.HtmlUtil;
import com.unity.potato.util.RedisUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class MailService{

    @Autowired
    private JavaMailSender javaMailSender;

    private final RedisUtil redisUtil;

    private final HtmlUtil htmlUtil;

    public MailService(JavaMailSender javaMailSender, RedisUtil redisUtil, HtmlUtil htmlUtil) {
        this.javaMailSender = javaMailSender;
        this.redisUtil = redisUtil;
        this.htmlUtil = htmlUtil;
    }


    public void sendHtmlMessage(String to, String htmlBody, String subject) throws Exception {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlBody, true);
            helper.setFrom(new InternetAddress("commercium@naver.com", "감자조아"));

            javaMailSender.send(message);
        } catch(Exception e){
            e.printStackTrace();
        }
    }


    public void sendCodeMail(String email) throws Exception {
        try {
            String htmlBody = htmlUtil.readHtmlFile("certEmail.html");
            String code = generateRandomCode();
            htmlBody= htmlBody.replace("[[code]]", code);
            String subject = "[감자조아] 이메일 인증 코드 발급";
            sendHtmlMessage(email, htmlBody, subject);

            redisUtil.add("emailCertCode", code, 30);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String generateRandomCode() {
        Random random = new Random();
        int randomNum = random.nextInt(10000); // 0부터 9999 사이의 난수 생성
        return String.format("%04d", randomNum); // 4자리로 맞추고 앞을 0으로 채움
    }


}
