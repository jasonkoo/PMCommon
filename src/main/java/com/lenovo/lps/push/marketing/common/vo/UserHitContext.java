package com.lenovo.lps.push.marketing.common.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Deprecated
public class UserHitContext implements Serializable{
	private static final long serialVersionUID = 9086568344244130133L;
	
	private long pid;
	
	@Deprecated 
	private Set<String> adPushd;
	private long lastVisit = -1;
	@Deprecated
	private long lastUVReport = -1;
//	private long lastPushTime = -1;
	private long todayFirstPushTime = -1;
	private int todayADCount = 0;
	private boolean isTestDevice = false;
	transient Boolean changedFlag;
	transient Boolean skipInrcADCount;
	
	public boolean isAdPushd(String adid){
		if(adPushd==null){
			return false;
		}
		return adPushd.contains(adid);
	}
	
	public void addAdPushd(String adid){
		if(adPushd==null){
			adPushd = new HashSet<String>();
		}
		adPushd.add(adid);
		setChangeFlag();
	}
	
	private void setChangeFlag(){
		changedFlag = true;
	}
	
	public boolean isChanged(){
		if(changedFlag!=null && changedFlag){
			return true;
		}
		return false;
	}
	
	public void setSkipInrcADCount(){
		skipInrcADCount = true;
		setChangeFlag();
	}
	
	public boolean isSkipInrcADCount(){
		if(skipInrcADCount!=null && skipInrcADCount){
			return true;
		}
		return false;
	}
	
	public long getPid() {
		return pid;
	}
	public void setPid(long pid) {
		this.pid = pid;
		setChangeFlag();
	}
	public Set<String> getAdPushd() {
		return adPushd;
	}
	public void setAdPushd(Set<String> adPushd) {
		this.adPushd = adPushd;
		setChangeFlag();
	}
	public long getLastVisit() {
		return lastVisit;
	}
	public void setLastVisit(long lastVisit) {
		this.lastVisit = lastVisit;
		setChangeFlag();
	}
	public long getLastUVReport() {
		return lastUVReport;
	}
	public void setLastUVReport(long lastUVReport) {
		this.lastUVReport = lastUVReport;
		setChangeFlag();
	}
//	public long getLastPushTime() {
//		return lastPushTime;
//	}
//	public void setLastPushTime(long lastPushTime) {
//		this.lastPushTime = lastPushTime;
//		setChangeFlag();
//	}
	public int getTodayADCount() {
		return todayADCount;
	}
	public void setTodayADCount(int todayADCount) {
		this.todayADCount = todayADCount;
		setChangeFlag();
	}
	public boolean isTestDevice() {
		return isTestDevice;
	}
	public void setTestDevice(boolean isTestDevice) {
		this.isTestDevice = isTestDevice;
		setChangeFlag();
	}

	public long getTodayFirstPushTime() {
		return todayFirstPushTime;
	}

	public void setTodayFirstPushTime(long todayFirstPushTime) {
		this.todayFirstPushTime = todayFirstPushTime;
		setChangeFlag();
	}
}
