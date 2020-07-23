package com.mstone.reffy;

import com.mstone.reffy.auth.ReffyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private ReffyUserDetailsService reffyUserDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests(auth -> auth.antMatchers("/referendums/{id}/vote").authenticated().anyRequest().permitAll())
        .formLogin(formLogin -> formLogin.loginPage("/login").usernameParameter("email"))
        .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?loggedOut"))
        .csrf(csrf -> csrf.disable());
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(reffyUserDetailsService);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}