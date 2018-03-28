package com.spring.annotation.cache.aspect.provider;

/**
 * 缓存提供者接口
 * 
 * @author 苏晓蒙
 * @time 2015年4月27日 下午2:46:06
 * @version 0.1
 * @since 0.1
 */
public interface CacheProvider {

    /**
     * 删除
     * 
     * @author 苏晓蒙
     * @time 2015年4月27日 下午2:46:24
     * @version 0.1
     * @since 0.1
     * @param cacheKey
     * @param field
     */
    public void removeCache(String cacheKey, String field);

    /**
     * 返回的是 指定的类型
     * 
     * @author 苏晓蒙
     * @time 2015年4月27日 下午3:04:29
     * @version 0.1
     * @since 0.1
     * @param key
     * @param filed
     * @param returnType
     * @return
     */
    public <T> T readCache(String key, String filed, Class<T> returnType);

    /**
     * 写入缓存
     * 
     * @author 苏晓蒙
     * @time 2015年4月27日 下午3:26:56
     * @version 0.1
     * @since 0.1
     * @param key
     * @param filed
     * @param expire 失效时间
     * @param retVal
     */
    public void writerCache(String key, String filed, Integer expire, Object retVal);

}
