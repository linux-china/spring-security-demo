package org.mvnsearch.config;

import org.mvnsearch.service.CurrentUserDetails;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * User Details based authentication
 *
 * @author linux_china
 */
public class UserDetailsAuthentication extends AbstractAuthenticationToken {
    private final Object principal;
    private final Object credentials;

    public UserDetailsAuthentication(Object credentials, CurrentUserDetails currentUser) {
        super(currentUser.getAuthorities());
        this.principal = currentUser;
        this.credentials = credentials;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
