package cache.manager;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import com.spring.domain.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2016/9/25 15:06
 * @since 0.1
 */
public class MyCache implements Cache {

    private String name;

    private Map<String, Account> accountMap = new HashMap<>();


    public MyCache() {
    }

    public MyCache(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object getNativeCache() {
        return accountMap;
    }

    @Override
    public ValueWrapper get(Object key) {
        ValueWrapper result = null;
        Account account = accountMap.get(key);
        if (account != null) {
            account.setPassword("from mycache:" + name);
            result = new SimpleValueWrapper(account);
        }
        return result;
    }

    /**
     * Return the value to which this cache maps the specified key,
     * generically specifying a type that return value will be cast to.
     * <p>Note: This variant of {@code getUserById} does not allow for differentiating
     * between a cached {@code null} value and no cache entry found at all.
     * Use the standard {@link #get(Object)} variant for that purpose instead.
     *
     * @param key  the key whose associated value is to be returned
     * @param type the required type of the returned value (may be
     *             {@code null} to bypass a type check; in case of a {@code null}
     *             value found in the cache, the specified type is irrelevant)
     * @return the value to which this cache maps the specified key
     * (which may be {@code null} itself), or also {@code null} if
     * the cache contains no mapping for this key
     * @throws IllegalStateException if a cache entry has been found
     *                               but failed to match the specified type
     * @see #get(Object)
     * @since 4.0
     */
    @Override
    public <T> T get(Object key, Class<T> type) {
        return null;
    }

    @Override
    public <T> T get(Object o, Callable<T> callable) {
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        Account account = (Account) value;
        accountMap.put((String) key, account);
    }

    /**
     * Atomically associate the specified value with the specified key in this cache
     * if it is not set already.
     * <p>This is equivalent to:
     * <pre><code>
     * Object existingValue = cache.getUserById(key);
     * if (existingValue == null) {
     *     cache.put(key, value);
     *     return null;
     * } else {
     *     return existingValue;
     * }
     * </code></pre>
     * except that the action is performed atomically. While all out-of-the-box
     * {@link CacheManager} implementations are able to perform the put atomically,
     * the operation may also be implemented in two steps, e.g. with a check for
     * presence and a subsequent put, in a non-atomic way. Check the documentation
     * of the native cache implementation that you are using for more details.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     * @return the value to which this cache maps the specified key (which may be
     * {@code null} itself), or also {@code null} if the cache did not contain any
     * mapping for that key prior to this call. Returning {@code null} is therefore
     * an indicator that the given {@code value} has been associated with the key.
     * @since 4.1
     */
    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        return null;
    }

    @Override
    public void evict(Object key) {
    }

    @Override
    public void clear() {
    }
}