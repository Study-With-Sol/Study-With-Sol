package com.shbhack.studywithsol.user.service;

import com.shbhack.studywithsol.user.domain.User;
import com.shbhack.studywithsol.user.dto.request.UserAuthenticationRequest;
import com.shbhack.studywithsol.user.dto.request.UserSignUpRequest;
import com.shbhack.studywithsol.user.dto.response.UserAuthenticationResponse;
import com.shbhack.studywithsol.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public UserAuthenticationResponse authentication(UserAuthenticationRequest userAuthenticationRequestDto) {
        //예금주 조회

        //1원 이체

        return null;
    }

    // ID 중복 검사

    public Boolean duplicationCheck(String id) {
        if(!userRepository.findById(id).isPresent()){
            return false;
        }
        return true;
    }

    public Boolean signUp(UserSignUpRequest userSignUpRequest) {
        //저장
        User user = userRepository.save(userSignUpRequest.toUser());

        return userRepository.findById(userSignUpRequest.id()).get().getUserId() == user.getUserId();
    }

}
