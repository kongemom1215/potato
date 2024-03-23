package com.unity.potato.domain.user;

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


    public UserInfo(Long userId, String userEmail, String userBirth, String userPwd, String userNickname, LocalDateTime joinDt, LocalDateTime lastLoginDt) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userBirth = userBirth;
        this.userPwd = userPwd;
        this.userNickname = userNickname;
        this.joinDt = joinDt;
        this.lastLoginDt = lastLoginDt;
    }
}
