package com.buildnow.springbootapp.buildnowspringboot.dto;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Admin;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Recruiter;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
@Slf4j
public class CustomUserDetails implements UserDetails {
    private String username;
    private String password;
    @Getter
    private String recruiterCompanyName;
    @Getter
    private String recruiterCompanyLogo;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(Object user){
        if(user instanceof Recruiter) {
            Recruiter recruiter = (Recruiter) user;
            this.username = recruiter.getUsername();
            this.password = recruiter.getPassword();
            this.authorities = Collections.singletonList(new SimpleGrantedAuthority(recruiter.getRole()));

        }else if(user instanceof Applier) {
            Applier applier = (Applier) user;
            this.username = applier.getUsername();
            this.password = applier.getPassword();
            this.authorities = Collections.singletonList(new SimpleGrantedAuthority(applier.getRole()));
        }else if(user instanceof Admin){
            Admin admin = (Admin) user;
            this.username = admin.getUsername();
            this.password = admin.getPassword();
            this.authorities = Collections.singletonList(new SimpleGrantedAuthority(admin.getRole()));
        }
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        log.info(authorities.toString()
        );
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
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
