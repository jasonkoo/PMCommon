package com.lenovo.lps.push.marketing.common.cache;

import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.lang.SerializationUtils;
import org.apache.log4j.Logger;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import com.lenovo.lps.push.common.PushConsts;
import com.lenovo.lps.push.common.utils.CacheKey;
import com.lenovo.lps.push.common.vo.AppInfo;
import com.lenovo.lps.push.marketing.common.vo.DeviceInfoVO;
import com.lenovo.lps.push.marketing.common.vo.DevicePushContext;
import com.lenovo.lps.push.marketing.common.vo.UserHitContext;

public class BigCache {
	private static final int PUSHCONTEXT_TTL_SECONDS = 60*60*24*8;
	private static final CacheKey KEY_DEVICE = new CacheKey("O.DEVICE.");
	public static final CacheKey KEY_HIT_CTX = new CacheKey("O.HIT.CONTEXT.");
	private static final CacheKey KEY_PUSH_CTX = new CacheKey("O.PUSH.CONTEXT.");
	private static final CacheKey KEY_AD_PUSHTO = new CacheKey("S.AD.PUSHTO.");
	private static final CacheKey KEY_LIST = new CacheKey("S.LIST.");
	private static final CacheKey KEY_APP = new CacheKey("O.APP.");
	private static final CacheKey KEY_GOAL_COUNT = new CacheKey("I.GOAL.");

	private static final Logger LOG = Logger.getLogger(BigCache.class);
	
	private ShardedJedisPool shardedJedisPool;

	/* (non-Javadoc)
	 * @see com.lenovo.lps.push.marketing.common.cache.IBigCache#getDeviceInfo(long)
	 */
	public DeviceInfoVO getDeviceInfo(long pid) throws IOException {
		byte[] key = KEY_DEVICE.getBinaryKey(Long.toString(pid));
		DeviceInfoVO deviceInfo =  (DeviceInfoVO)loadObject(key);
		return deviceInfo;
	}

	/* (non-Javadoc)
	 * @see com.lenovo.lps.push.marketing.common.cache.IBigCache#saveDeviceInfo(com.lenovo.lps.push.marketing.common.vo.DeviceInfoVO)
	 */
	public void saveDeviceInfo(DeviceInfoVO device) throws IOException {
		saveObject(
				KEY_DEVICE.getBinaryKey(Long.toString(device.getPid()))
				,device);
	}

	/* (non-Javadoc)
	 * @see com.lenovo.lps.push.marketing.common.cache.IBigCache#getUserHitContext(long)
	 */
	public UserHitContext getUserHitContext(long pid) throws IOException {
		byte[] key = KEY_HIT_CTX.getBinaryKey(Long.toString(pid));
		UserHitContext userHitContext =  (UserHitContext)loadObject(key);
		if(userHitContext==null){
			userHitContext = new UserHitContext();
			userHitContext.setPid(pid);
		}
		return userHitContext;
	}

	/* (non-Javadoc)
	 * @see com.lenovo.lps.push.marketing.common.cache.IBigCache#saveUserHitContext(com.lenovo.lps.push.marketing.common.vo.UserHitContext)
	 */
	public void saveUserHitContext(UserHitContext userHitContext) throws IOException {
		saveObject(
				KEY_HIT_CTX.getBinaryKey(Long.toString(userHitContext.getPid()))
				,userHitContext);
	}
	
	public Object loadObject(byte[] key) throws IOException{
		ShardedJedis jedis = shardedJedisPool.getResource();
		try {
			byte[] objBytes = jedis.get(key);
			if (objBytes != null) {
				return SerializationUtils.deserialize(objBytes);
			}
		} catch (Exception e) {
			if (null != jedis && e instanceof JedisConnectionException) {
				shardedJedisPool.returnBrokenResource(jedis);
				jedis = null;
			}
			throw new IOException(e);
		} finally {
			if (null != jedis)
				shardedJedisPool.returnResource(jedis);
		}
		return null;
	}
	
	public void saveObject(byte[] key,Serializable obj) throws IOException{
		saveObject(key,obj,-1);
	}
	
