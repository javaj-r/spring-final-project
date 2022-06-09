package com.javid.sfp.config;

import com.javid.sfp.security.Role;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author javid
 * Created on 5/17/2022
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.authorizeRequests()

                .antMatchers("/welcome").authenticated()
                .antMatchers("/admin", "/swagger", "/swagger-ui/**", "/javainuse-openapi/**").hasAuthority(Role.ADMIN)
                .antMatchers("/expert").hasAnyAuthority(Role.ADMIN, Role.EXPERT)
                .antMatchers("/customer").hasAnyAuthority(Role.ADMIN, Role.CUSTOMER)
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .defaultSuccessUrl("/welcome", true)

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

                .and()
                .exceptionHandling()
                .accessDeniedPage("/accessDenied");
    }


    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().antMatchers(HttpMethod.GET,
                "/", "/index", "/home"
                , "/webjars/**", "/js/**", "/css/**", "/fonts/**"
                , "/map/**", "/workgroup/**"
                , "/customer/register", "/expert/register", "/confirm/**");

        web.ignoring().antMatchers(HttpMethod.GET,
                "/api/v1/workgroup/**"
                , "/api/v1/work/**"
                , "/api/v1/{\\d+}/work");

        web.ignoring().antMatchers(HttpMethod.POST, "/api/v1/expert", "/api/v1/customer");
    }
}
