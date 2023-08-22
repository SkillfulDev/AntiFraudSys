package ua.chernonog.working.antifraud.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ua.chernonog.working.antifraud.mapper.UserEntityToUserDetails;
import ua.chernonog.working.antifraud.mapper.UserEntityToUserRes;
import ua.chernonog.working.antifraud.repository.UserRepository;

import java.io.IOException;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Slf4j
class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(hc -> hc.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .sessionManagement(c -> c.sessionCreationPolicy(STATELESS))
                .httpBasic(withDefaults());
//                .httpBasic(c -> {
//                });

        http.authorizeHttpRequests(c -> c
                        .requestMatchers(antMatcher("/api/auth/user")).permitAll()
//                .requestMatchers(antMatcher("actuator/**)")).permitAll()
                        .requestMatchers(antMatcher("/error")).permitAll()
                        .requestMatchers("api/**").authenticated()
                        .anyRequest().denyAll()
        );
//        http.exceptionHandling(c->c.accessDeniedHandler((request, response, authException) -> {
//            // Логируйте ошибку аутентификации
//            org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
//
//                    logger.error("Authentication error: {}", authException.getMessage(), authException);
//                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication error");
//                })
//                .accessDeniedHandler((request, response, accessDeniedException) -> {
//                    // Логируйте ошибку доступа
//                    org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
//                    logger.error("Access denied: {}", accessDeniedException.getMessage(), accessDeniedException);
//                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
//                }));
        http.exceptionHandling(c -> c
                        .authenticationEntryPoint(authenticationEntryPoint())
                        .accessDeniedHandler(accessDeniedHandler())
                )
                .formLogin(c -> c
                        .failureHandler(authenticationFailureHandler())
                );

        return http.build();
    }

    private AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
//            org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
            log.error("Authentication error: {}", authException.getMessage(), authException);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication error");
        };
    }

    private AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
//            org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
            log.error("Access denied: {}", accessDeniedException.getMessage(), accessDeniedException);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
        };
    }

    private AuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                                AuthenticationException exception) throws IOException, ServletException {
//                org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
                log.error("Authentication failure: {}", exception.getMessage(), exception);
                super.onAuthenticationFailure(request, response, exception);
            }
        };
    }
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public UserDetailsService userDetailsService(
                UserRepository userRepository) {

            return username -> userRepository
                    .findByUsernameIgnoreCase(username)
                    .map(user -> User.withUsername(user.getUsername())
                            .password(user.getPassword()).build())
                    .orElseThrow(() -> new UsernameNotFoundException("User " + username + "not found"));

        }

    }


