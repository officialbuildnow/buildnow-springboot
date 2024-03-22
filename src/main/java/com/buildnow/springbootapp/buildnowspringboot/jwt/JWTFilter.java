package com.buildnow.springbootapp.buildnowspringboot.jwt;

import com.buildnow.springbootapp.buildnowspringboot.dto.CustomUserDetails;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Recruiter;
import com.buildnow.springbootapp.buildnowspringboot.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Slf4j
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if(authorization == null || !authorization.startsWith("Bearer ")){
            log.error("token null");
            filterChain.doFilter(request, response);
            return;
        }

        log.info("authorization 시작");

        String token = authorization.split(" ")[1];

        if(jwtUtil.isExpired(token)){
            log.error("토큰 만료");
            filterChain.doFilter(request, response);
            return;

        }

        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);
        log.info(role);

        if(role.equals("ROLE_RECRUITER")){
            log.info("recruiter 로직 시작");
            Recruiter recruiter = new Recruiter(
                    username, "12344", "123", "123", "sdf"
            );

            CustomUserDetails customUserDetails = new CustomUserDetails(recruiter);

            Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authToken);
            log.info("recruiter 로직 끝");
        }else if(role.equals("ROLE_APPLIER")){
            log.info("applier 로직 시작");
            Applier applier = new Applier(
                    "123",
                    "123",
                    "123",
                    "123",
                    username,
                    "123"
            );

            CustomUserDetails customUserDetails = new CustomUserDetails(applier);

            Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authToken);
            log.info("applier 로직 끝");
        }

        filterChain.doFilter(request, response);
    }

}
