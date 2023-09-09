package com.shbhack.studywithsol.user.service;

import com.shbhack.studywithsol.user.dto.request.UserAuthenticationRequestDto;
import com.shbhack.studywithsol.user.dto.request.UserSignUpRequestDto;
import com.shbhack.studywithsol.user.dto.response.UserAuthenticationResponseDto;
import com.shbhack.studywithsol.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public UserAuthenticationResponseDto authentication(UserAuthenticationRequestDto userAuthenticationRequestDto) {
        //예금주 조회

        //1원 이체

        return null;
    }

    public Object signUp(UserSignUpRequestDto userSignUpRequestDto) {
        //id 중복 확인
        return null;
    }

}
