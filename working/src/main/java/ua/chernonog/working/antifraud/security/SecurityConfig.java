package ua.chernonog.working.antifraud.security;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ua.chernonog.working.antifraud.mapper.UserEntityToUserDetails;
import ua.chernonog.working.antifraud.mapper.UserEntityToUserRes;
import ua.chernonog.working.antifraud.repository.UserRepository;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

//@EnableWebSecurity
@EnableMethodSecurity
@Configuration
class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(c -> c
                .requestMatchers("/api/auth/**").permitAll()
//                .requestMatchers(POST, "/register").permitAll()
                .requestMatchers("/error").permitAll()
                .anyRequest().authenticated());
        http.sessionManagement(c -> c.sessionCreationPolicy(STATELESS));
        http.httpBasic(withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
//        http.csrf(c -> c
//                .ignoringRequestMatchers("/register")
//                .ignoringRequestMatchers(antMatcher("/h2/**")));
        http.cors(AbstractHttpConfigurer::disable);
        http.headers(c -> c
                .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {

        return username -> userRepository
                .findByUsernameIgnoreCase(username)
                .map(user-> User.builder().username(user.getUsername())
                        .password(user.getPassword()).build())
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + "not found"));

    }

}
