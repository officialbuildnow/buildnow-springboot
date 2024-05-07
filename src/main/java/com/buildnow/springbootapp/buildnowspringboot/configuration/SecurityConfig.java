package com.buildnow.springbootapp.buildnowspringboot.configuration;

import com.buildnow.springbootapp.buildnowspringboot.jwt.JWTFilter;
import com.buildnow.springbootapp.buildnowspringboot.jwt.JWTUtil;
import com.buildnow.springbootapp.buildnowspringboot.jwt.LoginFilter;
import com.buildnow.springbootapp.buildnowspringboot.repository.RecruiterRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.RecruitmentRepository;
import com.buildnow.springbootapp.buildnowspringboot.service.CustomUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final RecruitmentRepository recruitmentRepository;
    private final RecruiterRepository recruiterRepository;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable);
        http
                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        List<String> allowedOrigins = Arrays.asList("http://localhost:3000", "https://buildnow-v1.vercel.app");
                        config.setAllowedOrigins(allowedOrigins);
                        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
                        config.setExposedHeaders(Collections.singletonList("Authorization"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }));
        http
                .formLogin(AbstractHttpConfigurer::disable);
        http
                .httpBasic(AbstractHttpConfigurer::disable);
        http
                .authorizeHttpRequests((auth)->auth
                        .requestMatchers("/login", "/", "/recruiter", "/applier/join", "/api/process", "/codef").permitAll()
                        .requestMatchers("/tempOCR/admin/**",
                                "application/admin/**",
                                "tempHanded/**",
                                "/grading/admin/**",
                                "/recruiting-work-type/admin/**",
                                "/application-evaluation/admin/**",
                                "/temp-prerequisite/admin/**",
                                "/tempsave/admin/**",
                                "/capacity-value/admin/**",
                                "/capacity-value/admin&recruiter/**",
                                "finance/admin/**",
                                "/license/admin/**",
                                "/applier/admin/**",
                                "/extra-value/admin/**",
                                "/recruitment/admin/**",
                                "/recruiter/admin/**"
                                ).hasRole("ADMIN")
                        .requestMatchers("/recruiter/data",
                                "/recruitment",
                                "/applier/recruiter/**",
                                "/application-evaluation/recruiter/**",
                                "/capacity-value/admin&recruiter/**",
                                "/application/recruiter/**",
                                "/recruitment/recruiter/**",
                                "/grading/recruiter/**"
                        ).hasRole("RECRUITER")
                        .requestMatchers("/applier/**", "/tempsave/applier/**", "/application/applier/**", "/tempOCR/applier/**", "/recruitment/applier/**").hasRole("APPLIER")
                        .anyRequest().authenticated());

        http
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil, recruiterRepository, recruitmentRepository), UsernamePasswordAuthenticationFilter.class);
        http
                .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);
        http
                .sessionManagement((session)->session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();

    }

}
