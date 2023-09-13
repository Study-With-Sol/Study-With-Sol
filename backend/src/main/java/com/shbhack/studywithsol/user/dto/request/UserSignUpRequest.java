package com.shbhack.studywithsol.user.dto.request;

import com.shbhack.studywithsol.user.domain.User;
import com.sun.istack.NotNull;
import lombok.Builder;

@Builder
public record UserSignUpRequest(
        @NotNull
        String id,

        @NotNull
        String password,

        @NotNull
        String name,

        @NotNull
        String email,

        @NotNull
        Boolean isParent

) {
    public User toUser(){
        return new User(id, password, name, email, isParent);
    }
}