	public void saveObject(byte[] key,Serializable obj,int ttlSeconds) throws IOException{
		ShardedJedis jedis = shardedJedisPool.getResource();
		try {
			byte[] objectBytes = null;
			if(obj==null){
				objectBytes = new byte[0];
			}else{
				objectBytes = SerializationUtils.serialize(obj);
			}
			jedis.set(key, objectBytes);
			if(ttlSeconds>0){
				jedis.expire(key, ttlSeconds);
			}
		} catch (Exception e) {
			LOG.warn(e, e);
			if (null != jedis && e instanceof JedisConnectionException) {
				shardedJedisPool.returnBrokenResource(jedis);
				jedis = null;
			}
			throw new IOException(e);
		} finally {
			if (null != jedis)
				shardedJedisPool.returnResource(jedis);
		}
	}

	/* (non-Javadoc)
	 * @see com.lenovo.lps.push.marketing.common.cache.IBigCache#isDeviceInfoExists(long)
	 */
	public boolean isDeviceInfoExists(long pid) throws IOException {
		ShardedJedis jedis = shardedJedisPool.getResource();
		try {
			return jedis.exists(KEY_DEVICE.getBinaryKey(Long.toString(pid)));
		} catch (Exception e) {
			LOG.warn(e, e);
			if (null != jedis && e instanceof JedisConnectionException) {
				shardedJedisPool.returnBrokenResource(jedis);
				jedis = null;
			}
			throw new IOException(e);
		} finally {
			if (null != jedis)
				shardedJedisPool.returnResource(jedis);
		}
	}

