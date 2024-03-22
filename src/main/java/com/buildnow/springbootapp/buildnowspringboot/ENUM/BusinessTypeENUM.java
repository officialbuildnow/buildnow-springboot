package com.buildnow.springbootapp.buildnowspringboot.ENUM;

import lombok.Getter;

@Getter
public enum BusinessTypeENUM {
    PERSONAL("개인 사업자"),
    CORPORATION("법인");

    private final String description;
    BusinessTypeENUM(String description){
        this.description = description;
    }

}
