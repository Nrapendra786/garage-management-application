package com.ysuratasktest;

import com.ysuratask.Application;
import com.ysuratask.utils.NumberUtil;
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
public class NumberUtilTest {

    @Test
    public void testNumberUtil(){
        Assert.assertEquals(new Integer(NumberUtil.ZERO),new Integer(0));
        Assert.assertEquals(new Integer(NumberUtil.ONE),new Integer(1));
        Assert.assertEquals(new Integer(NumberUtil.TWO),new Integer(2));
    }
}
