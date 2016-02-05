package org.mvnsearch.controller;

import org.mvnsearch.domain.User;
import org.mvnsearch.service.CurrentUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * controller advice:  get current login user
 *
 * @author linux_china
 */
@ControllerAdvice
public class CurrentUserControllerAdvice {

    @ModelAttribute("currentUser")
    public User getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null : ((CurrentUserDetails) authentication.getPrincipal()).getUser();
    }

}
