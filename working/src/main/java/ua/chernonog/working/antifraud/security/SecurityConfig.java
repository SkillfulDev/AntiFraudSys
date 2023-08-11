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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

//@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());
        http.sessionManagement(c -> c.sessionCreationPolicy(STATELESS));
//        http.authorizeHttpRequests(c -> c.requestMatchers(toH2Console())
//                .permitAll()
//              http.authorizeHttpRequests(w->w.requestMatchers
//                              (new AntPathRequestMatcher( "/h2/")).permitAll().requestMatchers(new AntPathRequestMatcher("/api/auth/user")).permitAll()
//                .anyRequest().permitAll());
        http.authorizeHttpRequests(req -> req
                        .requestMatchers(antMatcher("/api/auth/user")).permitAll()
                        .requestMatchers(antMatcher(toH2Console().toString())).permitAll());
//                .permitAll();


        http.csrf(c -> c.ignoringRequestMatchers(toH2Console()));
        http.headers(headers -> headers.frameOptions(z -> z.sameOrigin()));
        return http.build();

    }
}
