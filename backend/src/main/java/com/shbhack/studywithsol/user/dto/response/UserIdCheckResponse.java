package com.shbhack.studywithsol.user.dto.response;

import com.shbhack.studywithsol.user.domain.User;
import lombok.Builder;

@Builder
public record UserIdCheckResponse(
        String id,
        String name
) {
    public static UserIdCheckResponse of(String id, String name){
        return new UserIdCheckResponse(id, name);
    }
}
