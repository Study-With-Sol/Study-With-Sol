package com.shbhack.studywithsol.user.dto.response;

import com.shbhack.studywithsol.user.domain.Connection;
import com.shbhack.studywithsol.user.domain.User;

public record UserParentResponse(
        Long userId,
        String alias
) {
    public static UserParentResponse of(User user, Connection connection){
        return new UserParentResponse(
                user.getUserId(),
                connection.getAlias()
        );
    }
}
