package com.lenovo.lps.push.marketing.common.vo;

import java.io.Serializable;

/**
 * 表示一次广告匹配结果
 * @author chenzhao1
 *
 */
/**
 * @author chenzhao1
 *
 */
public class HitResult implements Serializable {
	private static final long serialVersionUID = 8094565901051084079L;
	public static final int SUCCESS = 0;
	public static final int FREQUENT_VISIT = 1;
	public static final int NOT_PUSH_TIME = 2;
	public static final int DISTURBED = 3;
	public static final int NO_SATISFACTORY_AD = 4;
	public static final int NOT_FOUND_USER = 5;
	public static final int PUSH_FAILD = 6;
	public static final int PUSH_SYSTEM_BUSY = 7;
	public static final int AD_FC_FORBID = 8;
	public static final int IN_GLOBAL_BLACKLIST = 9;
	public static final int GLOBAL_CONDITION_MISSMATCH = 10;
	public static final int CACHE_ERROR = 11;
	
	public HitResult(long pid, String ad_id, int result, DeviceInfoVO deviceInfo) {
		super();
		this.pid = pid;
		this.ad_id = ad_id;
		this.result = result;
		this.hitTime = System.currentTimeMillis();
		this.deviceInfo = deviceInfo;
	}
	
	public HitResult() {
	}

	private long pid;
	private String ad_id;
	private int result;
	private long hitTime = System.currentTimeMillis();
	private DeviceInfoVO deviceInfo;
	private boolean todayFirstHit = false;
	
	public long getPid() {
		return pid;
	}
	public void setPid(long pid) {
		this.pid = pid;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public long getHitTime() {
		return hitTime;
	}

	public void setHitTime(long hitTime) {
		this.hitTime = hitTime;
	}

	public String getAd_id() {
		return ad_id;
	}

	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
	}

	public DeviceInfoVO getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(DeviceInfoVO deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	
	public boolean isTodayFirstHit() {
		return todayFirstHit;
	}

	public void setTodayFirstHit(boolean todayFirstHit) {
		this.todayFirstHit = todayFirstHit;
	}

	@Override
	public String toString() {
		return "HitResult [pid=" + pid + ", ad_id=" + ad_id + ", result="
				+ result + ", hitTime=" + hitTime + "]";
	}
}
