package com.unity.potato.domain.history;

import com.unity.potato.domain.vo.EmailVo;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="email_cert_history")
public class EmailCertHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="email_address")
    private String emailAddress;

    @Column(name="reg_dt")
    private LocalDateTime regDt;

    @Column(name="cert_type")
    private char certType;

    public EmailCertHistory(String emailAddress){
        this.emailAddress = emailAddress;
    }

    public EmailCertHistory(EmailVo emailVo){
        this.emailAddress = emailVo.getEmail();
        this.regDt = LocalDateTime.now();
        this.certType = emailVo.getEmailType();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public LocalDateTime getRegDt() {
        return regDt;
    }

    public void setRegDt(LocalDateTime regDt) {
        this.regDt = regDt;
    }

    public char getCertType() {
        return certType;
    }

    public void setCertType(char certType) {
        this.certType = certType;
    }
}
