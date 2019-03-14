package org.mvnsearch.restful;

import org.mvnsearch.domain.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * user controller
 *
 * @author linux_china
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping("/{id}")
    public User show( @PathVariable Integer id,
                     HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        user.setId(1L);
        user.setEmail("demo@demo.com");
        return user;
    }
}
