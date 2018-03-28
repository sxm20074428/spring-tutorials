package cache.ordinary.main;

import cache.ordinary.service.AccountService2Impl;
import com.spring.domain.Account;
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
 * @time 2016/9/25 14:31
 * @since 0.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-cache.xml"})
public class AccountService2Test {

    @Autowired
    private AccountService2Impl accountService2;

    private final Logger logger = LoggerFactory.getLogger(AccountService2Test.class);

    @Test
    public void testInject() {
        assertNotNull(accountService2);
    }

    @Test
    public void testGetAccountByName() throws Exception {
        logger.info("first query...");
        Account account =  accountService2.getAccountByName("accountName");
        logger.info("passwd={}", account.getPassword());

        logger.info("second query...");
        account=accountService2.getAccountByName("accountName");
        logger.info("passwd={}", account.getPassword());

    }

    @Test
    public void testUpdateAccount() throws Exception {
        Account account1 = accountService2.getAccountByName("accountName1");
        logger.info(account1.toString());
        Account account2 = accountService2.getAccountByName("accountName2");
        logger.info(account2.toString());

        account2.setPassword("121212");
        accountService2.updateAccount(account2);

        // account1会走缓存
        account1 = accountService2.getAccountByName("accountName1");
        logger.info(account1.toString());

        // account2会查询db
        account2 = accountService2.getAccountByName("accountName2");
        logger.info(account2.toString());

    }

    @Test
    public void testReload() throws Exception {
        accountService2.reload();
        // 这2行查询数据库
        accountService2.getAccountByName("somebody1");
        accountService2.getAccountByName("somebody2");

        // 这两行走缓存
        accountService2.getAccountByName("somebody1");
        accountService2.getAccountByName("somebody2");
    }
}
