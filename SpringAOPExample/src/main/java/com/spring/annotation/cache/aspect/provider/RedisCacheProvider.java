package com.spring.annotation.cache.aspect.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.UnsupportedEncodingException;

/**
 * redis的缓存实现
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2015年4月27日 下午2:47:49
 * @since 0.1
 */
@Component
public class RedisCacheProvider implements CacheProvider {

    private static final Logger logger = LoggerFactory.getLogger(RedisCacheProvider.class);

    @Autowired
    private JedisPool jedisPool;

    private static final String ENCODING_CHARSET = "ISO-8859-1";

    private String readCache(String key, String filed) {
        Jedis jedis = jedisPool.getResource();
        String text = StringUtils.isEmpty(filed) ? jedis.get(key) : jedis.hget(key, filed);
        jedis.close();
        return text;
    }

    public void removeCache(String cacheKey, String field) {
        Jedis jedis = jedisPool.getResource();
        if (StringUtils.isEmpty(field)) {
            jedis.del(cacheKey);
        } else {
            jedis.hdel(cacheKey, field);
        }
        jedis.close();
    }

    /**
     * 读取缓存中的对象
     */
    @SuppressWarnings("unchecked")
    public <T> T readCache(String key, String filed, Class<T> returnType) {
        String serializationStr = readCache(key, filed);
        if (null != serializationStr) {
            try {
                return (T) SerializationUtils.deserialize(serializationStr.getBytes(ENCODING_CHARSET));
            } catch (UnsupportedEncodingException e) {
                logger.error(e.toString());
            }
        }
        return null;
    }

    /**
     * 写入缓存
     */
    public void writerCache(String key, String filed, Integer expire, Object retVal) {
        Jedis jedis = jedisPool.getResource();
        try {
            if (StringUtils.isEmpty(filed)) {
                jedis.set(key, new String(SerializationUtils.serialize(retVal), ENCODING_CHARSET));
            } else {
                jedis.hset(key, filed, new String(SerializationUtils.serialize(retVal), ENCODING_CHARSET));
            }
            if (null != expire) {
                jedis.expire(key, expire);
            }
        } catch (UnsupportedEncodingException e) {
            logger.error(e.toString());
        }
        jedis.close();
    }

}
