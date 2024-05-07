package com.buildnow.springbootapp.buildnowspringboot.entitiy;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Recruitment;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String businessId;
    private String managerName;
    private String role;
    private String companyName;
    @Setter
    private String companyLogo;
    private String refreshToken;
    private LocalDateTime lastJoinDateTime;

    @OneToMany(mappedBy = "recruiter",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "recruiter-recruitment")
    private List<Recruitment> recruitmentList;

    @Builder
    public Recruiter(String username, String password, String businessId, String managerName, String companyName, String companyLogo) {
        this.username = username;
        this.password = password;
        this.businessId = businessId;
        this.managerName = managerName;
        this.companyName = companyName;
        this.companyLogo = companyLogo;
        this.role = "ROLE_RECRUITER";
        this.recruitmentList = new ArrayList<>();
    }

    public void addRecruitment(Recruitment recruitment){
        recruitmentList.add(recruitment);
        recruitment.setRecruiter(this);
    }
    public void removeRecruitment(Recruitment recruitment){
        recruitmentList.remove(recruitment);
        recruitment.setRecruiter(null);
    }

}
