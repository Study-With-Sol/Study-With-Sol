package com.shbhack.studywithsol.utils.error.dto;

public record ErrorResponseDto(int code, String phrase) {
    public static ErrorResponseDto of(int code,String phrase){
        return new ErrorResponseDto(code, phrase);
    }
}