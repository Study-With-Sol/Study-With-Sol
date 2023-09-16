package com.shbhack.studywithsol.user.controller;

import com.shbhack.studywithsol.user.dto.request.*;
import com.shbhack.studywithsol.user.dto.response.UserIdCheckResponse;
import com.shbhack.studywithsol.user.dto.response.UserLoginResponse;
import com.shbhack.studywithsol.user.service.UserService;
import com.shbhack.studywithsol.utils.dto.response.BaseResponseDto;
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


    @PostMapping("/authentication") //본인 인증
    public BaseResponseDto<?> authentication(@RequestBody UserAuthenticationRequest userAuthenticationRequest){
        return BaseResponseDto.ok(userService.authentication(userAuthenticationRequest));
    }

    @PostMapping("/duplication-check") //아이디 중복 체크
    public BaseResponseDto<String> duplicationCheck(@RequestBody UserDuplicationCheckRequest userDuplicationCheckRequest){
        boolean result= userService.duplicationCheck(userDuplicationCheckRequest.id());
        if(result){
            return BaseResponseDto.message(userDuplicationCheckRequest.id(), "duplicated");
        }
        return BaseResponseDto.ok(userDuplicationCheckRequest.id());
    }

    @PostMapping("/sign-up") // 회원 가입
    public BaseResponseDto<Boolean> signUp(@RequestBody UserSignUpRequest userSignUpRequest){
        return BaseResponseDto.ok(userService.signUp(userSignUpRequest));
    }

    @PostMapping("/login") //로그인
    public  BaseResponseDto<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest){
        return BaseResponseDto.ok(userService.login(userLoginRequest));
    }

    @GetMapping("/main-account") //주계좌 여부 확인
    public BaseResponseDto<Boolean> existMainAccount(Authentication authentication){
        return BaseResponseDto.ok(userService.existMainAccount(Long.valueOf(authentication.getName())));
    }
    @PostMapping("/id-check") //자녀등록을 위한 아이디 확인 -> 아이디의 사용자 이름 반환
    public BaseResponseDto<UserIdCheckResponse> idCheck(@RequestBody UserIdCheckRequest userIdCheckRequest){
        return BaseResponseDto.ok(userService.idCheck(userIdCheckRequest));
    }

    @GetMapping("/one-transfer/{childId}") //1원 이체
    public BaseResponseDto<Boolean> oneTransfer(@PathVariable String childId, Authentication authentication){
        return BaseResponseDto.ok(userService.oneTransfer(childId, Long.valueOf(authentication.getName())));
    }

    @PostMapping("/register") //자녀에게 1원이체 보낸 메시지 확인
    public BaseResponseDto registerChild(@RequestBody UserRegisterChildRequest userRegisterChildRequest, Authentication authentication){
        Boolean result = userService.registerChild(userRegisterChildRequest, Long.valueOf(authentication.getName()));
        if(!result){
            return BaseResponseDto.error(new String("메시지가 일치하지 않습니다."));
        }
        return BaseResponseDto.ok(result);
    }


    /**
     * 샘플코드
     * 지워야함
     */
    @PostMapping("/sample")
    public BaseResponseDto sample(Authentication authentication){
        return BaseResponseDto.ok(new String("userId ( pk ) : "+ authentication.getName()));
    }

}
