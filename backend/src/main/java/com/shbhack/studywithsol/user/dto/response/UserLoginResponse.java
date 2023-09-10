package com.shbhack.studywithsol.user.dto.response;

import com.shbhack.studywithsol.user.domain.User;

public record UserLoginResponse(
        String id,
        String name,
        String phoneNumber,
        Boolean isParent,
        String token
) {
    public static UserLoginResponse of(User user, String token){
        return new UserLoginResponse(
                user.getId(),
                user.getName(),
                user.getPhoneNumber(),
                user.getIsParent(),
                token
        );
    }
}
