package com.ssafy.fongfongtrip.global.exception;

public class LoginIdDuplicationException extends RuntimeException {

    public LoginIdDuplicationException() {
        super("중복된 아이디가 존재합니다.");
    }

    public LoginIdDuplicationException(String message) {
        super(message);
    }
}
