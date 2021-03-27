package com.jayian.businesscard.service.user;

import org.springframework.stereotype.Service;

import java.util.HashMap;

public interface UserService {

    // 회원 가입 : 정보승
    public int joinUser(UserInfoVO joinUser);

    // E-mail 인증 처리 : 정보승
    public int verifyUser(String verify);

    // 회원 로그인 + 회원 정보 조회 (비밀번호를 제외한 모든 정보가 출력) : 정보승
    public UserInfoVO selectUser(HashMap<String, Object> loginUser);

    // 회원 정보 수정 : 정보승
    public int updateUserInfo(UserInfoVO updateUser);

    //대표이미지 가져오기 : 여지원
    public String getProfile(String userid);
}
