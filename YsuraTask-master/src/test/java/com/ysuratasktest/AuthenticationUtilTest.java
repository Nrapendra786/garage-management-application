package com.ysuratasktest;

import com.ysuratask.Application;
import com.ysuratask.utils.AppUtil;
import com.ysuratask.utils.AuthenticationUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by NrapendraKumar on 26-03-2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class AuthenticationUtilTest {

    @Test
    public void testAuthenticationUtil() {
        Assert.assertEquals(AuthenticationUtil.USERNAME,"manager");
        Assert.assertEquals(AuthenticationUtil.PASSWORD,"manager");
        Assert.assertEquals(AuthenticationUtil.ROLE_MANAGER,"MANAGER");
    }
}
