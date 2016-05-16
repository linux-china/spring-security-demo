package org.mvnsearch.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;

import static com.google.common.base.Predicates.and;

/**
 * web security config
 *
 * @author linux_china
 */
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER - 1)
class WebSecurityConfig2 extends WebSecurityConfigurerAdapter {
    private String[] whiteUrls = new String[]{"/", "/webjars/**", "/wro4j/**", "/static/**", "/js/**", "/images/**", "**/favicon.ico", "/login", "/css/**", "/doLogin"};

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(whiteUrls).permitAll();
    }

    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(whiteUrls);
    }
}