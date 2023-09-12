package com.shbhack.studywithsol.user.controller;

import com.shbhack.studywithsol.user.dto.request.UserAuthenticationRequest;
import com.shbhack.studywithsol.user.dto.request.UserSignUpRequest;
import com.shbhack.studywithsol.user.dto.request.UserDuplicationCheckRequest;
import com.shbhack.studywithsol.user.service.UserService;
import com.shbhack.studywithsol.utils.dto.response.BaseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public BaseResponseDto authentication(@RequestBody UserAuthenticationRequest userAuthenticationRequest){
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
    public BaseResponseDto signUp(@RequestBody UserSignUpRequest userSignUpRequest){
        return BaseResponseDto.ok(userService.signUp(userSignUpRequest));
    }


}
