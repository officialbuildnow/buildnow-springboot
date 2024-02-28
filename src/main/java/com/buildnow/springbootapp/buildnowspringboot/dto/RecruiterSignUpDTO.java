package com.buildnow.springbootapp.buildnowspringboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruiterSignUpDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String businessId;
    @NotBlank
    private String managerName;
    @NotBlank
    private String companyName;

    public RecruiterSignUpDTO(
            @JsonProperty("username") String username,
            @JsonProperty("password") String password,
            @JsonProperty("businessId") String businessId,
            @JsonProperty("managerName") String managerName,
            @JsonProperty("companyName") String companyName
            ){
        this.username = username;
        this.password = password;
        this.businessId = businessId;
        this.managerName = managerName;
        this.companyName = companyName;
    }

}
