/*
package com.wiscom.hence.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisDataException;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
@Configuration
@PropertySource("file:./conf/bootRedis.properties")
public class JedisUtil {

    private static Logger logger = LogManager.getLogger(JedisUtil.class);

    //redis连接池
    private static JedisPool jedisPool;

    //redis数据库的连接参数
    @Value("${redis.ip}")
    String ip;

    @Value("${redis.port}")
    int port;

    @Value("${redis.password}")
    String password;

    @Value("${redis.index}")
    int index;

    @Value("${redis.maxActive}")
    int maxActive;

    @Value("${redis.maxIdle}")
    int maxIdle;

    @Value("${redis.maxWait}")
    int maxWait;

    @Value("${redis.testOnBorrow}")
    boolean testOnBorrow;

    @Value("${redis.timeout}")
    int timeout;


    */
/**
     * 在构造代码块中初始化jedis连接池
     *//*


    @PostConstruct
    public void init() {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            //连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true

            config.setBlockWhenExhausted(true);

            //设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)

            config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");

            //是否启用pool的jmx管理功能, 默认true

            config.setJmxEnabled(true);

            //MBean ObjectName = new ObjectName("org.apache.commons.pool2:type=GenericObjectPool,name=" + "pool" + i); 默认为"pool", JMX不熟,具体不知道是干啥的...默认就好.

            config.setJmxNamePrefix("pool");

            //是否启用后进先出, 默认true

            config.setLifo(true);

            //最大空闲连接数, 默认8个

            config.setMaxIdle(8);

            //最大连接数, 默认8个

            config.setMaxTotal(8);

            //获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1

            config.setMaxWaitMillis(-1);

            //逐出连接的最小空闲时间 默认1800000毫秒(30分钟)

            config.setMinEvictableIdleTimeMillis(1800000);

            //最小空闲连接数, 默认0

            config.setMinIdle(0);

            //每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3

            config.setNumTestsPerEvictionRun(3);

            //对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)

            config.setSoftMinEvictableIdleTimeMillis(1800000);

            //在获取连接的时候检查有效性, 默认false

            config.setTestOnBorrow(false);

            //在空闲时检查有效性, 默认false

            config.setTestWhileIdle(false);

            //逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1

            config.setTimeBetweenEvictionRunsMillis(-1);

            // 构造连接池
            jedisPool = new JedisPool(config, ip, port, timeout, password, index);
        } catch (Exception e) {
            logger.error("init:" + e.getMessage());

            throw new RuntimeException("初始化redis数据库连接失败.....");
        }
    }

    public static void setAllKeyValue(Map<String, Map<String, String>> map, int expireTime) {
        Jedis jedis = null;
        boolean flag = false;
        try {
            jedis = jedisPool.getResource();
            Pipeline pie = jedis.pipelined();

            for (String key : map.keySet()) {
                pie.hmset(key, map.get(key));
                if (expireTime > 0) {
                    pie.expire(key, expireTime);
                }
            }
            pie.sync();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("setAllKeyValue:" + e.getMessage());
            flag = handleJedisException(e);
        } finally {
            closeResource(jedis, flag);
        }
    }

    //获取List<Map>
    public static List<Map<String, String>> getAllKeysValueList(String key) {

        List<Map<String, String>> result = new ArrayList<>();
        Map<String, Response<Map<String, String>>> responseMap = new HashMap<String, Response<Map<String, String>>>();
        boolean flag = false;
        Jedis jedis = null;
        String key1 = "";
        try {
            jedis = jedisPool.getResource();
            Pipeline pie = jedis.pipelined();
            Set<String> keys = jedis.keys(key + "*");

            for (String value : keys) {
                Response<Map<String, String>> keyValue = pie.hgetAll(value);
                responseMap.put(value, keyValue);
            }
            pie.sync();
            Iterator<Map.Entry<String, Response<Map<String, String>>>> it = responseMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Response<Map<String, String>>> entry = it.next();
                key1 = entry.getKey();
                result.add(entry.getValue().get());

                //jedis.del(key1);
            }
        } catch (Exception e) {
            e.printStackTrace();

            logger.error("getAllKeysValue:" + e.getMessage());

            flag = handleJedisException(e);
        } finally {
            closeResource(jedis, flag);
        }
        return result;
    }

    //根据key值获取对应参数 模糊查询
    public static Set<String> getKeys(String pattern) {
        Set<String> result = null;
        boolean flag = false;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis == null) {
                return null;
            }
            result = jedis.keys(pattern + "*");
            return result;
        } catch (Exception e) {
            logger.error("redis getKeys 出错", e);
            flag = handleJedisException(e);
        } finally {
            if (null != jedis) {
                closeResource(jedis, flag);
            }
        }
        return null;
    }


    */
/**
     * 处理redis异常.
     *
     * @param exception 异常对象
     * @return 回收redis连接的方式的布尔值
     *//*

    private static boolean handleJedisException(Exception exception) {

        if (exception instanceof JedisDataException && (exception.getMessage() == null) && (exception.getMessage().indexOf("READONLY") == -1)) {
            return false;
        }
        return true;
    }

    */
/**
     * 回收redsi连接
     *
     * @param jedis  redis连接
     * @param Broken 处理方式，boolean类型
     *//*

    private static void closeResource(Jedis jedis, boolean Broken) {
        if (Broken) {
            jedisPool.returnBrokenResource(jedis);
        } else {
            jedisPool.returnResource(jedis);
        }
    }


}*/
