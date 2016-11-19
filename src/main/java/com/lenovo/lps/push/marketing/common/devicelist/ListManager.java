package com.lenovo.lps.push.marketing.common.devicelist;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

/**
 * 名单管理器
 * @author chenzhao1
 */
public class ListManager {
	private static final Logger logger = Logger.getLogger(ListManager.class);
	private List<CacheStateListener> cacheStateListeners = new ArrayList<CacheStateListener>();
	private LinkedBlockingQueue<CacheTask> cacheTaskQueue = new LinkedBlockingQueue<CacheTask>();
	private ListCache cache;
	
	/**
	 * 名单管理器构造函数
	 * @param redisHost
	 * @param redisPort
	 */
	public ListManager(String redisHost,int redisPort){
		cache = new ListCache(redisHost,redisPort);
		new Thread("LISTMANAGER-CREATE-CACHE-THREAD"){
			public void run() {
				while(true){
					try {
						CacheTask cacheTask = cacheTaskQueue.take();
						changeCacheState(cacheTask.getListid(), CacheStateListener.LIST_STATE_MAKING_CACHE);
						cacheTask.execute();
						changeCacheState(cacheTask.getListid(), CacheStateListener.LIST_STATE_CACHED);
					} catch (InterruptedException e) {
						logger.warn(e,e);
					}
				}
			}

		}.start();
	}
	
	/**
	 * 添加名单状态监听器
	 * @param cacheStateListener
	 */
	public void addCacheStateListener(CacheStateListener cacheStateListener){
		if(cacheStateListener!=null){
			cacheStateListeners.add(cacheStateListener);
		}
	}
	
	/**
	 * 使用本地文件创建名单缓存
	 * @param deviceIdfile
	 * @param listTTL
	 * @return 名单ID
	 */
	public String createCache(File deviceIdfile,long listTTL){
		if(deviceIdfile ==null  || !deviceIdfile.isFile()){
			throw new IllegalArgumentException("Not a file : " + deviceIdfile);
		}
		String listid = cache.createList(listTTL);
		changeCacheState(listid,CacheStateListener.LIST_STATE_NEW);
		cacheTaskQueue.offer(new CacheTask(deviceIdfile, listid,listTTL,cache));
		return System.currentTimeMillis()+"";
	}
	
	private void changeCacheState(String listid,String cacheState){
		cache.setListCacheState(listid, cacheState);
		for(CacheStateListener listener:cacheStateListeners){
			listener.onListStateChanged(listid, cacheState);
		}
	}
	
	
//	public void uncache(String listId){
//		
//	}
}
