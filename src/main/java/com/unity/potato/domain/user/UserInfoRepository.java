package com.unity.potato.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    boolean existsByUserEmail (String inputEmail);

    boolean existsByUserNickname (String inputNickname);
}