	/* (non-Javadoc)
	 * @see com.lenovo.lps.push.marketing.common.cache.IBigCache#saveADHit(java.lang.String, long)
	 */
	public void saveADHit(String adid, long pid) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		try {
				jedis.sadd(KEY_AD_PUSHTO.getKey(adid),Long.toString(pid));
		} catch (Exception e) {
			LOG.warn(e, e);
			if (null != jedis && e instanceof JedisConnectionException) {
				shardedJedisPool.returnBrokenResource(jedis);
				jedis = null;
			}
		} finally {
			if (null != jedis) {
				shardedJedisPool.returnResource(jedis);
			}
		}		
	}
	
	public void remove(byte[] key){
		ShardedJedis jedis = shardedJedisPool.getResource();
		try {
			jedis.del(key);
		} catch (Exception e) {
			LOG.warn(e, e);
			if (null != jedis && e instanceof JedisConnectionException) {
				shardedJedisPool.returnBrokenResource(jedis);
				jedis = null;
			}
		} finally {
			if (null != jedis)
				shardedJedisPool.returnResource(jedis);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lenovo.lps.push.marketing.common.cache.IBigCache#getShardedJedisPool()
	 */
	public ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}

	/* (non-Javadoc)
	 * @see com.lenovo.lps.push.marketing.common.cache.IBigCache#setShardedJedisPool(redis.clients.jedis.ShardedJedisPool)
	 */
	public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}

	/* (non-Javadoc)
	 * @see com.lenovo.lps.push.marketing.common.cache.IBigCache#addListItem(long, java.lang.String)
	 */
	public void addListItem(String listid,String listItem)throws IOException{
		ShardedJedis jedis = shardedJedisPool.getResource();
		try {
			jedis.sadd(KEY_LIST.getBinaryKey(listid), listItem.getBytes("UTF-8"));
		} catch (Exception e) {
			LOG.warn(e, e);
			if (null != jedis && e instanceof JedisConnectionException) {
				shardedJedisPool.returnBrokenResource(jedis);
				jedis = null;
			}
			throw new IOException(e);
		} finally {
			if (null != jedis)
				shardedJedisPool.returnResource(jedis);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lenovo.lps.push.marketing.common.cache.IBigCache#isInList(long, java.lang.String)
	 */
	public boolean isInList(String listid,String listItem)throws IOException{
		ShardedJedis jedis = shardedJedisPool.getResource();
		try {
			return jedis.sismember(KEY_LIST.getBinaryKey(listid), listItem.getBytes("UTF-8"));
		} catch (Exception e) {
			LOG.warn(e, e);
			if (null != jedis && e instanceof JedisConnectionException) {
				shardedJedisPool.returnBrokenResource(jedis);
				jedis = null;
			}
			throw new IOException(e);
		} finally {
			if (null != jedis)
				shardedJedisPool.returnResource(jedis);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lenovo.lps.push.marketing.common.cache.IBigCache#removeList(long)
	 */
	public void removeList(String listid) {
		remove(KEY_LIST.getBinaryKey(listid));
	}

	public boolean isListExist(String listid) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		try {
			byte[] key = KEY_LIST.getBinaryKey(listid);
			return jedis.exists(key);
		} catch (Exception e) {
			LOG.warn(e, e);
			if (null != jedis && e instanceof JedisConnectionException) {
				shardedJedisPool.returnBrokenResource(jedis);
				jedis = null;
			}
			return false;
		} finally {
			if (null != jedis)
				shardedJedisPool.returnResource(jedis);
		}
	}

	public void saveAppInfo(AppInfo appInfo) throws IOException {
		saveObject(
				KEY_APP.getBinaryKey(Long.toString(appInfo.getPid()),appInfo.getSid())
				,appInfo);
	}

	public AppInfo getAppInfo(long pid, String sid) throws IOException {
		byte[] key = KEY_APP.getBinaryKey(Long.toString(pid),sid);
		AppInfo appInfo =  (AppInfo)loadObject(key);
		return appInfo;
	}

	public long cleanAdData(String adid) throws Exception {
		ShardedJedis jedis = shardedJedisPool.getResource();
		String key = KEY_AD_PUSHTO.getKey(adid);
		try {
			long count = jedis.scard(key);
			for(int i=0;i<count;i++){
				String pid = jedis.spop(key);
				if(pid!=null){
					byte[] hitCtxKey = KEY_HIT_CTX.getBinaryKey(pid);
					byte[] objBytes = jedis.get(hitCtxKey);
					if (objBytes != null) {
						UserHitContext hitContext = (UserHitContext)SerializationUtils.deserialize(objBytes);
						if(hitContext!=null && hitContext.getAdPushd()!=null && hitContext.getAdPushd().remove(adid)){
							byte[] objectBytes = SerializationUtils.serialize(hitContext);
							jedis.set(hitCtxKey, objectBytes);
						}
					}else{
						continue;
					}
				}
			}
			jedis.del(key);
			return count;
		} catch (Exception e) {
			if (null != jedis && e instanceof JedisConnectionException) {
				shardedJedisPool.returnBrokenResource(jedis);
				jedis = null;
			}
			throw e;
		} finally {
			if (null != jedis)
				shardedJedisPool.returnResource(jedis);
		}
	}

	public long incrGoalCount(String adid) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		try {
			String key = KEY_GOAL_COUNT.getKey(adid);
			long cnt = jedis.incr(key);
			if(cnt<2){
				jedis.expireAt(key,PushConsts.TIMEMILLIS_1WEEK*8);
			}
			return cnt;
		} catch (Exception e) {
			LOG.warn(e, e);
			if (null != jedis && e instanceof JedisConnectionException) {
				shardedJedisPool.returnBrokenResource(jedis);
				jedis = null;
			}
			return 0;
		} finally {
			if (null != jedis)
				shardedJedisPool.returnResource(jedis);
		}
	}

	public DevicePushContext getDevicePushContext(long pid) throws IOException {
		byte[] key = KEY_PUSH_CTX.getBinaryKey(Long.toString(pid));
		DevicePushContext pushContext =  (DevicePushContext)loadObject(key);
		return pushContext;
	}

	public void saveDevicePushContext(DevicePushContext devicePushContext)
			throws IOException {
		saveObject(
				KEY_PUSH_CTX.getBinaryKey(Long.toString(devicePushContext.getPid()))
				,devicePushContext, PUSHCONTEXT_TTL_SECONDS);
	}

}