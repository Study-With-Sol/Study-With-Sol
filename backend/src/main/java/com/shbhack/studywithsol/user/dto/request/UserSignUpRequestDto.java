package com.shbhack.studywithsol.user.dto.request;

import com.shbhack.studywithsol.user.domain.User;
import com.sun.istack.NotNull;
import lombok.Builder;

import javax.persistence.Column;

@Builder
public record UserSignUpRequestDto(
        @NotNull
        String id,

        @NotNull
        String password,

        @NotNull
        String name,

        String phoneNumber,

        @NotNull
        Boolean isParent

) {
    public User toUser(){
        return new User(id, password, name, phoneNumber, isParent);
    }
}
