package ua.chernonog.working.antifraud.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

//@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //Веключаем способ передачи логина/пароля
//                .httpBasic(Customizer.withDefaults())
//
//                .headers(f->f.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
//                .csrf(d->d.ignoringRequestMatchers("/h2/**").disable())
//
////
//                .headers(c -> c.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
//               .securityMatcher("/h2/**")
//                .authorizeHttpRequests(c -> c.requestMatchers(
//                                "/api/auth/user").permitAll().anyRequest().permitAll())
//                .sessionManagement(session -> session
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//
//                .build();
        http.authorizeHttpRequests(c -> c.requestMatchers(toH2Console())
                        .permitAll())
                .csrf(c -> c.ignoringRequestMatchers(toH2Console()));


        return http.authorizeHttpRequests(c -> c.requestMatchers(antMatcher(
                                "/api/auth/user")).permitAll()
                        .anyRequest().permitAll())
                .build();

    }
}
