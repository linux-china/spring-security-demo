package org.mvnsearch;

import org.junit.Test;
import org.mvnsearch.service.HelloMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;

/**
 * spring security demo test
 *
 * @author linux_china
 */
//@ContextConfiguration(classes = SampleWebSecurityConfig.class)
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
public class SpringSecurityDemoTest extends SpringTest {
    @Autowired
    private HelloMessageService helloMessageService;

    @Test
    public void testSpike() {
        String message = helloMessageService.getMessage();
        System.out.println(message);
    }


}
