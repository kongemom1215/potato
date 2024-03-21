package com.unity.potato.domain.user;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "user_information")
public class UserInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_email", nullable = false, unique = true)
    private String userEmail;

    @Column(name = "user_birth", nullable = false)
    private Date userBirth;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @Column(name = "user_nickname", nullable = false)
    private String userNickname;

    @Column(name = "join_date")
    private Date joinDate;

    @Column(name = "last_login_date")
    private Date lastLoginDate;

    // 생성자, getter, setter 등 필요한 메서드들
}