package com.mstone.reffy.auth;

import com.mstone.reffy.user.UserRepository;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ReffyUserDetailsService implements UserDetailsService {
  private final UserRepository userRepository;

  public ReffyUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info("loadUserByUsername: {}", username);
    return userRepository
      .findByEmail(username)
      .map(u -> new User(u.getEmail(), u.getPassword(), getAuthorities()))
      .orElseThrow(() -> new UsernameNotFoundException("No user found with email: " + username));
  }

  private List<GrantedAuthority> getAuthorities() {
    return Arrays.asList(new SimpleGrantedAuthority("USER"));
  }
}
