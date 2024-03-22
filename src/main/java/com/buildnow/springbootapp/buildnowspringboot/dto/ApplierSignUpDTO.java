package com.buildnow.springbootapp.buildnowspringboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApplierSignUpDTO {
    private String companyName;
    private String businessId;
    private String managerName;
    private String managerPhoneNum;
    private String managerEmail;

    private String username;
    private String password;
    public ApplierSignUpDTO(
            @JsonProperty("companyName") String companyName,
            @JsonProperty("businessId") String businessId,
            @JsonProperty("managerName") String managerName,
            @JsonProperty("managerPhoneNum") String managerPhoneNum,
            @JsonProperty("managerEmail") String managerEmail,
            @JsonProperty("username") String username,
            @JsonProperty("password") String password
    ){
        this.companyName = companyName;
        this.businessId = businessId;
        this.managerName = managerName;
        this.managerPhoneNum = managerPhoneNum;
        this.managerEmail = managerEmail;
        this.username = username;
        this.password = password;
    }
}
