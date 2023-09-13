package com.shbhack.studywithsol.user.controller;

import com.shbhack.studywithsol.user.dto.request.*;
import com.shbhack.studywithsol.user.dto.response.UserAuthenticationResponse;
import com.shbhack.studywithsol.user.dto.response.UserInfoResponse;
import com.shbhack.studywithsol.user.dto.response.UserLoginResponse;
import com.shbhack.studywithsol.user.service.UserService;
import com.shbhack.studywithsol.utils.dto.response.BaseResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /*
    계좌번호랑 이름을 받고 요청을 하면
    그 계좌번호가 입력한 사람건지 예금주 확인을 해서 1원을 보내준다고 가정하고
    잘보냈다는 응답을 보내고

    프론트에서 사용자가 확인해서 제대로 답을 하면 아이디랑 비밀번호 이름을 받는다.

     1원 이체 -
     */
    @PostMapping("/authentication")
    public BaseResponseDto<UserAuthenticationResponse> authentication(@RequestBody UserAuthenticationRequest userAuthenticationRequest){
        return BaseResponseDto.ok(userService.authentication(userAuthenticationRequest));
    }

    //아이디 중복 체크
    @PostMapping("/duplication-check")
    public BaseResponseDto<String> duplicationCheck(@RequestBody UserDuplicationCheckRequest userDuplicationCheckRequest){
        boolean result= userService.duplicationCheck(userDuplicationCheckRequest.id());
        if(result){
            return BaseResponseDto.message(userDuplicationCheckRequest.id(), "duplicated");
        }
        return BaseResponseDto.ok(userDuplicationCheckRequest.id());
    }

    @PostMapping("/sign-up")
    public BaseResponseDto<Boolean> signUp(@RequestBody UserSignUpRequest userSignUpRequest){
        return BaseResponseDto.ok(userService.signUp(userSignUpRequest));
    }

    @PostMapping("/login")
    public  BaseResponseDto<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest){
        return BaseResponseDto.ok(userService.login(userLoginRequest));
    }

    @GetMapping("/info")
    public BaseResponseDto<UserInfoResponse> getUserInfo(Authentication authentication){
        return BaseResponseDto.ok(userService.getUserInfo(Long.valueOf(authentication.getName())));
    }
    @PostMapping("/password")
    public BaseResponseDto<Boolean> checkPassword(Authentication authentication, @RequestBody UserUpdatePasswordRequest userUpdatePasswordRequest){
        return BaseResponseDto.ok(userService.checkPassword(Long.valueOf(authentication.getName()), userUpdatePasswordRequest));
    }

    @PatchMapping("/password")
    public BaseResponseDto<?> updatePassword(Authentication authentication, @RequestBody UserUpdatePasswordRequest userUpdatePasswordRequest){
        userService.updatePassword(Long.valueOf(authentication.getName()), userUpdatePasswordRequest);
        return BaseResponseDto.ok(null);
    }

    @PatchMapping("/email")
    public BaseResponseDto<?> updateEmail(Authentication authentication, @RequestBody UserUpdateEmailRequest userUpdateEmailRequest){
        userService.updateEmail(Long.valueOf(authentication.getName()), userUpdateEmailRequest);
        return BaseResponseDto.ok(null);
    }

    @PostMapping("/sample")
    public BaseResponseDto sample(Authentication authentication){
        return BaseResponseDto.ok(new String("userId ( pk ) : "+ authentication.getName()));
    }

}
