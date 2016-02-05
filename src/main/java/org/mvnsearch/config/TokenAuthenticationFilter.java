package org.mvnsearch.config;

import org.mvnsearch.domain.User;
import org.mvnsearch.service.CurrentUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token authentication filter
 *
 * @author linux_china
 */
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //todo ignore static resources
        String credentials = request.getHeader("Token");
        if (credentials == null) {
            Cookie cookie = WebUtils.getCookie(request, "token");
            if (cookie != null) {
                credentials = cookie.getValue();
            }
        }
        if (credentials != null) {
            User user = new User();
            user.setEmail("admin@demo.com");
            user.setId(1L);
            user.setRole("buyer");
            user.setPasswordHash(credentials);
            CurrentUser currentUser = new CurrentUser(user);
            SecurityContextHolder.getContext().setAuthentication(new UserDetailsAuthentication(credentials, currentUser));
        }
        filterChain.doFilter(request, response);
    }
}
