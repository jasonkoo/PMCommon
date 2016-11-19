package com.lenovo.lps.push.marketing.common.conf;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.lenovo.lps.push.marketing.common.util.TimeUtils;
import com.lenovo.lps.push.marketing.common.vo.expression.Condition;

/**
 * Hit模块配置信息
 * @author chenzhao1
 *
 */
public class HitConfig implements Serializable{
	private static final long serialVersionUID = 1899881576600255807L;
	
	private String confId = UUID.randomUUID().toString();
	private long minHitInterval = 3600000;
	private int[] pushHours = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23};
	private Set<Long> testDevices;
	private int userMaxAdsPerDay = 1;
	private Condition[] globalDimConditions;
	private String globalBlacklistId;
	private long minSdkVercode = 403010962;
	
	private transient int lastCheckHour = -1;
	private transient boolean isPushhour = true;
	public static final transient String IS_VALID_OK = "ok";
	
	public boolean isPushTime(){
		if(lastCheckHour!=TimeUtils.getCurrenthour()){
			if(Arrays.binarySearch(pushHours, TimeUtils.getCurrenthour())<0){
				isPushhour = false;
			}else{
				isPushhour = true;
			}
		}
		return isPushhour;
	}
	
	public boolean isTestDevice(long pid){
		if(testDevices!=null){
			return testDevices.contains(pid);
		}
		return false;
	}
	
	public String getConfId() {
		return confId;
	}
	public void setConfId(String confId) {
		this.confId = confId;
	}
	public long getMinHitInterval() {
		return minHitInterval;
	}
	public void setMinHitInterval(long minHitInterval) {
		this.minHitInterval = minHitInterval;
	}
	public int[] getPushHours() {
		return pushHours;
	}
	public void setPushHours(int[] pushHours) {
		this.pushHours = pushHours;
	}
	public int getUserMaxAdsPerDay() {
		return userMaxAdsPerDay;
	}
	public void setUserMaxAdsPerDay(int userMaxAdsPerDay) {
		this.userMaxAdsPerDay = userMaxAdsPerDay;
	}
	public Set<Long> getTestDevices() {
		return testDevices;
	}
	public void setTestDevices(Set<Long> testDevices) {
		this.testDevices = testDevices;
	}

	public Condition[] getGlobalDimConditions() {
		return globalDimConditions;
	}

	public void setGlobalDimConditions(Condition[] globalDimConditions) {
		this.globalDimConditions = globalDimConditions;
	}

	public String getGlobalBlacklistId() {
		return globalBlacklistId;
	}

	public void setGlobalBlacklistId(String globalBlacklistId) {
		this.globalBlacklistId = globalBlacklistId;
	}
	
	public long getMinSdkVercode() {
		return minSdkVercode;
	}

	public void setMinSdkVercode(long minSdkVercode) {
		this.minSdkVercode = minSdkVercode;
	}

	public String isValid(List<String> blackIds) {
		
		if (this.minHitInterval<=0) {
			return "invalid minHitInterval: " + minHitInterval;
		}
		if (this.userMaxAdsPerDay<=0) {
			return "invalid userMaxAdsPerDay: " + userMaxAdsPerDay;
		}
		
		if (pushHours==null || pushHours.length==0) {
			return "empty pushHours";
		}
		for (int i=0;i<pushHours.length;i++){
			if (pushHours[i]<0 || pushHours[i]>23) {
				return "invalid hour: " + pushHours[i];
			}
		}
		
		if (StringUtils.isNotEmpty(globalBlacklistId)) {
			if (blackIds == null || !blackIds.contains(this.globalBlacklistId)) {
				return "invalid globalBlacklistId: " + globalBlacklistId;
			}
		}
		
		return IS_VALID_OK;
	}

}
