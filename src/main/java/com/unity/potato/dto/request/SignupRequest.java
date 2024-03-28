package com.unity.potato.dto.request;

public class SignupRequest {
    private String email;
    private String birthDate;
    private String nickname;
    private String password;
    private boolean agreeTermsUse;
    private boolean agreeTermsUseInfo;
    private boolean agreeTermsMarketing;
    private String emailKey;
    private String nicknameKey;

    public boolean hasNullField() {
        return email == null ||
                birthDate == null ||
                password == null ||
                emailKey == null ||
                nicknameKey == null;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAgreeTermsUse() {
        return agreeTermsUse;
    }

    public void setAgreeTermsUse(boolean agreeTermsUse) {
        this.agreeTermsUse = agreeTermsUse;
    }

    public boolean isAgreeTermsUseInfo() {
        return agreeTermsUseInfo;
    }

    public void setAgreeTermsUseInfo(boolean agreeTermsUseInfo) {
        this.agreeTermsUseInfo = agreeTermsUseInfo;
    }

    public boolean isAgreeTermsMarketing() {
        return agreeTermsMarketing;
    }

    public void setAgreeTermsMarketing(boolean agreeTermsMarketing) {
        this.agreeTermsMarketing = agreeTermsMarketing;
    }

    public String getEmailKey() {
        return emailKey;
    }

    public void setEmailKey(String emailKey) {
        this.emailKey = emailKey;
    }

    public String getNicknameKey() {
        return nicknameKey;
    }

    public void setNicknameKey(String nicknameKey) {
        this.nicknameKey = nicknameKey;
    }
}
