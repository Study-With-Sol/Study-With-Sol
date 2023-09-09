package com.shbhack.studywithsol.user.dto.response;

public record UserAuthenticationResponse(
        String name
) {
    public static UserAuthenticationResponse of(){
        return null;
    }
}
