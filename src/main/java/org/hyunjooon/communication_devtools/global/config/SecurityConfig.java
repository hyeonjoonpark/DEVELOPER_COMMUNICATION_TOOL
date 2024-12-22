package org.hyunjooon.communication_devtools.global.config;

import lombok.RequiredArgsConstructor;
import org.hyunjooon.communication_devtools.domain.auth.filter.JwtAuthorizationFilter;
import org.hyunjooon.communication_devtools.domain.auth.handler.CustomLogoutSuccessHandler;
import org.hyunjooon.communication_devtools.global.utils.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;
    private final JwtUtil jwtUtil;

    private static final String ROLE_USER = "USER";
    private static final String ROLE_ADMIN = "ADMIN";

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
//                .oauth2Login(oauth -> oauth.successHandler((request, response, authentication) -> {
//                    response.sendRedirect("http://localhost:3001"); // 프론트엔드 경로로 리다이랙트
//                }))
                .logout(
                        logout -> logout
                                .logoutSuccessHandler(customLogoutSuccessHandler)
                )
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/graphiql", "/graphql").permitAll() // graphql 관련 접근 허용
                                .requestMatchers("/favicon.ico").permitAll() // favicon.ico에 대한 접근 허용
                                .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                                .requestMatchers("/test").authenticated()
                                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                                .requestMatchers("/graphql?query=testQuery").authenticated() // GraphQL 특정 API 인증필요
                                .anyRequest().authenticated()
                )
                .sessionManagement(
                        session -> session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(new JwtAuthorizationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
