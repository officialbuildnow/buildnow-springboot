package com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Finance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;
    private String value;

    @Setter
    @ManyToOne
    @JsonBackReference(value="applier-finance")
    private Applier applier;

    @Builder
    public Finance(
            String category,
            String value
    ){
        this.category = category;
        this.value = value;
    }

}
