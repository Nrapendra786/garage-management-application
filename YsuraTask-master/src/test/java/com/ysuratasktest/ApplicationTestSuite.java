package com.ysuratasktest;

import com.ysuratask.utils.UrlUtil;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by NrapendraKumar on 26-03-2016.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AppUtilTest.class,
        AuthenticationUtilTest.class,
        DependencyInjectionTest.class,
        GarageServiceImplTest.class,
        GarageSpaceInfoFileReaderServiceImplTest.class,
        NumberUtilTest.class,
        UrlUtilTest.class
})
public class ApplicationTestSuite {
}
