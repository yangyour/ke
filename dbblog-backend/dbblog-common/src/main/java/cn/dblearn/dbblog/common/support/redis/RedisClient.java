package cn.dblearn.dbblog.common.support.redis;

import org.apache.commons.lang.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;


/**
 * <p>redis 客户端</p>
 *
 */
public class RedisClient {

  public static JedisPool pool = null;

  /**
   * <p>传入ip和端口号构建redis 连接池</p>
   * @param ip ip
   * @param prot 端口
   */
  public RedisClient(String ip, int port) {
    if (pool == null) {
      JedisPoolConfig config = new JedisPoolConfig();
      // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
      config.setMaxIdle(5);
      // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
      config.setTestOnBorrow(true);
      pool = new JedisPool(config, ip, port, 100000);
    }
  }

  /**
   * <p>传入ip和端口号构建redis 连接池</p>
   * @param ip ip
   * @param prot 端口
   */
  public RedisClient(String ip, int port, String password) {
    if (pool == null) {
      JedisPoolConfig config = new JedisPoolConfig();
      // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
      config.setMaxIdle(5);
      // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
      config.setTestOnBorrow(true);
      pool = new JedisPool(config, ip, port, 100000,password);
    }
  }

  /**
   * <p>通过配置对象 ip 端口 构建连接池</p>
   * @param config 配置对象
   * @param ip ip
   * @param prot 端口
   */
  public RedisClient(JedisPoolConfig config , String ip, int prot){
    if (pool == null) {
      pool = new JedisPool(config,ip,prot,10000);
    }
  }

  /**
   * <p>通过配置对象 ip 端口 超时时间 构建连接池</p>
   * @param config 配置对象
   * @param ip ip
   * @param prot 端口
   * @param timeout 超时时间
   */
  public RedisClient(JedisPoolConfig config , String ip, int prot , int timeout){
    if (pool == null) {
      pool = new JedisPool(config,ip,prot,timeout);
    }
  }

  /**
   * <p>通过连接池对象 构建一个连接池</p>
   * @param pool 连接池对象
   */
  public RedisClient(JedisPool pool){
    if (RedisClient.pool == null) {
    	RedisClient.pool = pool;
    }
  }

  /**
   * <p>通过key获取储存在redis中的value</p>
   * <p>并释放连接</p>
   * @param key
   * @return 成功返回value 失败返回null
   */
  public  String get(String key){
    Jedis jedis = null;
    String value = null;
    try {
      jedis = pool.getResource();
      value = jedis.get(key);
    } catch (Exception e) {
      //pool.returnBrokenResource(jedis);
      e.printStackTrace();
    } finally {
      returnResource(pool, jedis);
    }
    return value;
  }
  /**
   * <p>通过key获取储存在redis中的value</p>
   * <p>并释放连接</p>
   * @param key
   * @return 成功返回value 失败返回null
   */
  public  String get(String key,String defalut){
    Jedis jedis = null;
    String value = null;
    try {
      jedis = pool.getResource();
      value = jedis.get(key);
      if(StringUtils.isEmpty(value)){
    	  return defalut;
      }
    } catch (Exception e) {
      //pool.returnBrokenResource(jedis);
      e.printStackTrace();
    } finally {
      returnResource(pool, jedis);
    }
    return value;
  }

  /**
   * <p>通过key获取储存在redis中的value</p>
   * <p>并释放连接</p>
   * @param key
   * @return 成功返回value 失败返回null
   */
  public  byte[] get(byte[] key){
    Jedis jedis = null;
    byte[] value = null;
    try {
      jedis = pool.getResource();
      value = jedis.get(key);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      returnResource(pool, jedis);
    }
    return value;
  }

  /**
   * <p>向redis存入key和value,并释放连接资源</p>
   * <p>如果key已经存在 则覆盖</p>
   * @param key
   * @param value
   * @return 成功 返回OK 失败返回 0
   */
  public  String set(String key,String value){
    Jedis jedis = null;
    try {
      jedis = pool.getResource();

      return jedis.set(key, value);
    } catch (Exception e) {
      //pool.returnBrokenResource(jedis);
      e.printStackTrace();
      return "0";
    } finally {
      returnResource(pool, jedis);
    }
  }

  public String set(String key, String value, int expire) {
		Jedis jedis = pool.getResource();
		try {
			value = jedis.set(key, value);
			if (expire != 0) {
				jedis.expire(key, expire);
			}
			return value;
		} catch (Exception e) {
		      //pool.returnBrokenResource(jedis);
		      e.printStackTrace();
		      return "0";
		    } finally {
		      returnResource(pool, jedis);
		    }

  }

