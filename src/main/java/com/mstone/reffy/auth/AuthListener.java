package com.mstone.reffy.auth;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthListener {
  @EventListener
  public void onLogin(AuthenticationSuccessEvent event) {
    var token = (UsernamePasswordAuthenticationToken) event.getSource();
    var user = (UserDetails) token.getPrincipal();
    log.info("login successful: {}", user.getUsername());
  }

  @EventListener
  public void onLogout(LogoutSuccessEvent event) {
    var token = (UsernamePasswordAuthenticationToken) event.getSource();
    var user = (UserDetails) token.getPrincipal();
    log.info("logout successful: {}", user.getUsername());
  }
}