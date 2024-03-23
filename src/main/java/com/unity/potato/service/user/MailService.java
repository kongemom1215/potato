package com.unity.potato.service.user;

import com.unity.potato.util.HtmlUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;
    //private final RedisUtil redisUtil;
    @Autowired
    private HtmlUtil htmlUtil;

    private MimeMessage createMessage(String code, String email) throws IOException, MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject("[감자조아] 이메일 인증 코드 발급");

        String content = htmlUtil.readHtmlFile();
        content= content.replace("[[code]]", code);
        helper.setText(content, true);

        return  message;
    }

    private void sendMail(String code, String email) throws Exception{
        try {
            MimeMessage mimeMessage = createMessage(code, email);
            javaMailSender.send(mimeMessage);
        } catch (MailException e){
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public String sendCertificationMail(String email) {
        try{
            String code = UUID.randomUUID().toString().substring(0, 4); //랜덤 인증번호 uuid를 이용!
            sendMail(code,email);

            //redisUtil.setDataExpire(code,email,60*5L); // {key,value} 5분동안 저장.

            return  code;
        }catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public static String createRandomCode() {
        return UUID.randomUUID().toString().substring(0, 4);
    }
}
