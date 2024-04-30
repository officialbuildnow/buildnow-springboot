package com.buildnow.springbootapp.buildnowspringboot.entitiy.application;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TempPrerequisite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String prerequisiteName;
    private Boolean isPrerequisite;
    private String whyMidal;

    @Setter
    @ManyToOne
    @JsonBackReference(value="application-tempPrerequisite")
    private Application application;

    @Builder
    public TempPrerequisite(String prerequisiteName, Boolean isPrerequisite, String whyMidal){
        this.prerequisiteName = prerequisiteName;
        this.isPrerequisite = isPrerequisite;
        this.whyMidal = whyMidal;
    }

    public void updateByAdmin(Boolean isPrerequisite, String whyMidal){
        this.isPrerequisite = isPrerequisite;
        this.whyMidal = whyMidal;
    }
}
