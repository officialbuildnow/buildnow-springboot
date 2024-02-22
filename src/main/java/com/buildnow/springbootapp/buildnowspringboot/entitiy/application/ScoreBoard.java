package com.buildnow.springbootapp.buildnowspringboot.entitiy.application;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class ScoreBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;
    private Long score;

    @ManyToOne
    private UpperCategoryScoreBoard upperCategoryScoreBoard;
}
