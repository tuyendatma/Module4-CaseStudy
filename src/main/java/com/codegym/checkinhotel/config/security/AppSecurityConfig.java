package com.codegym.checkinhotel.config.security;

import com.codegym.checkinhotel.config.CustomSuccessHandler;
import com.codegym.checkinhotel.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomSuccessHandler customSuccessHandler;

    @Autowired
    private IAppUserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService((UserDetailsService) userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/home","/hotels","/rooms","/cities").permitAll()
//                .and().authorizeRequests()
//                .antMatchers("/home/**","/bookings/**","/hotels/**,/rooms/**","/cities/**","/hoteldetails/**","/roomdetails/**").access("hasRole('ADMIN')")  //hasRole("ADMIN")
//                .antMatchers("/home/**","/bookings/**","/hotels/**,/rooms/**,/cities/**").access("hasRole('USER')")  //hasRole("USER")
//                .and()
////                .formLogin()
//                .formLogin().loginPage("/login").successHandler(customSuccessHandler)
//                .usernameParameter("username").passwordParameter("password").permitAll()
////                .failureUrl("/fail-login")
//                .and()
//                .logout().logoutSuccessUrl("/home").permitAll()
////                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .and().exceptionHandling().accessDeniedPage("/access-denied");
//        http.csrf().disable();
    }
}
