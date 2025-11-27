package com.TeacherApp.TeacherApp.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.TeacherApp.TeacherApp.Exceptions.CustomAccessDeniedHandler;
import com.TeacherApp.TeacherApp.Jwt.JwtAuthFilter;
import com.TeacherApp.TeacherApp.Services.MyUserDetailsService;

@EnableWebSecurity
@Configuration
public class SecurityConfig {


    @Autowired
    private JwtAuthFilter jwtFilter;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/api/v1/auth/**").permitAll()
        .requestMatchers("/api/v1/details/students").hasAnyRole("ADMIN","TEACHER")
        .requestMatchers("/api/v1/details/teacher").hasAnyRole("ADMIN","TEACHER")
        .requestMatchers("/api/v1/details/faculty").hasAnyRole("ADMIN","TEACHER")
        .requestMatchers("/api/v1/details/section").hasAnyRole("ADMIN","TEACHER")
        .anyRequest().authenticated());
        
        http.csrf(c -> c.disable());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling(ex -> ex.accessDeniedHandler(accessDeniedHandler));
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(myUserDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config){
        return config.getAuthenticationManager();
    }
    
}
