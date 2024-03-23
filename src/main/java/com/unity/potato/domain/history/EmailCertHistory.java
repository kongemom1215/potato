package com.unity.potato.domain.history;

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

    @Column(name="expire_dt")
    private LocalDateTime expireDt;

    @Column(name="cert_fl")
    private char certYn;

    public EmailCertHistory(String emailAddress){
        this.emailAddress = emailAddress;
    }

    public EmailCertHistory(String emailAddress, String certCd, LocalDateTime expireDt, char certYn) {
        this.emailAddress = emailAddress;
        this.certCd = certCd;
        this.expireDt = expireDt;
        this.certYn = certYn;
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

    public LocalDateTime getExpireDt() {
        return expireDt;
    }

    public void setExpireDt(LocalDateTime expireDt) {
        this.expireDt = expireDt;
    }

    public char getCertYn() {
        return certYn;
    }

    public void setCertYn(char certYn) {
        this.certYn = certYn;
    }
}
