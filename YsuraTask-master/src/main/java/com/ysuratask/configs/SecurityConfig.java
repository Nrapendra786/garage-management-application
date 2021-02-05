package com.ysuratask.configs;
import com.ysuratask.utils.AuthenticationUtil;
import com.ysuratask.utils.UrlUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by NrapendraKumar on 25-03-2016.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                http.authorizeRequests().
                        antMatchers(UrlUtil.GARAGE_ENTER_VEHICLE_URL).permitAll().
                        antMatchers(UrlUtil.GARAGE_EXIT_VEHICLE_ALL_URL).permitAll().
                        antMatchers(UrlUtil.GARAGE_GET_VEHICLE_LOCATION_ALL_URL).hasRole(AuthenticationUtil.ROLE_MANAGER).
                        antMatchers(UrlUtil.GARAGE_FREE_SPACE_URL).hasRole(AuthenticationUtil.ROLE_MANAGER).
                        and().httpBasic().and().csrf().disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser(AuthenticationUtil.USERNAME).
                password(AuthenticationUtil.PASSWORD).
                roles(AuthenticationUtil.ROLE_MANAGER);
    }
}


