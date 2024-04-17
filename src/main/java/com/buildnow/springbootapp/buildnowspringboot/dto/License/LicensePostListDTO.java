package com.buildnow.springbootapp.buildnowspringboot.dto.License;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class LicensePostListDTO {
    private List<LicensePostDTO> licensePostDTOList;
}
