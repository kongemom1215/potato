package com.unity.potato.service.mail;

import com.unity.potato.config.EmailConfig;
import com.unity.potato.domain.history.EmailCertHistory;
import com.unity.potato.domain.history.EmailCertHistoryRepository;
import com.unity.potato.domain.vo.EmailVo;
import com.unity.potato.dto.request.EmailAuthRequest;
import com.unity.potato.dto.response.Result;
import com.unity.potato.util.HtmlUtil;
import com.unity.potato.util.RedisUtil;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.unity.potato.util.constants.EmailConstants.*;

@Service
public class MailService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailCertHistoryRepository emailCertHistoryRepository;

    private final EmailConfig emailConfig;

    private final RedisUtil redisUtil;

    private final HtmlUtil htmlUtil;

    @Value("${spring.mail.username}")
    private String hostMail;

    public MailService(JavaMailSender javaMailSender, EmailConfig emailConfig, RedisUtil redisUtil, HtmlUtil htmlUtil) {
        this.javaMailSender = javaMailSender;
        this.emailConfig = emailConfig;
        this.redisUtil = redisUtil;
        this.htmlUtil = htmlUtil;
    }

    /**
     * 인증코드 전송 서비스
     * - 해당 이메일로 저장된 redis 인증키 있는지 check
     * - 랜덤코드 이메일 전송
     * - 이력 저장 및 redis 저장
     */
    @Transactional
    public void sendCodeMail(String email) throws Exception {
        try {
            if(redisUtil.existData(PREFIX_CERTIFICATION+email)){
                redisUtil.delete(PREFIX_CERTIFICATION+email);
            }

            EmailVo emailVo = createMail(email);

            sendHtmlMessage(emailVo);
            saveEmailCertHistory(emailVo);

            redisUtil.add(PREFIX_CERTIFICATION+email , emailVo.getCertCd(), 5);
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }

    /**
     * Html 템플릿 메일 보내기
     */
    public void sendHtmlMessage(EmailVo emailVo) throws Exception {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(emailVo.getEmail());
            helper.setSubject(emailVo.getEmailtitle());
            helper.setText(emailVo.getHtmlContent(), true);
            helper.setFrom(emailVo.getHostAddress());

            javaMailSender.send(message);
        } catch(Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }


    /**
     * [db] email_cert_history에 이력 저장
     * @param emailVo
     */
    public void saveEmailCertHistory(EmailVo emailVo){
        EmailCertHistory emailCertHistory = new EmailCertHistory(emailVo);
        emailCertHistoryRepository.save(emailCertHistory);
    }

    /**
     * 이메일 전송 VO 세팅
     */
    public EmailVo createMail(String email) throws Exception {
        EmailVo mailVo = new EmailVo(emailConfig.getUserName(), HOST_NAME);
        String certCd = generateRandomCode();
        mailVo.setCertCd(certCd);
        System.out.println("createMail email : " + email);
        mailVo.setEmail(email);
        mailVo.setEmailtitle(TITLE_EMAIL_CHECK);
        mailVo.setHtmlContent(selectHtmlBody("certEmail", certCd));
        mailVo.setEmailType('T');

        return mailVo;
    }

    /**
     * option에 따른 HTML 반환
     */
    public String selectHtmlBody(String option, String code) throws Exception {
        if("certEmail".equals(option)){
            String htmlBody = htmlUtil.readHtmlFile("certEmail.html");
            htmlBody= htmlBody.replace("[[code]]", code);
            return htmlBody;
        }
        return null;
    }

    /**
     * 4자리 무작위 숫자 생성
     */
    public static String generateRandomCode() {
        Random random = new Random();
        int randomNum = random.nextInt(10000); // 0부터 9999 사이의 난수 생성
        return String.format("%04d", randomNum); // 4자리로 맞추고 앞을 0으로 채움
    }

    /**
     * 인증코드 확인 서비스
     * - 레디스에 저장된 인증코드와 입력 코드 비교
     * - 인증 성공시 이력 저장 및 redis 저장 데이터 삭제
     */
    @Transactional
    public boolean isCodeVerified(EmailAuthRequest request){
        String email = request.getEmail();
        String redisCode = redisUtil.getValue(PREFIX_CERTIFICATION+email);

        if(redisCode != null){
            if(request.getCode().equals(redisCode)){    // 인증 성공
                EmailVo emailVo = new EmailVo();
                emailVo.setEmailType('V');
                emailVo.setEmail(email);

                saveEmailCertHistory(emailVo);

                redisUtil.delete(PREFIX_CERTIFICATION+email);

                return true;
            }
        }

        return false;
    }

    public Long getEmailTryCount(String email){
        String key = PREFIX_TRY_CERTIFICATION + email;
        Long tryCnt = redisUtil.increment(key);
        redisUtil.addWithExpireDay(key, tryCnt.toString(), 1L);

        return tryCnt;
    }

    public Long getEmailCodeTryCount(EmailAuthRequest request){
        String key = PREFIX_TRY_VERIFICATION + request.getEmail();
        Long tryCnt = redisUtil.increment(key);
        redisUtil.addWithExpireDay(key, tryCnt.toString(), 1L);

        return tryCnt;
    }

    public Map<String, Object> generateUuid(EmailAuthRequest request){
        Map<String, Object> uuidMap = new HashMap<>();
        String uuid = UUID.randomUUID().toString().substring(0,6);
        uuidMap.put("uuid", uuid);

        redisUtil.add(EMAIL_VERIFICATION_ID+request.getEmail(), uuid);

        return uuidMap;
    }

}
