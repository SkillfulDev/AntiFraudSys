package ua.chernonog.working.antifraud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
//import ua.chernonog.working.antifraud.mapper.UserEntityToUserDetails;
import org.springframework.web.cors.CorsConfiguration;
import ua.chernonog.working.antifraud.model.security.UserDetailsImpl;
import ua.chernonog.working.antifraud.repository.UserRepository;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;


@EnableWebSecurity
@EnableMethodSecurity
@Configuration
class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(hc -> hc.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .sessionManagement(c -> c.sessionCreationPolicy(STATELESS))
                .httpBasic(withDefaults());
        http.authorizeHttpRequests(c -> c
//                .requestMatchers(antMatcher("/api/auth/user")).permitAll()
//                .requestMatchers(antMatcher("/actuator/health)")).permitAll()
//                .requestMatchers(antMatcher("/error")).permitAll()
//                .requestMatchers(antMatcher("/swagger-ui")).permitAll()
//                .requestMatchers(antMatcher("/v3/api-docs")).permitAll()
//                .requestMatchers(antMatcher("api/**")).authenticated()

                .anyRequest().permitAll()
//        )
//                .formLogin(formLogin -> formLogin
//                        .loginPage("/8.8.8.8")
//                        .permitAll()

        );
        http.cors(r->new CorsConfiguration().applyPermitDefaultValues());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } //TODO


    @Bean
    public UserDetailsService userDetailsService(
            UserRepository userRepository) {

        return username -> userRepository
                .findByUsernameIgnoreCase(username)
                .map(user -> new UserDetailsImpl(user.getUsername(),user.getPassword()))
//                        User.withUsername(user.getUsername())
//                        .authorities("ROLE_USER")
//                        .password(user.getPassword()).build())
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + "not found"));

    }
}


