package com.buildnow.springbootapp.buildnowspringboot.entitiy.application;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class UpperCategoryScoreBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String upperCategory;

    @ManyToOne
    private Application application;

    @OneToMany(mappedBy = "upperCategoryScoreBoard")
    private List<ScoreBoard> scoreBoardList;
}
