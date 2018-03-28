package cache.ordinary.main;

import cache.ordinary.service.AccountService1Impl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2016/9/25 12:36
 * @since 0.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-cache.xml"})
public class AccountService1Test {

    @Autowired
    private AccountService1Impl accountService;

    private final Logger logger = LoggerFactory.getLogger(AccountService1Test.class);


    @Test
    public void testInject() {
        assertNotNull(accountService);
    }

    @Test
    public void testGetAccountByName() throws Exception {

        accountService.getAccountByName("accountName");
        accountService.getAccountByName("accountName");

        accountService.reload();
        logger.info("after reload ....");

        accountService.getAccountByName("accountName");
        accountService.getAccountByName("accountName");
    }
}
