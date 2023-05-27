package com.yuewang.rbac.config;

import com.yuewang.rbac.security.AuthFilter;
import com.yuewang.rbac.security.LoginFilter;
import com.yuewang.rbac.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @ClassName SpringSecurityConfig
 * @Description configuration for Spring Security:
 * HttpSecurity/AuthenticationProvider/PasswordEncoder/AuthenticationManager/SecurityFilterChain
 * @Author Yue Wang
 * @Date 2023/5/14 12:03
 **/
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Autowired
    private UserServiceImpl userDetailsService;
    @Autowired
    private LoginFilter loginFilter;

    @Autowired
    private AuthFilter authFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Disable csrf and frameOptions, or will affect frontend API requests.
        http.csrf().disable();
        http.headers().frameOptions().disable();
        // Enable cross-origin resource sharing (CORS) to facilitate frontend calls to the API.
        http.cors().configurationSource(corsConfigurationSource());
        // This is a key configuration that determines which interfaces are protected and which interfaces bypass protection.
        http.authorizeRequests()
                // This is an essential configuration that allows cross-domain debugging for frontend developers.
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                // Specifies that certain endpoints can be accessed without authentication.
                .antMatchers(
                        "/auth/login",
                        "/auth/code/**",
                        "/auth/register",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/images/**",
                        "/webjars/**",
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/configuration/security"
                )
                .permitAll()
                // All other endpoints require authentication to access.
                .antMatchers("/**").authenticated()
                // Configure authentication error handler.
                .and().exceptionHandling().authenticationEntryPoint(new MyEntryPoint()).accessDeniedHandler(new MyDeniedHandler());
        //Disable session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // Replace the default authentication filter with custom ones.
        http.addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(authFilter, FilterSecurityInterceptor.class);

        return http.build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
