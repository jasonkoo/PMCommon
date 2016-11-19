package com.lenovo.lps.push.marketing.common.util;

import com.lenovo.lps.push.marketing.common.util.Consts;

/**
 * @author chenzhao1
 * @deprecated use : com.lenovo.lps.push.marketing.common.cache.BigCache
 */
public class CacheKey {
	private String prfix;
	private int expireSends = -1;
	public CacheKey(String prfix, int expireSends) {
		this.prfix = prfix;
		this.expireSends = expireSends;
	}
	
	public CacheKey(String prfix) {
		super();
		this.prfix = prfix;
	}

	public void setPrfix(String prfix) {
		this.prfix = prfix;
	}
	public int getExpireSends() {
		return expireSends;
	}

	public void setExpireSends(int expireSends) {
		this.expireSends = expireSends;
	}

	public String getKey(String ... keyArgs){
		String key = prfix;
		if(keyArgs!=null && keyArgs.length>0){
			for(int i=0;i<keyArgs.length;i++){
				if(i==0){
					key += keyArgs[i];
				}else{
					key += ("."  + keyArgs[i]);
				}
			}
		}
		return key;
	}
	public byte[] getBinaryKey(String ... keyArgs){
		return getKey(keyArgs).getBytes(Consts.UTF8);
	}
	
}
