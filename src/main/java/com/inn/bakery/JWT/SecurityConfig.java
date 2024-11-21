package com.inn.bakery.JWT;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                        auth -> auth.requestMatchers("/signin", "/signup").permitAll()
                                .requestMatchers("/users/**", "/apps/**").hasAuthority("ADMIN")
                                .requestMatchers("/myapps/**").hasAuthority("CLIENT")
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/signin")
                        .usernameParameter("email")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .rememberMe(withDefaults())
                .logout(logout -> logout.logoutUrl("/signout").permitAll());


        return http.build();
    }
}
