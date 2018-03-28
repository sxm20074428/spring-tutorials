package cache.ordinary.service;

import com.google.common.base.Optional;
import com.spring.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2016/9/25 14:35
 * @since 0.1
 */
@Service
public class AccountService2Impl {

    private final Logger logger = LoggerFactory.getLogger(AccountService2Impl.class);

    // 使用了一个缓存名叫 accountCache
    @Cacheable(value = "accountCache")
    public Account getAccountByName(String accountName) {

        // 方法内部实现不考虑缓存逻辑，直接实现业务
        logger.info("real querying account... {}", accountName);
        Optional<Account> accountOptional = getFromDB(accountName);
        if (!accountOptional.isPresent()) {
            throw new IllegalStateException(String.format("can not find account by account name : [%s]", accountName));
        }

        return accountOptional.get();
    }

    @Cacheable(value = "accountCache", condition = "#accountName.length() <= 4")// 缓存名叫 accountCache
    public Account getAccountByNameWithCondition(String accountName) {
        // 方法内部实现不考虑缓存逻辑，直接实现业务
        return getFromDB(accountName).get();
    }

    @Cacheable(value = "accountCache", key = "#accountName.concat(#password)")
    public Account getAccount(String accountName, String password, boolean sendLog) {
        // 方法内部实现不考虑缓存逻辑，直接实现业务
        return getFromDB(accountName, password).get();
    }

    // 用 @CachePut 注释，这个注释可以确保方法被执行，同时方法的返回值也被记录到缓存中。
    @CachePut(value = "accountCache", key = "#account.getPassword()")
    public void updateAccount(Account account) {
        logger.info("real update db...{}", account.toString());
    }

    //@CachEvict 主要针对方法配置，能够根据一定的条件对缓存进行清空
    @CacheEvict(value = "accountCache", allEntries = true)
    public void reload() {
    }


    private Optional<Account> getFromDB(String accountName) {
        logger.info("real querying db... {}", accountName);
        //Todo query data from database
        return Optional.fromNullable(new Account(accountName));
    }

    private Optional<Account> getFromDB(String accountName, String password) {
        logger.info("real querying db... {}", accountName);
        //Todo query data from database
        return Optional.fromNullable(new Account(accountName, password));
    }
}
