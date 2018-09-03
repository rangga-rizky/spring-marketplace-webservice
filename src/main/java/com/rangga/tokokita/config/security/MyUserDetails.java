package com.rangga.tokokita.config.security;

import com.rangga.tokokita.model.User;
import com.rangga.tokokita.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetails implements UserDetailsService {
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final User user = userRepository.findByUsername(username);
   if (user == null) {
      throw new UsernameNotFoundException("User '" + username + "' not found");
    }


    return org.springframework.security.core.userdetails.User//
        .withUsername(username)//
        .password(user.getPassword())
        .authorities("CLIENT")//
        .accountExpired(false)//
        .accountLocked(false)//
        .credentialsExpired(false)//
        .disabled(false)//
        .build();
  }

}
