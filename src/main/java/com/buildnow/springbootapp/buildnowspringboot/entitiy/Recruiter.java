package com.buildnow.springbootapp.buildnowspringboot.entitiy;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Recruitment;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String recruiterName;
    private String password;
    private String businessId;
    private String managerName;

    private String companyName;
    private String refreshToken;
    @Column(nullable = true)
    private LocalDateTime lastJoinDateTime;

    @OneToMany(mappedBy = "recruiter", fetch = FetchType.LAZY)
    private List<Recruitment> recruitmentList;

    public Recruiter(String recruiterName, String password, String businessId, String managerName, String companyName) {
        this.recruiterName = recruiterName;
        this.password = password;
        this.businessId = businessId;
        this.managerName = managerName;
        this.companyName = companyName;
    }



}
