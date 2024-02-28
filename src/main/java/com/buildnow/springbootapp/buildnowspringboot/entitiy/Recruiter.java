package com.buildnow.springbootapp.buildnowspringboot.entitiy;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Recruitment;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruiter implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String businessId;
    private String managerName;
    private String role;
    private String companyName;
    private String refreshToken;
    @Column(nullable = true)
    private LocalDateTime lastJoinDateTime;

    @OneToMany(mappedBy = "recruiter", fetch = FetchType.LAZY)
    private List<Recruitment> recruitmentList;

    public Recruiter(String username, String password, String businessId, String managerName, String companyName) {
        this.username = username;
        this.password = password;
        this.businessId = businessId;
        this.managerName = managerName;
        this.companyName = companyName;
        this.role = "ROLE_RECRUITER";
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
