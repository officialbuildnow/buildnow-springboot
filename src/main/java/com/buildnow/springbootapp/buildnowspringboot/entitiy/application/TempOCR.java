package com.buildnow.springbootapp.buildnowspringboot.entitiy.application;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class TempOCR { //OCR로 불러온 협력업체신청서 임시저장 테이블
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;
    private String value;
    private Boolean isVerified;
    @Setter
    @ManyToOne
    @JsonBackReference(value="application-tempOCR")
    private Application application;
    @Builder
    public TempOCR(String category, String value){
        this.category = category;
        this.value = value;
        this.isVerified = false;
    }
}
