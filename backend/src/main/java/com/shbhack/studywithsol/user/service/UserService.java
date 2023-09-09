package com.shbhack.studywithsol.user.service;

import com.shbhack.studywithsol.user.domain.User;
import com.shbhack.studywithsol.user.dto.request.UserAuthenticationRequest;
import com.shbhack.studywithsol.user.dto.request.UserSignUpRequest;
import com.shbhack.studywithsol.user.dto.response.UserAuthenticationResponse;
import com.shbhack.studywithsol.user.repository.UserRepository;
import com.shbhack.studywithsol.utils.error.enums.ErrorMessage;
import com.shbhack.studywithsol.utils.error.exception.custom.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
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
        //Id 중복 확인
        userRepository.findById(userSignUpRequest.id()).ifPresent(
                user -> { throw new BusinessException(ErrorMessage.USER_ID_DUPLICATED);}
        );

        //저장
        User user = User.builder()
                .id(userSignUpRequest.id())
                .password(passwordEncoder.encode(userSignUpRequest.password()))
                .name(userSignUpRequest.name())
                .phoneNumber(userSignUpRequest.phoneNumber())
                .isParent(userSignUpRequest.isParent())
                .build();

        userRepository.save(user);

        return userRepository.findById(userSignUpRequest.id()).get().getUserId() == user.getUserId();
    }

}
