package com.shbhack.studywithsol.user.dto.request;

import com.sun.istack.NotNull;

import javax.persistence.Column;

public record UserSignUpRequestDto(
        @NotNull
        Boolean isParent,

        @NotNull
        String nickname,

        @NotNull
        String password,
        @NotNull
        String name,
        @NotNull
        String email,

        String phoneNumber

) {
}
