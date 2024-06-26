package com.ysuratask.configs;

import com.ysuratask.utils.AuthenticationUtil;
import com.ysuratask.utils.UrlUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Created by NrapendraKumar on 25-03-2016.
 */

@Configuration
public class SecurityConfig  {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(UrlUtil.GARAGE_ENTER_VEHICLE_URL).permitAll()
                                .requestMatchers(UrlUtil.GARAGE_EXIT_VEHICLE_ALL_URL).permitAll()
                                .requestMatchers(UrlUtil.GARAGE_GET_VEHICLE_LOCATION_ALL_URL).hasRole(AuthenticationUtil.ROLE_MANAGER)
                                .requestMatchers(UrlUtil.GARAGE_FREE_SPACE_URL).hasRole(AuthenticationUtil.ROLE_MANAGER)
                )
                .httpBasic(withDefaults())
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var user = User.withUsername(AuthenticationUtil.USERNAME)
                .password("{noop}" + AuthenticationUtil.PASSWORD) // NoOpPasswordEncoder for demo purposes
                .roles(AuthenticationUtil.ROLE_MANAGER)
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}


