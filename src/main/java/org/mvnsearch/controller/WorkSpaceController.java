package org.mvnsearch.controller;

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

    @RequestMapping("/home")
    public String home(HttpServletRequest request) {
        return "home";
    }
}
