package com.example.agencia_de_viagem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityWeb {

    @Autowired
    private UserDetailsServiceCustom userDetailsServiceCustom;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors->cors.disable())
                .csrf(csrf -> csrf.ignoringRequestMatchers("**"))
                .authorizeHttpRequests((requests)->requests
                .requestMatchers("/api/v1/").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/users").hasRole("USER")
                .anyRequest().authenticated()
        )
                .sessionManagement(session ->
                        session.maximumSessions(1)
                                .expiredUrl("/login?expired")
                                .maxSessionsPreventsLogin(true)
                )
                .userDetailsService(userDetailsServiceCustom)
                .httpBasic(Customizer.withDefaults())
                .formLogin((form)->form.permitAll())
                .logout((logout)->logout.permitAll());
        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails userDetails = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(userDetails);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
