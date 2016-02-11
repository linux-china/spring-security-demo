package org.mvnsearch.service;

import org.mvnsearch.domain.User;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * current user details
 *
 * @author linux_china
 */
public class CurrentUserDetails extends org.springframework.security.core.userdetails.User {
    private User user;

    public CurrentUserDetails(User user) {
        super(user.getEmail(), user.getPasswordHash(), AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "user=" + user +
                "} " + super.toString();
    }
}
