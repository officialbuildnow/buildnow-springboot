package com.buildnow.springbootapp.buildnowspringboot.dto.License;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LicensePostDTO {
    @JsonProperty("licenseName")
    private String licenseName;
    @JsonProperty("licenseNum")
    private String licenseNum;
    @JsonProperty("capacityValue")
    private Long capacityValue;
    @JsonProperty("licenseSeq")
    private String licenseSeq;
    @JsonProperty("licenseYear")
    private String licenseYear;
}
