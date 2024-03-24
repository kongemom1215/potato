package com.unity.potato.domain.history;

import com.unity.potato.domain.vo.EmailVo;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="email_cert_history")
public class EmailCertHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="email_id")
    private Long emailId;

    @Column(name="email_address")
    private String emailAddress;

    @Column(name="cert_cd")
    private String certCd;

    @Column(name="send_dt")
    private LocalDateTime sendDt;

    @Column(name="cert_dt")
    private LocalDateTime certDt;

    @Column(name="cert_fl")
    private char certYn = 'N';

    public EmailCertHistory(String emailAddress){
        this.emailAddress = emailAddress;
    }

    public EmailCertHistory(EmailVo emailVo){
        this.emailAddress = emailVo.getEmail();
        this.certCd = emailVo.getCertCd();
        this.sendDt = LocalDateTime.now();
    }

    public LocalDateTime getSendDt() {
        return sendDt;
    }

    public void setSendDt(LocalDateTime sendDt) {
        this.sendDt = sendDt;
    }

    public LocalDateTime getCertDt() {
        return certDt;
    }

    public void setCertDt(LocalDateTime certDt) {
        this.certDt = certDt;
    }

    public Long getEmailId() {
        return emailId;
    }

    public void setEmailId(Long emailId) {
        this.emailId = emailId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCertCd() {
        return certCd;
    }

    public void setCertCd(String certCd) {
        this.certCd = certCd;
    }

    public char getCertYn() {
        return certYn;
    }

    public void setCertYn(char certYn) {
        this.certYn = certYn;
    }
}
