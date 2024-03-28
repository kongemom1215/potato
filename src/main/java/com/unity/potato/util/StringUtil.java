package com.unity.potato.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class StringUtil {
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern NICKNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9]*$");

    public static boolean isEmailType(String input){
        if (input == null || input.isEmpty()) {
            return false;
        }

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static boolean isNicknameType(String nickname) {
        // 닉네임이 null이거나 비어있는 경우는 유효하지 않음
        if (nickname == null || nickname.isEmpty()) {
            return false;
        }

        // 닉네임의 길이가 2글자에서 10글자 사이인지 확인
        if (nickname.length() < 2 || nickname.length() > 10) {
            return false;
        }

        // 닉네임에 띄어쓰기나 특수문자가 있는지 확인
        if (!NICKNAME_PATTERN.matcher(nickname).matches()) {
            return false;
        }

        // 모든 조건을 만족하면 유효한 닉네임
        return true;
    }

    public static String generateUuid(){
        String uuid = UUID.randomUUID().toString();

        return uuid;
    }

    public static boolean isValidDateAndAge(String dateOfBirth) {
        // 생년월일이 유효한지 확인 (YYYYMMDD 형식)
        if (!isValidDateFormat(dateOfBirth)) {
            return false;
        }

        // 14살 이상인지 확인
        if (!isOver14YearsOld(dateOfBirth)) {
            return false;
        }

        return true;
    }

    private static boolean isValidDateFormat(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.setLenient(false); // 엄격한 형식 체크
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private static boolean isOver14YearsOld(String dateOfBirth) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            Date birthDate = sdf.parse(dateOfBirth);
            Calendar cal = Calendar.getInstance();
            cal.setTime(birthDate);
            cal.add(Calendar.YEAR, 14);
            Date date14YearsAgo = cal.getTime();
            return date14YearsAgo.before(new Date());
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isValidPassword(String password, String email) {
        // 1. 비밀번호가 8글자~16글자인지 확인
        if (password.length() < 8 || password.length() > 16) {
            System.out.println("비밀번호는 8글자에서 16글자 사이여야 합니다.");
            return false;
        }

        // 2. 비밀번호가 이메일 주소를 포함하는지 확인
        if (email != null && password.contains(email.split("@")[0])) {
            System.out.println("비밀번호에 이메일 주소를 포함할 수 없습니다.");
            return false;
        }

        // 3. 비밀번호가 영문 대/소문자, 숫자, 기호 중 2개 이상 조합되어 있는지 확인
        int complexityCount = 0;
        if (password.matches(".*[a-z].*")) complexityCount++;
        if (password.matches(".*[A-Z].*")) complexityCount++;
        if (password.matches(".*\\d.*")) complexityCount++;
        if (password.matches(".*[~!@#$%^&*()_+\\-={}\\[\\]:;\"'<>,.?/\\\\].*")) complexityCount++;

        if (complexityCount < 2) {
            System.out.println("비밀번호는 영문 대/소문자, 숫자, 특수기호 중 2개 이상을 조합해야 합니다.");
            return false;
        }

        return true;
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

}

