package org.sid.apro.sec;


import jakarta.servlet.Filter;
import org.sid.apro.service.IAproIniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity // Enables @PreAuthorize and other method-level security annotations
public class SecurityConfig {
    @Autowired
    private  IAproIniService iAproIniService;
    @Autowired
    private  BCryptPasswordEncoder bCryptPasswordEncoder;

    private  JWTAuthenticationFilter jwtAuthenticationFilter;

    private  JWTAuthorizationFilter jwtAuthorizationFilter;
    /*
    public SecurityConfig(JWTAuthenticationFilter jwtAuthenticationFilter, JWTAuthorizationFilter jwtAuthorizationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/**/**").permitAll()
                        .requestMatchers("/login/**", "/inscrire/**", "/forgot_password/**", "/resetPassword/**").permitAll()
                        .requestMatchers("/Utilisateur/**", "/etudiant/**").hasAuthority("Admin")
                        .requestMatchers("/**/**", "/**/**").hasAuthority("Patient")
                        .anyRequest().authenticated()
                );

        // Add custom filters
        http.addFilter(jwtAuthenticationFilter);
        http.addFilterBefore((Filter) jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    
}
