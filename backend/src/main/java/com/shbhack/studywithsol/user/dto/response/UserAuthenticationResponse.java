package com.shbhack.studywithsol.user.dto.response;

public record UserAuthenticationResponse(
        String name
) {
    /*
    TODO :  이메일 본인인증 구현시 작성
     */
    public static UserAuthenticationResponse of(){
        return null;
    }
}
