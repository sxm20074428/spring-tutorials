package cache.ordinary.service;

import cache.ordinary.CacheContext;
import com.google.common.base.Optional;
import com.spring.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2016/9/25 12:30
 * @since 0.1
 */
@Service
public class AccountService1Impl {

    private final Logger logger = LoggerFactory.getLogger(AccountService1Impl.class);

    @Resource
    private CacheContext<Account> accountCache;

    public Account getAccountByName(String accountName) {
        Account result = accountCache.get(accountName);
        if (result != null) {
            logger.info("getAccountByName from cache... {}", accountName);
            return result;
        }

        Optional<Account> accountOptional = getFromDB(accountName);
        if (!accountOptional.isPresent()) {
            throw new IllegalStateException(String.format("can not find account by account name : [%s]", accountName));
        }

        Account account = accountOptional.get();
        accountCache.addOrUpdateCache(accountName, account);
        return account;
    }

    public void reload() {
        accountCache.evictCache();
    }

    private Optional<Account> getFromDB(String accountName) {
        logger.info("real querying db... {}", accountName);
        //Todo query data from database
        return Optional.fromNullable(new Account(accountName));
    }
}
