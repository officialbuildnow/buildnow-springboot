package com.buildnow.springbootapp.buildnowspringboot.jwt;

import com.buildnow.springbootapp.buildnowspringboot.dto.CustomUserDetails;
import com.buildnow.springbootapp.buildnowspringboot.dto.recruiter.RecruiterLoginDTO;
import com.buildnow.springbootapp.buildnowspringboot.dto.recruiter.RecruitmentInfoDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Recruiter;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Recruitment;
import com.buildnow.springbootapp.buildnowspringboot.repository.RecruiterRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.RecruitmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final RecruiterRepository recruiterRepository;
    private final RecruitmentRepository recruitmentRepository;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authToken);
    }

    @Override
    @Transactional
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
        log.info("성공적!");
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String username = customUserDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String role = auth.getAuthority();

        String token = jwtUtil.createJwt(username, role, 600000L);
        response.addHeader("Authorization", "Bearer " + token);

        //리크루터로 로그인 한 경우 커스텀된 정보 반환
        if(role.equals("ROLE_RECRUITER")){
            String companyLogoURL = customUserDetails.getRecruiterCompanyLogo();
            String companyName = customUserDetails.getRecruiterCompanyName();
            Long recruitmentId = customUserDetails.getRecruitmentId();
            RecruiterLoginDTO recruiterLoginDTO = new RecruiterLoginDTO();
            recruiterLoginDTO.setRecruiterName(companyName);
            recruiterLoginDTO.setRecruiterLogo(companyLogoURL);
            recruiterLoginDTO.setRecruiterId(recruitmentId);

            Recruiter recruiter = recruiterRepository.findByUsername(username);
            List<Recruitment> recruitmentList = recruitmentRepository.findByRecruiter(recruiter);
            recruiterLoginDTO.setRecruitmentInfoDTOList(new ArrayList<>());
            if(!recruitmentList.isEmpty()){
                for(Recruitment recruitment : recruitmentList){
                    RecruitmentInfoDTO recruitmentInfoDTO = new RecruitmentInfoDTO();
                    recruitmentInfoDTO.setRecruitmentId(recruitment.getId());
                    recruitmentInfoDTO.setRecruitmentTitle(recruitment.getRecruitmentTitle());
                    recruitmentInfoDTO.setThreshold(recruitment.getThreshold());
                    recruitmentInfoDTO.setDeadLine(recruitment.getDeadline());
                    Long applicationNum = (long) recruitment.getApplicationList().size();
                    recruitmentInfoDTO.setApplicationNum(applicationNum);
                    recruiterLoginDTO.getRecruitmentInfoDTOList().add(recruitmentInfoDTO);
                }
            }
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            String recruiterLoginDTOtoJson = mapper.writeValueAsString(recruiterLoginDTO);

            response.getWriter().write(recruiterLoginDTOtoJson);
        }
        else{
            response.getWriter().write(role);
        }
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        log.error("Authentication failed: {}", failed.getMessage());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"error\": \"Authentication failed: " + failed.getMessage() + "\"}");
    }

}
