package com.shbhack.studywithsol.utils.error.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {

    // 에러 메시지 추가하면 됩니다.
    USER_NOT_FOUND(NOT_FOUND, "해당 회원을 찾을 수 없습니다."),
    ACCOUNT_NOT_FOUND(NOT_FOUND, "해당 계좌를 찾을 수 없습니다."),

    STUDY_NOT_FOUNT(NOT_FOUND, "해당 학습을 찾을 수 없습니다."),
    STUDY_ALREADY_APPROVAL(NOT_ACCEPTABLE, "지급 완료된 학습은 취소할 수 없습니다."),
    STUDY_IS_NOT_DONE(NOT_ACCEPTABLE, "완료 되지 않은 학습에 대해서는 지급할 수 없습니다.");

    private final int code;
    private final String phrase;

    ErrorMessage(HttpStatus code, String phrase) {
        this.code = code.value();
        this.phrase = phrase;
    }
}
