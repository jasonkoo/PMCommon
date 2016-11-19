package com.lenovo.lps.push.marketing.common.rpc;

import java.util.List;

import com.lenovo.lps.push.marketing.common.vo.AD;

public interface ADService {
	/**
	 * 取得广告
	 * @param adId
	 * @return
	 * @deprecated
	 */
	public AD getAD(String adId,boolean withPushCondtion);
	
	/**
	 * 取得广告
	 * @param adId
	 * @return
	 */
	public AD getAD(String adId);
	
	/**
	 * 获取当前启动的广告id
	 * @return
	 */
	public List<String> getActiveADIds();
	
	/**
	 * 取得所有的AD对象
	 * @return
	 */
	public List<AD> getAllAds();
	
	/**
	 * 保存AD
	 * @param ad
	 */
	public void saveAD(AD ad);
	
	/**
	 * 判断AD是否存在
	 * @param adid
	 * @return
	 */
	public boolean adExist(String adid);
}