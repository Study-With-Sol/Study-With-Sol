package com.shbhack.studywithsol.user.dto.response;

public record UserAuthenticationResponseDto(
        String name
) {
    public static UserAuthenticationResponseDto of(){
        return null;
    }
}
