package org.mvnsearch.service;

import org.mvnsearch.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * user details service implementation
 *
 * @author linux_china
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setId(1L);
        user.setEmail("admin");
        user.setPasswordHash("$2a$10$.K9OBfoRWH5PmbvCMpvxWONIszIuKN1SLXo960NXUZMn/EJi9lwJK");
        user.setRole("admin");
        return new CurrentUserDetails(user);
    }
}
