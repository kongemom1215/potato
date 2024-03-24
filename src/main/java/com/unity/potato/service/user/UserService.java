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

}
