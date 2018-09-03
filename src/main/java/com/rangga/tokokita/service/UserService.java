package com.rangga.tokokita.service;


import com.rangga.tokokita.config.security.JwtTokenProvider;
import com.rangga.tokokita.exception.UnprocessableEntityException;
import com.rangga.tokokita.model.User;
import com.rangga.tokokita.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private AuthenticationManager authenticationManager;

  public String signin(String username, String password) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
      return jwtTokenProvider.createToken(username);
    } catch (AuthenticationException e) {
      throw new UnprocessableEntityException("username/password tidak sesusai");
    }
  }

  public User signup(User user) {
    if (userRepository.findByUsername(user.getUsername()) == null) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      user.setCreated_at(new Date());
      return userRepository.save(user);
    } else {
      throw new UnprocessableEntityException("Username Telah digunakan");
    }
  }

  public User save(User user){
    return userRepository.save(user);
  }

  public User getCurrentUser(HttpServletRequest req) {
   return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
  }



}
