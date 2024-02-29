package com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class PossibleWorkType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String workType;
    @ManyToOne
    private Applier applier;
    @OneToMany(mappedBy = "possibleWorkType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CapacityValue> capacityValueList;
}
