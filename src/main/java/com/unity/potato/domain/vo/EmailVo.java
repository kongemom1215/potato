package com.unity.potato.domain.vo;

import com.unity.potato.dto.request.EmailAuthRequest;
import jakarta.mail.internet.InternetAddress;

public class EmailVo {
    private String email;
    private String emailtitle;
    private String certCd;
    private String htmlContent;
    private InternetAddress hostAddress;
    private char emailType;

    public EmailVo(String hostMail, String hostName){
        try {
            this.hostAddress = new InternetAddress(hostMail,hostName);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public EmailVo(){
    }

    public char getEmailType() {
        return emailType;
    }

    public void setEmailType(char emailType) {
        this.emailType = emailType;
    }

    public InternetAddress getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(InternetAddress hostAddress) {
        this.hostAddress = hostAddress;
    }

    public String getEmailtitle() {
        return emailtitle;
    }

    public void setEmailtitle(String emailtitle) {
        this.emailtitle = emailtitle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCertCd() {
        return certCd;
    }

    public void setCertCd(String certCd) {
        this.certCd = certCd;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }
}
