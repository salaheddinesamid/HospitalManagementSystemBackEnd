package com.hospitalmanagement.application.jwt;
import com.hospitalmanagement.application.service.AdminDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;



@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtilities ;
    private final AdminDetailsService adminDetailsService ;

    // This filters every request that is sent to the server
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        System.out.println(request.getLocalAddr());

        // Extract the token from the HTTP headers
        String token = jwtUtilities.getToken(request) ;
        System.out.println(request.getHeader("Authorization"));
        if (token!=null && jwtUtilities.validateToken(token)) {
            // Extract the username
            String email = jwtUtilities.extractUserName(token);
            // Load user details
            UserDetails userDetails = adminDetailsService.loadUserByUsername(email);
            if (userDetails != null) {
                UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails.
                            getUsername() ,null , userDetails.getAuthorities());
                log.info("authenticated user with email :{}", email);

                // Set the authentication context
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request,response);
    }
}