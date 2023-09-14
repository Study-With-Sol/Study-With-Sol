package com.shbhack.studywithsol.user.dto.response;

import com.shbhack.studywithsol.user.domain.User;

public record UserChildInfoResponse(
        String id,
        String name
) {
    public static UserChildInfoResponse of(User user){
        return new UserChildInfoResponse(
                user.getId(),
                user.getName()
        );
    }
}
