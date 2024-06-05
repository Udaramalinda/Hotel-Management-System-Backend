package com.suntravels.backend.SunTravelsBackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    private final String admin = "ADMIN";
    private final String user = "USER";

    public SecurityConfiguration( JwtAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider ){
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf( AbstractHttpConfigurer::disable )
                .authorizeHttpRequests(req ->
                                              req.requestMatchers( "/agent/login" ).permitAll()
                                                      .requestMatchers( "/agent/register", "/agent/details" ).hasAnyAuthority( admin )
                                                      .requestMatchers( "/contract/register" ).hasAnyAuthority( admin )
                                                      .requestMatchers( "/contract/details" ).hasAnyAuthority( admin, user )
                                                      .requestMatchers( "/hotel/register" ).hasAnyAuthority( admin )
                                                      .requestMatchers( "/hotel/names", "/hotel/details" ).hasAnyAuthority( admin, user )
                                                      .requestMatchers( "/markup/register" ).hasAnyAuthority( admin )
                                                      .requestMatchers( "/markup/value" ).hasAnyAuthority( admin, user )
                                                      .requestMatchers( "/roomtype/register" ).hasAnyAuthority( admin )
                                                      .requestMatchers( "/roomtype/names" ).hasAnyAuthority( admin, user )
                                                      .requestMatchers( "/search" ).hasAnyAuthority( admin,user ))
//                .authorizeHttpRequests(req ->
//                                              req.requestMatchers( "/agent/**", "/search/**", "/contract/**", "/hotel/**", "/markup/**", "/roomtype/**" )
//                                                      .permitAll()
//                )
                .sessionManagement(sessionManagement -> sessionManagement
                                                               .sessionCreationPolicy( SessionCreationPolicy.STATELESS ))
                .authenticationProvider( authenticationProvider )
                .addFilterBefore( jwtAuthFilter, UsernamePasswordAuthenticationFilter.class );

        return http.build();
    }
}