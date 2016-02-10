package org.mvnsearch.service.impl;

import org.mvnsearch.service.HelloMessageService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * hello service implementation
 *
 * @author linux_china
 */
@Service
public class HelloMessageServiceImpl implements HelloMessageService {
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getMessage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "Hello " + authentication.getName();
    }
}
