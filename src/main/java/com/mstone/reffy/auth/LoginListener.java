package com.mstone.reffy.auth;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoginListener implements ApplicationListener<AuthenticationSuccessEvent> {
  @Override
  public void onApplicationEvent(AuthenticationSuccessEvent event) {
    var token = (UsernamePasswordAuthenticationToken) event.getSource();
    var user = (UserDetails) token.getPrincipal();
    log.info("login successful: {}", user.getUsername());
  }
}