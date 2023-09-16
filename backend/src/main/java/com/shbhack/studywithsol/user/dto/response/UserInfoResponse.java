package com.shbhack.studywithsol.user.dto.response;

import com.shbhack.studywithsol.account.domain.Account;
import com.shbhack.studywithsol.user.domain.User;

public record UserInfoResponse (
        String id,
        String name,
        String email,
        Boolean isParent,
        Account mainAccout
){
    public static UserInfoResponse of(User user){
        return new UserInfoResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getIsParent(),
                user.getMainAccount()
        );
    }

}
