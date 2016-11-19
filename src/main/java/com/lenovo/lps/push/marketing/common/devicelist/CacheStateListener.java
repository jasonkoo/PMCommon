package com.lenovo.lps.push.marketing.common.devicelist;

public interface CacheStateListener {
	public static final String LIST_STATE_NEW = "new";
	public static final String LIST_STATE_MAKING_CACHE = "making_cache";
	public static final String LIST_STATE_CACHED = "cached";
	
	public void onListStateChanged(String listid,String state);
}