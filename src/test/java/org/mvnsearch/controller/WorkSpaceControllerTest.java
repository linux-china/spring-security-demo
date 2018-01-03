package org.mvnsearch.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mvnsearch.SpringSecurityDemoApplication;
import org.mvnsearch.service.CurrentUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * workspace controller test
 *
 * @author linux_china
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringSecurityDemoApplication.class)
public class WorkSpaceControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    public void testGood() throws Exception {
        mockMvc.perform(get("/home").with(user(currentUserDetails())))
                .andExpect(status().isOk());
    }

    /**
     * current user details
     *
     * @return user details
     */
    public UserDetails currentUserDetails() {
        org.mvnsearch.domain.User user = new org.mvnsearch.domain.User();
        user.setEmail("libing.chen@gmail.com");
        user.setId(1L);
        user.setAuthoritiesText("ROLE_USER");
        return new CurrentUserDetails(user);
    }
}
