package cache.ordinary;
/**
 * Created by sxm on 2016/9/25.
 */

import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 缓存管理器
 * 负责实现缓存逻辑，支持对象的增加、修改和删除，支持值对象的泛型
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2016/9/25 11:05
 * @since 0.1
 */
@Component
public class CacheContext<T> {

    private Map<String, T> cache = Maps.newConcurrentMap();

    public T get(String key) {
        return cache.get(key);
    }

    public void addOrUpdateCache(String key, T value) {
        cache.put(key, value);
    }

    // 根据 key 来删除缓存中的一条记录
    public void evictCache(String key) {
        if (cache.containsKey(key)) {
            cache.remove(key);
        }
    }

    // 清空缓存中的所有记录
    public void evictCache() {
        cache.clear();
    }
}
