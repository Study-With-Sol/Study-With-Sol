package com.shbhack.studywithsol.user.controller;

import com.shbhack.studywithsol.user.dto.request.UserAuthenticationRequestDto;
import com.shbhack.studywithsol.user.dto.request.UserOneTransferRequestDto;
import com.shbhack.studywithsol.user.dto.request.UserSignUpRequestDto;
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
     1원 이체
     */
    @PostMapping("/1transfer")
    public BaseResponseDto oneTransfer(@RequestBody UserOneTransferRequestDto userOneTransferRequestDto){
        return BaseResponseDto.ok(userService.oneTransfer(userOneTransferRequestDto));
    }

    /*
    1원 이체로 본인 인증
     */
    @PostMapping("/authentication")
    public BaseResponseDto authentication(@RequestBody UserAuthenticationRequestDto userAuthenticationRequestDto){
//        return BaseResponseDto.ok(userService.authentication(userAuthenticationRequestDto));
        return null;
    }
    @PostMapping("/sign-up")
    public BaseResponseDto signUp(@RequestBody UserSignUpRequestDto userSignUpRequestDto){
        return BaseResponseDto.ok(userService.signUp(userSignUpRequestDto));
    }


}
