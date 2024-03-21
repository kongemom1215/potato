package com.unity.potato.util;


public class LoginUtil {
    private static final LoginUtil INSTANCE = new LoginUtil();

    // private 생성자로 외부에서 인스턴스 생성을 막음
    private LoginUtil() {}

    // 싱글톤 인스턴스 반환 메소드
    public static LoginUtil getInstance() {
        return INSTANCE;
    }

    public boolean isNotLogin() {
        // 로그인 상태를 체크하는 로직 추가
        return true;
    }
}
