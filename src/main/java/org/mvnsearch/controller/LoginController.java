package org.mvnsearch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * login controller
 *
 * @author linux_china
 */
@Controller

public class LoginController {

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        return "login";
    }

    @RequestMapping(path = "/doLogin", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        if ("admin@demo.com".equals(email)) {
            Cookie token = new Cookie("token", "1234567");
            token.setMaxAge(7 * 24 * 60 * 60);
            response.addCookie(token);
            return "redirect:/home";
        }
        return "login";
    }

}
