package org.mvnsearch.controller;

import org.mvnsearch.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * workspace controller
 *
 * @author linux_china
 */
@Controller
public class WorkSpaceController {
    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping("/home")
    public String home(@AuthenticationPrincipal User user, HttpServletRequest request) {
        return "home";
    }
}
