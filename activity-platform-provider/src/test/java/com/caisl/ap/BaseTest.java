
package com.caisl.ap;

import com.caisl.ap.ActivityPlatformApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * BaseTest
 *
 * @author caisl
 * @since 2019-01-12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ActivityPlatformApplication.class)
public abstract class BaseTest {
}
