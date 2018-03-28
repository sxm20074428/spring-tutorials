package cache.manager;

import org.springframework.cache.support.AbstractCacheManager;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2016/9/25 15:06
 * @since 0.1
 */

public class MyCacheManager extends AbstractCacheManager {

    private Collection<? extends MyCache> caches;

    /**
     * Specify the collection of Cache instances to use for this CacheManager.
     */
    public void setCaches(Collection<? extends MyCache> caches) {
        this.caches = caches;
    }

    @Override
    protected Collection<? extends MyCache> loadCaches() {
        return this.caches;
    }
}