  /**
   * <p>向redis存入key和value,并释放连接资源</p>
   * <p>如果key已经存在 则覆盖</p>
   * @param key
   * @param value
   * @return 成功 返回OK 失败返回 0
   */
  public  String set(byte[] key,byte[] value){
    Jedis jedis = null;
    try {
      jedis = pool.getResource();

      return jedis.set(key, value);
    } catch (Exception e) {
      e.printStackTrace();
      return "0";
    } finally {
      returnResource(pool, jedis);
    }
  }

  public String set(byte[] key,byte[] value, int expire) {
		Jedis jedis = pool.getResource();
		try {
			String v = jedis.set(key, value);
			if (expire != 0) {
				jedis.expire(key, expire);
			}
			return v;
		} catch (Exception e) {
		      e.printStackTrace();
		      return "0";
		    } finally {
		      returnResource(pool, jedis);
		    }

  }

  /**
   *
   * @param key
   * @param time
   * @return
   */
  public Long expire(String key,int time){
    Jedis jedis = null;
    try {
      jedis = pool.getResource();
      return jedis.expire(key,time);
    } catch (Exception e) {
      //pool.returnBrokenResource(jedis);
      e.printStackTrace();
      return 0L;
    } finally {
      returnResource(pool, jedis);
    }
  }

  /**
   * <p>删除指定的key,也可以传入一个包含key的数组</p>
   * @param keys 一个key  也可以使 string 数组
   * @return 返回删除成功的个数
   */
  public Long del(String...keys){
    Jedis jedis = null;
    try {
      jedis = pool.getResource();
      return jedis.del(keys);
    } catch (Exception e) {
      //pool.returnBrokenResource(jedis);
      e.printStackTrace();
      return 0L;
    } finally {
      returnResource(pool, jedis);
    }
  }

  public Long del(byte[] key){
	    Jedis jedis = null;
	    try {
	      jedis = pool.getResource();
	      return jedis.del(key);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return 0L;
	    } finally {
	      returnResource(pool, jedis);
	    }
	  }

  /**
   * 删除匹配项
   * @param pattern
   * @return
   */
  public Long delPattern(String pattern){
	  	Set<String> keys = keys(pattern);
	  	long count=0;
	  	for(String key:keys){
	  		count=count+del(key);
	  	}
	  	return count;
	  }

  /**
   * <p>通过key向指定的value值追加值</p>
   * @param key
   * @param str
   * @return 成功返回 添加后value的长度 失败 返回 添加的 value 的长度  异常返回0L
   */
  public Long append(String key ,String str){
    Jedis jedis = null;
    Long res = null;
    try {
      jedis = pool.getResource();
      res = jedis.append(key, str);
    } catch (Exception e) {
      //pool.returnBrokenResource(jedis);
      e.printStackTrace();
      return 0L;
    } finally {
      returnResource(pool, jedis);
    }
    return res;
  }

  /**
   * <p>返回满足pattern表达式的所有key</p>
   * <p>keys(*)</p>
   * <p>返回所有的key</p>
   * @param pattern
   * @return
   */
  public Set<String> keys(String pattern){
    Jedis jedis = null;
    Set<String> res = null;
    try {
      jedis = pool.getResource();
      res = jedis.keys(pattern);
    } catch (Exception e) {
      //pool.returnBrokenResource(jedis);
      e.printStackTrace();
    } finally {
      returnResource(pool, jedis);
    }
    return res;
  }

  /**
   * <p>返回满足pattern表达式的所有key</p>
   * <p>keys(*)</p>
   * <p>返回所有的key</p>
   * @param pattern
   * @return
   */
  public Set<byte[]> keys(byte[] pattern){
    Jedis jedis = null;
    Set<byte[]> res = null;
    try {
      jedis = pool.getResource();
      res = jedis.keys(pattern);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      returnResource(pool, jedis);
    }
    return res;
  }

  /**
   * <p>通过key判断值得类型</p>
   * @param key
   * @return
   */
  public String type(String key){
    Jedis jedis = null;
    String res = null;
    try {
      jedis = pool.getResource();
      res = jedis.type(key);
    } catch (Exception e) {
      //pool.returnBrokenResource(jedis);
      e.printStackTrace();
    } finally {
      returnResource(pool, jedis);
    }
    return res;
  }

  /**
   * 返还到连接池
   *
   * @param pool
   * @param redis
   */
  public static void returnResource(JedisPool pool, Jedis jedis) {
    if (jedis != null) {
    	jedis.close();
    }
  }
}
