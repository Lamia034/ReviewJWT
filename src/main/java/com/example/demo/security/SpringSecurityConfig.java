package com.example.demo.security;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {
//@Autowired
private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomerUserDetailsService customerUserDetailsService ;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement ->
                        sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests((authorize) -> {
                    authorize
                            .requestMatchers("/", "/css/**", "/js/**", "/images/**").permitAll()
                            .requestMatchers("/home/**","/register").permitAll()
                            .requestMatchers("/review/**").hasAuthority("ROLE_USER")
                            .requestMatchers("/reviews/**").hasAuthority("ROLE_ADMIN")
                            .anyRequest().authenticated();
                });
//                            .formLogin(withDefaults())
//                            .httpBasic(withDefaults());

                http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return  http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
    { return authenticationConfiguration.getAuthenticationManager();}

    @Bean
    public PasswordEncoder passwordEncoder()
    { return new BCryptPasswordEncoder(); }


}
//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtUtilities jwtUtilities, CustomerUserDetailsService customerUserDetailsService) {
//        return new JwtAuthenticationFilter();
//    }
//@Bean
//public JwtAuthenticationFilter jwtAuthenticationFilter(JwtUtilities jwtUtilities, CustomerUserDetailsService customerUserDetailsService) {
//    return new JwtAuthenticationFilter(jwtUtilities, customerUserDetailsService);
//}