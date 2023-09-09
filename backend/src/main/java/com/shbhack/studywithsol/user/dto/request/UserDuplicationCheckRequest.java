package com.shbhack.studywithsol.user.dto.request;

import javax.validation.constraints.NotNull;

public record UserDuplicationCheckRequest(
        @NotNull
        String id
) {
}
