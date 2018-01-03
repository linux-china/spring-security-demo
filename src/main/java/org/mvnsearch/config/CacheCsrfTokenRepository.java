package org.mvnsearch.config;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * CSRF token repository
 *
 * @author linux_china
 */
public class CacheCsrfTokenRepository implements CsrfTokenRepository {

    private static final String DEFAULT_CSRF_PARAMETER_NAME = "_csrf";
    private static final String DEFAULT_CSRF_HEADER_NAME = "X-CSRF-TOKEN";
    private Map<String, Date> tokens = new HashMap<>();

    public CsrfToken generateToken(HttpServletRequest request) {
        String tokenValue = UUID.randomUUID().toString();
        return new DefaultCsrfToken(DEFAULT_CSRF_HEADER_NAME, DEFAULT_CSRF_PARAMETER_NAME, tokenValue);
    }

    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
        tokens.put(token.getToken(), new Date());
    }

    public CsrfToken loadToken(HttpServletRequest request) {
        String tokenValue = request.getParameter(DEFAULT_CSRF_PARAMETER_NAME);
        if (tokenValue != null && tokens.containsKey(tokenValue)) {
            tokens.remove(tokenValue);
            return new DefaultCsrfToken(DEFAULT_CSRF_HEADER_NAME, DEFAULT_CSRF_PARAMETER_NAME, tokenValue);
        }
        return null;
    }
}
