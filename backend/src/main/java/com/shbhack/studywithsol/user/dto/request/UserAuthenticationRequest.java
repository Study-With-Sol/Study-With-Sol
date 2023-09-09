package com.shbhack.studywithsol.user.dto.request;

public record UserAuthenticationRequest(
        String name,
        String inAccount
) {
}
