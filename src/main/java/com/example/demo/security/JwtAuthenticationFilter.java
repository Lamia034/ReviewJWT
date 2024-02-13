package com.example.demo.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

        private final JwtUtilities jwtService;
    private final CustomerUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        final String token;
        final String userEmail;
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        token = header.replace("Bearer ", "");
        jwtService.setToken(token);
        userEmail = jwtService.extractUsername(token);
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(userEmail);
            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

}
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
////@Autowired
////    private JwtUtilities jwtUtilities;
////@Autowired
////    private CustomerUserDetailsService customerUserDetailsService ;
//private final JwtUtilities jwtUtilities;
//    private final CustomerUserDetailsService customerUserDetailsService;
//
////    @Autowired
////    public JwtAuthenticationFilter(JwtUtilities jwtUtilities, CustomerUserDetailsService customerUserDetailsService) {
////        this.jwtUtilities = jwtUtilities;
////        this.customerUserDetailsService = customerUserDetailsService;
////    }
//    @Override
//    protected void doFilterInternal(@NonNull HttpServletRequest request,
//                                    @NonNull HttpServletResponse response,
//                                    @NonNull FilterChain filterChain)
//            throws ServletException, IOException {
//
//        String token = jwtUtilities.getToken(request) ;
//
//        if (token!=null && jwtUtilities.validateToken(token))
//        {
//            String name = jwtUtilities.extractUsername(token);
//
//            UserDetails userDetails = customerUserDetailsService.loadUserByUsername(name);
//            if (userDetails != null) {
//                UsernamePasswordAuthenticationToken authentication =
//                        new UsernamePasswordAuthenticationToken(userDetails.getUsername() ,null , userDetails.getAuthorities());
//                log.info("authenticated user with name :{}", name);
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            }
//        }
//        filterChain.doFilter(request,response);
//    }
//
//}