package com.shbhack.studywithsol.user.service;

import com.shbhack.studywithsol.user.dto.request.UserOneTransferRequestDto;
import com.shbhack.studywithsol.user.dto.request.UserSignUpRequestDto;
import com.shbhack.studywithsol.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    WebClient webClient = WebClient
            .builder().baseUrl("https://shbhack.shinhan.com").build();

    private final UserRepository userRepository;
    public Object signUp(UserSignUpRequestDto userSignUpRequestDto) {
        //id 중복 확인
        return null;
    }

    public Object oneTransfer(UserOneTransferRequestDto userOneTransferRequestDto) {

    }
}
