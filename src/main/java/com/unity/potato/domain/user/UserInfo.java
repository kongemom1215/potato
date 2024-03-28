package com.unity.potato.domain.user;

import com.unity.potato.dto.request.SignupRequest;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_email", nullable = false, unique = true)
    private String userEmail;

    @Column(name = "user_birth", nullable = false)
    private String userBirth;

    @Column(name = "user_pwd", nullable = false)
    private String userPwd;

    @Column(name = "user_nickname", nullable = false)
    private String userNickname;

    @Column(name = "join_dt")
    private LocalDateTime joinDt;

    @Column(name = "last_login_dt")
    private LocalDateTime lastLoginDt;

    public UserInfo(SignupRequest info, String encodePwd){
        this.userEmail = info.getEmail();
        this.userBirth = info.getBirthDate();
        this.userPwd = encodePwd;
        this.userNickname = info.getNickname();
        this.joinDt = LocalDateTime.now();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(String userBirth) {
        this.userBirth = userBirth;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public LocalDateTime getJoinDt() {
        return joinDt;
    }

    public void setJoinDt(LocalDateTime joinDt) {
        this.joinDt = joinDt;
    }

    public LocalDateTime getLastLoginDt() {
        return lastLoginDt;
    }

    public void setLastLoginDt(LocalDateTime lastLoginDt) {
        this.lastLoginDt = lastLoginDt;
    }
}
