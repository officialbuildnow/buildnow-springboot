package com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExtraValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;
    private String value;

    @Setter
    @ManyToOne
    @JsonBackReference(value = "applier-extraValue")
    Applier applier;

    @Builder
    public ExtraValue(
            String category,
            String value
    ){
        this.category = category;
        this.value = value;
    }

    public void updateValue(
            String value
    ){
        this.value = value;
    }

}
