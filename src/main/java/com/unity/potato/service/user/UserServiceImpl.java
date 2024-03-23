package com.unity.potato.service.user;

import com.unity.potato.domain.history.EmailCertHistory;

import java.io.IOException;

public interface UserServiceImpl {
    public EmailCertHistory sendAuthEmail(String inputEmail) throws IOException;
}
