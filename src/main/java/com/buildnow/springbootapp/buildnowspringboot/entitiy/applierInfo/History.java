package com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate day;
    private String detail;

    @ManyToOne
    @JsonBackReference
    private Applier applier;
}
