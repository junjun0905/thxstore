package com.ssafy.thxstore.common.exceptions;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "C_001", "서버가 터졌습니다."),
    METHOD_NOT_ALLOWED(405, "C_002", "Api는 열려있으나 메소드는 사용 불가합니다."),
    INVALID_INPUT_VALUE(400, "C_003", "적절하지 않은 요청 값입니다."),
    INVALID_TYPE_VALUE(400, "C_004", "요청 값의 타입이 잘못되었습니다."),
    ENTITY_NOT_FOUND(400, "C_005", "지정한 Entity를 찾을 수 없습니다."),

    FAVORITE_DUPLICATED(400, "FA_001", "중복된 즐겨찾기를 추가할 수 없습니다."),

    AUTH_ERROR(400, "AU_001", "인증 관련 오류가 발생했습니다."),
    DUPLICATED_EMAIL(400, "AU_002", "이미 존재하는 E-mail입니다."),
    DUPLICATED_NICKNAME(400, "AU_003", "이미 존재하는 닉네임입니다."),
    UNAUTHORIZED_REDIRECT_URI(400, "AU_004", "인증되지 않은 REDIRECT_URI입니다."),
    BAD_LOGIN(400, "AU_005", "잘못된 아이디 또는 패스워드입니다.");

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}