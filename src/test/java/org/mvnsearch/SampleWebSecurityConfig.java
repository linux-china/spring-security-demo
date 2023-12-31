package org.mvnsearch;

import org.mvnsearch.service.HelloMessageService;
import org.mvnsearch.service.impl.HelloMessageServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * sample web security config
 *
 * @author linux_china
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity()
public class SampleWebSecurityConfig {
  @Bean
  public HelloMessageService messageService() {
    return new HelloMessageServiceImpl();
  }


  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    UserDetails user = User.withDefaultPasswordEncoder()
      .username("admin")
      .password("password")
      .roles("ADMIN")
      .username("jacky")
      .password("password")
      .roles("USER")
      .build();
    return new InMemoryUserDetailsManager(user);
  }
}
