package org.mvnsearch.config;

import org.mvnsearch.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@Order(SecurityProperties.BASIC_AUTH_ORDER)
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CsrfTokenRepository tokenRepository;

    private String rememberMeAppKey = "yourAppKey";

    private String[] whiteUrls = new String[]{"/jsondoc*"};

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().csrfTokenRepository(tokenRepository).and().addFilterBefore(new AuthorizationHeaderFilter(), RememberMeAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers("/home").authenticated()
                .anyRequest().authenticated()
                .and()
                .httpBasic().disable()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .usernameParameter("email")
                .defaultSuccessUrl("/home")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("remember-me")
                .permitAll()
                .and()
                .rememberMe().key(rememberMeAppKey)
                .and()
                .exceptionHandling().accessDeniedPage("/403");
    }

    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(whiteUrls);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, UserDetailsService userDetailsService) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationManagerBean();
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