package org.mvnsearch.controller;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
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
    public String home(HttpServletRequest request) {
        return "home";
    }
}
