package org.mvnsearch.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * login controller
 *
 * @author linux_china
 */
@Controller
public class LoginController {
  @Autowired
  private RememberMeServices rememberMeServices;
  @Autowired
  private UserDetailsService userDetailsService;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @RequestMapping(path = "/login", method = RequestMethod.GET)
  public String login(HttpServletRequest request) {
    return "login";
  }

  @RequestMapping(path = "/doLogin", method = RequestMethod.POST)
  public String doLogin(HttpServletRequest request, HttpServletResponse response) {
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    UserDetails userDetails = userDetailsService.loadUserByUsername(email);
    if (userDetails != null && passwordEncoder.matches(password, userDetails.getPassword())) {
      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
      rememberMeServices.loginSuccess(request, response, authentication);
      return "redirect:/home";
    }
    return "login";
  }

}
