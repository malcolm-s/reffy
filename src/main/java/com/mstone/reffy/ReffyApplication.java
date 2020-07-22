package com.mstone.reffy;

import com.mstone.reffy.auth.ReffyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class ReffyApplication {
  public static void main(String[] args) {
    SpringApplication.run(ReffyApplication.class, args);
  }

  @Bean
  SpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
    var templateEngine = new SpringTemplateEngine();
    templateEngine.addTemplateResolver(templateResolver);
    templateEngine.addDialect(new LayoutDialect());
    return templateEngine;
  }
}

@Configuration
@EnableWebMvc
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private ReffyUserDetailsService reffyUserDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests(auth -> auth.antMatchers("/referendums/{id}/vote").authenticated().anyRequest().permitAll());
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return reffyUserDetailsService;
  }
}