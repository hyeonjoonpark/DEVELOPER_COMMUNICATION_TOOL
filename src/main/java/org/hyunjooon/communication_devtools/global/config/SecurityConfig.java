package org.hyunjooon.communication_devtools.global.config;

import org.hyunjooon.communication_devtools.domain.auth.filter.JwtAuthorizationFilter;
import org.hyunjooon.communication_devtools.domain.auth.handler.CustomLogoutSuccessHandler;
import org.hyunjooon.communication_devtools.global.utils.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
@Slf4j
public class SecurityConfig {
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;
    private final JwtUtil jwtUtil;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(
                        formLogin -> formLogin
                                .defaultSuccessUrl("/")
                                .failureUrl("/login?error=true")
                )
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("http://localhost:3001", true)
                        .failureHandler((request, response, exception) -> {
                            log.error("OAuth2 로그인 실패: ", exception);
                            response.sendRedirect("http://localhost:3001/login?error=true");
                        })
                )
                .logout(
                        logout -> logout
                                .logoutSuccessHandler(customLogoutSuccessHandler)
                )
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/graphiql", "/graphql").permitAll() // graphql 관련 접근 허용
                                .requestMatchers("/login", "/login/oauth2/code/github").permitAll()
                                .requestMatchers("/favicon.ico").permitAll() // favicon.ico에 대한 접근 허용
                                .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                                .requestMatchers("/test").authenticated()
                                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                                .anyRequest().permitAll()
                )
                .sessionManagement(
                        session -> session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(new JwtAuthorizationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() throws Exception {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("USER")
                .build());
        return manager;
    }
}
