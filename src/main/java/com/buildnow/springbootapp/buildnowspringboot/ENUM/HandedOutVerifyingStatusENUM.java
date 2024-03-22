package com.buildnow.springbootapp.buildnowspringboot.ENUM;

import lombok.Getter;

@Getter
public enum HandedOutVerifyingStatusENUM {
    READY("검수전"),
    VERIFIED("유효한서류"),
    FAILED("유효하지않은서류");

    private final String description;

    HandedOutVerifyingStatusENUM(String description){
        this.description = description;
    }
}
