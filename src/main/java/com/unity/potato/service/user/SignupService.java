package com.unity.potato.service.user;

import com.unity.potato.domain.user.UserInfo;
import com.unity.potato.domain.user.UserInfoRepository;
import com.unity.potato.dto.request.SignupRequest;
import com.unity.potato.dto.response.Result;
import com.unity.potato.util.RedisUtil;
import com.unity.potato.util.StringUtil;
import com.unity.potato.util.constants.ValidateConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.unity.potato.util.constants.EmailConstants.EMAIL_VERIFICATION_ID;
import static com.unity.potato.util.constants.ValidateConstants.PREFIX_NICKNAME_VALIDATE;

@Service
public class SignupService {
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private BCryptPasswordEncoder pwdEncoder;

    @Transactional
    public Result signup(SignupRequest request){
        Result result = checkSignupParam(request);

        if(result != null){
            return result;
        }

        String encodePwd = pwdEncoder.encode(request.getPassword());

        UserInfo userInfo = new UserInfo(request, encodePwd);
        UserInfo newUserInfo = userInfoRepository.save(userInfo);

        if (newUserInfo != null && newUserInfo.getUserId() != null) {
            // 저장에 성공한 경우
            return new Result("0000", "회원가입을 성공하였습니다.");
        } else {
            return new Result("9930", "회원가입이 정상적으로 진행되지 않았습니다. 계속될 경우 문의 부탁드립니다.");
        }
    }

    public Result checkSignupParam(SignupRequest request){
        String redisEmailKey = redisUtil.getValue(EMAIL_VERIFICATION_ID+request.getEmail());
        String redisNicknameKey = redisUtil.getValue(PREFIX_NICKNAME_VALIDATE+request.getNickname());

        if(StringUtil.isNullOrEmpty(redisEmailKey) || !redisEmailKey.equals(request.getEmailKey())){
            return new Result("9980", "해당 이메일 인증 여부가 누락되었습니다. 회원가입을 다시 진행해주세요.");
        }
        if(StringUtil.isNullOrEmpty(redisNicknameKey) || !redisNicknameKey.equals(request.getNicknameKey())){
            return new Result("9970", "닉네임 중복여부를 확인해주세요.");
        }

        //DB 메일 체크
        if(userInfoRepository.existsByUserEmail(request.getEmail())){
            redisUtil.delete(EMAIL_VERIFICATION_ID+request.getEmail());
            return new Result("9960","죄송합니다. 입력하신 이메일은 이미 다른 사용자가 사용 중입니다. 회원가입을 다시 진행해주세요.");
        }

        //DB 이메일 체크
        if(userInfoRepository.existsByUserNickname(request.getNickname())){
            redisUtil.delete(PREFIX_NICKNAME_VALIDATE+request.getNickname());
            return new Result("9961","죄송합니다. 선택하신 닉네임은 이미 다른 사용자가 사용 중입니다. 다른 닉네임을 선택해 주세요.");
        }


        if(!StringUtil.isValidDateAndAge(request.getBirthDate())) {
            return new Result("9950", "형식에 맞지않는 생년월일입니다.");
        }

        if(!StringUtil.isValidPassword(request.getPassword(), request.getEmail())){
            return new Result("9951", "형식에 맞지않는 비밀번호입니다.");
        }

        return null;
    }
}
