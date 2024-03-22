package com.buildnow.springbootapp.buildnowspringboot.ENUM;

import lombok.Getter;

@Getter
public enum UpperCategoryENUM {
    APPLICATION("등록신청서"),
    BUSINESS("경영일반"),
    ECONOMIC("재무부문"),
    AUTHENTICATION("인증현황"),
    PERFORMANCE("시공실적"),
    PATENT("특허");

    private final String description;
    UpperCategoryENUM(String description) {
        this.description = description;
    }
}
