package org.mvnsearch.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * authorization header filter
 *
 * @author linux_china
 */
public class AuthorizationHeaderFilter extends GenericFilterBean {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            String authorization = request.getHeader("authorization");
            if (authorization != null && !authorization.isEmpty()) {
                request = new AuthorizationHeaderWrapper(request, authorization);
            }
        }
        chain.doFilter(request, response);
    }

    public static class AuthorizationHeaderWrapper extends HttpServletRequestWrapper {

        private String authorization;

        public AuthorizationHeaderWrapper(HttpServletRequest request, String authorization) {
            super(request);
            this.authorization = authorization;
        }

        @Override
        public Cookie[] getCookies() {
            String rememberMeKey = "remember-me";
            Cookie[] cookies = super.getCookies();
            if (cookies == null || cookies.length == 0) {
                cookies = new Cookie[]{new Cookie(rememberMeKey, authorization)};
            } else {
                boolean found = false;
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(rememberMeKey)) {
                        found = true;
                        cookie.setValue(authorization);
                        break;
                    }
                }
                if (!found) {
                    Cookie[] temp = new Cookie[cookies.length + 1];
                    System.arraycopy(cookies, 0, temp, 0, cookies.length);
                    temp[temp.length - 1] = new Cookie(rememberMeKey, authorization);
                    cookies = temp;
                }
            }
            return cookies;
        }
    }

}
