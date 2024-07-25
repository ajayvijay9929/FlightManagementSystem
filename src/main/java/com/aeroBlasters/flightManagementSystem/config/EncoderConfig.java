package com.aeroBlasters.flightManagementSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class to define beans for password encoding.
 * <p>
 * This class provides a BCryptPasswordEncoder bean, which is used
 * to encode passwords before they are stored or compared in the
 * authentication process.
 * </p>
 */
@Configuration
public class EncoderConfig {

  /**
   * Bean definition for BCryptPasswordEncoder.
   * <p>
   * BCryptPasswordEncoder is a password encoder that uses the BCrypt
   * hashing function to encode passwords, providing strong security
   * for password storage.
   * </p>
   * 
   * @return a BCryptPasswordEncoder instance.
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
