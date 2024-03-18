package com.buildnow.springbootapp.buildnowspringboot.ENUM;

import lombok.Getter;

@Getter
public enum RequiredLevelENUM {
    REQUIRED("필수"),
    PREFERRED("우대");

    private final String description;

    RequiredLevelENUM(String description){
        this.description = description;
    }


}
