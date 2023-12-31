package org.mvnsearch.config;

import org.mvnsearch.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.csrf.CsrfTokenRepository;

/**
 * web security config
 *
 * @author linux_china
 */
@EnableWebSecurity
@Configuration
class WebSecurityConfig {

  private String rememberMeAppKey = "yourAppKey";

  private String[] whiteUrls = new String[]{"/jsondoc*"};


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf((csrf) -> {
        csrf.csrfTokenRepository(tokenRepository());
      })
      .addFilterBefore(new AuthorizationHeaderFilter(), RememberMeAuthenticationFilter.class)
      .sessionManagement((sessionManagement) -> {
          sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }
      )
      .authorizeHttpRequests((authorize) -> {
          authorize.requestMatchers("/home").authenticated();
          authorize.anyRequest().authenticated();
        }
      )
      .httpBasic(httpSecurityHttpBasicConfigurer -> {
        httpSecurityHttpBasicConfigurer.disable();
      })
      .formLogin(httpSecurityFormLoginConfigurer -> {
        httpSecurityFormLoginConfigurer.loginPage("/login")
          .failureUrl("/login?error")
          .usernameParameter("email")
          .defaultSuccessUrl("/home")
          .permitAll();

      })
      .logout(httpSecurityLogoutConfigurer -> {
        httpSecurityLogoutConfigurer.logoutUrl("/logout")
          .logoutSuccessUrl("/")
          .deleteCookies("remember-me")
          .permitAll();
      })
      .rememberMe(httpSecurityRememberMeConfigurer -> {
        httpSecurityRememberMeConfigurer.key(rememberMeAppKey);
      })
      .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> {
        httpSecurityExceptionHandlingConfigurer.accessDeniedPage("/403");
      })
      .build();
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


  @Bean
  UserDetailsService customUserDetailsService() {
    return new UserDetailsServiceImpl();
  }

  @Bean
  RememberMeServices rememberMeServices(UserDetailsService userDetailsService) {
    TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices(rememberMeAppKey, userDetailsService);
    rememberMeServices.setAlwaysRemember(true);
    return rememberMeServices;
  }

  @Bean
  public CsrfTokenRepository tokenRepository() {
    return new CacheCsrfTokenRepository();
  }

}
