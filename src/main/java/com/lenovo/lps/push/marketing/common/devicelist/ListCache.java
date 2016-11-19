package com.lenovo.lps.push.marketing.common.devicelist;

import java.io.IOException;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;

import com.lenovo.lps.push.common.utils.CacheKey;

public class ListCache {
	private static final Logger logger = Logger.getLogger(ListCache.class);
	private static final CacheKey KEY_LIST = new CacheKey("C.DEVICE_LIST.");
	private static final CacheKey KEY_LIST_STATE = new CacheKey("S.DEVICE_LIST_STATE.");
	private static final CacheKey KEY_LIST_ID = new CacheKey("S.DEVICE_LIST_ID");
	private static final String INIT_LIST_MEMBER = "com.lenovo.lps.push.marketing.devicelist.init_list_member";
	private JedisPool pool;
	
	public ListCache(String redisHost, int redisPort) {
		JedisPoolConfig pollConf = new JedisPoolConfig();
		pollConf.setMaxIdle(50);
		pollConf.setMaxTotal(100);
		pollConf.setMaxWaitMillis(1000);
		pollConf.setTestOnBorrow(false);
		pool = new JedisPool(pollConf, redisHost,redisPort);
	}
	
	private void returnClient(Jedis j, boolean jedisException) {
		try {
			if(jedisException){
				if(j!=null){
					pool.returnBrokenResource(j);
				}
			}else{
				pool.returnResource(j);
			}
		} catch (Exception e) {
			logger.warn(e,e);
		}
	}
	
	private void set(String key,String value) throws IOException {
		Jedis j = null;
		boolean jedisException = false;
		try{
			j = pool.getResource();
			value = j.set(key, value);
			if(logger.isDebugEnabled()){
				logger.debug("set redis :  " + key + "=" + value);
			}
		}catch(JedisException e){
			jedisException = true;
			logger.warn(e,e);
			throw new IOException("write to redis error : key = " + key + "  value = " + value );
		}finally{
			returnClient(j, jedisException);
		}
	}
	
	private String get(String key) {
		Jedis j = null;
		boolean jedisException = false;
		String value = null;
		try{
			j = pool.getResource();
			value = j.get(key);
			return value;
		}catch(JedisException e){
			jedisException = true;
			logger.warn(e,e);
		}finally{
			returnClient(j, jedisException);
		}
		return null;
	}

	public boolean isInList(String listid,String deviceId){
		Jedis j = null;
		boolean jedisException = false;
		try{
			j = pool.getResource();
			if(j.sismember(KEY_LIST.getKey(listid), deviceId)){
				return true;
			}
		}catch(JedisException e){
			jedisException = true;
		}finally{
			returnClient(j, jedisException);
		}
		return false;
	}
	
	public void addToList(String listid,String[] deviceId, long ttl){
		Jedis j = null;
		boolean jedisException = false;
		try{
			j = pool.getResource();
			String key = KEY_LIST.getKey(listid);
			j.sadd(key, deviceId);
			if(ttl>0){
				j.expireAt(key, System.currentTimeMillis()+ttl);
			}
		}catch(JedisException e){
			jedisException = true;
		}finally{
			returnClient(j, jedisException);
		}
	}
	
	public void setListCacheState(String listid,String state){
		try {
			set(KEY_LIST_STATE.getKey(listid),state);
		} catch (IOException e) {
			logger.warn(e,e);
		}
	}
	
	public String getListCacheState(String listid){
		return get(KEY_LIST_STATE.getKey(listid));
	}
	
	public String createList(long ttl){
		Jedis j = null;
		boolean jedisException = false;
		try{
			j = pool.getResource();
			String id = "LIST_" + j.incr(KEY_LIST_ID.getKey());
			addToList(id,new String[]{INIT_LIST_MEMBER},ttl);
			return id;
		}catch(JedisException e){
			jedisException = true;
			logger.warn(e,e);
		}finally{
			returnClient(j, jedisException);
		}
		return null;
	}
}
