package com.shbhack.studywithsol.user.service;

import com.shbhack.studywithsol.jwt.JwtTokenProvider;
import com.shbhack.studywithsol.transaction.dto.request.TransactionCreateRequest;
import com.shbhack.studywithsol.transaction.service.TransactionService;
import com.shbhack.studywithsol.user.domain.User;
import com.shbhack.studywithsol.user.dto.request.UserAuthenticationRequest;
import com.shbhack.studywithsol.user.dto.request.UserIdCheckRequest;
import com.shbhack.studywithsol.user.dto.request.UserLoginRequest;
import com.shbhack.studywithsol.user.dto.request.UserSignUpRequest;
import com.shbhack.studywithsol.user.dto.response.UserAuthenticationResponse;
import com.shbhack.studywithsol.user.dto.response.UserIdCheckResponse;
import com.shbhack.studywithsol.user.dto.response.UserLoginResponse;
import com.shbhack.studywithsol.user.repository.MessageRepository;
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

    private final TransactionService transactionService;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
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
                .email(userSignUpRequest.email())
                .isParent(userSignUpRequest.isParent())
                .build();

        userRepository.save(user);

        return userRepository.findById(userSignUpRequest.id()).get().getUserId() == user.getUserId();
    }

    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        User user = userRepository.findById(userLoginRequest.id())
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND)); //해당 아이디가 없을때 예외 처리

        if (!passwordEncoder.matches(userLoginRequest.password(), user.getPassword())) {
            throw new BusinessException(ErrorMessage.USER_NOT_FOUND); // 비밀번호가 맞지 않을때 예외 처리
        }

        // 토큰 발행
        String token = JwtTokenProvider.createToken(user.getUserId());
        return UserLoginResponse.of(user, token);
    }


    public Boolean existMainAccount(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND)); //해당 아이디가 없을때 예외 처리

        if(user.getMainAccount() == null){
            return false;
        }
        return true;
    }


    public UserIdCheckResponse idCheck(UserIdCheckRequest userIdCheckRequest) {
        User user = userRepository.findById(userIdCheckRequest.id())
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND)); //해당 아이디가 없을때 예외 처리

        return UserIdCheckResponse.of(user.getId(), user.getName());
    }

    public Boolean oneTransfer(String childId, Long parentUserId) {
        User parent = userRepository.findById(parentUserId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND)); //해당 아이디가 없을때 예외 처리

        User child = userRepository.findById(childId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.CHILD_NOT_FOUND)); //해당 자녀 아이디가 없을때 예외 처리

        //자녀에게 주 계좌가 등록되어 있는지 확인
        if(child.getMainAccount() == null) {
            throw new BusinessException(ErrorMessage.ACCOUNT_NOT_FOUND);
        }

        //랜덤 문구 만들기
        String randomMessage = messageRepository.getRandomMessage().getAdjective() + messageRepository.getRandomMessage().getNoun();

        //부모에 메시지 저장
        parent.setMessage(randomMessage);

        //자녀로 1원 이체
        // 스윗솔 -> 자녀
        transactionService.save(new TransactionCreateRequest(child.getMainAccount().getId(), randomMessage, 1L, true ,child.getName(), "스윗솔" ));


        return true;
    }

}
