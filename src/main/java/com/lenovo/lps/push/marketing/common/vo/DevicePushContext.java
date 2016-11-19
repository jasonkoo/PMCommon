package com.lenovo.lps.push.marketing.common.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lenovo.lps.push.common.vo.AppInfo;

public class DevicePushContext implements Serializable{
	private static final long serialVersionUID = -5890372691016738410L;
	
	private long pid;
	private String lastVisitDate;
	private long lastSysADPushTime = -1;
	private long[] lastMatchAdSequence;
	private Set<Long> matchedAdIdxes;
	private String imei;
	private boolean testDevice = false; 
	
	//以下字段不写入Redis,而是每次匹配过程中进行set和get
	private transient boolean changed;
	private transient DeviceInfoVO device;
	private transient Map<String,AppInfo> appinfoMap;
	private transient List<AD> pushedADs;
	private transient int errorCode = -1;
	
	public long getPid() {
		return pid;
	}
	public void setPid(long pid) {
		this.pid = pid;
	}
	public String getLastVisitDate() {
		return lastVisitDate;
	}
	public void setLastVisitDate(String lastVisitDate) {
		if(this.lastVisitDate!=null && !this.lastVisitDate.equals(lastVisitDate)){
			this.changed = true;
		}
		this.lastVisitDate = lastVisitDate;
	}
	public long getLastSysADPushTime() {
		return lastSysADPushTime;
	}
	public void setLastSysADPushTime(long lastSysADPushTime) {
		if(this.lastSysADPushTime != lastSysADPushTime){
			this.changed = true;
		}
		this.lastSysADPushTime = lastSysADPushTime;
	}
	public long[] getLastMatchAdSequence() {
		return lastMatchAdSequence;
	}
	public void setLastMatchAdSequence(long[] lastMatchAdSequence) {
		this.changed = true;
		this.lastMatchAdSequence = lastMatchAdSequence;
	}
	public Set<Long> getMatchedAdIdxes() {
		return matchedAdIdxes;
	}
	public void addMatchedAdIdxes(long matchedAdIdx) {
		if(matchedAdIdxes==null){
			matchedAdIdxes = new HashSet<Long>();
		}
		this.changed = true;
		matchedAdIdxes.add(matchedAdIdx);
	}
	public boolean isTestDevice() {
		return testDevice;
	}
	public void setTestDevice(boolean testDevice) {
		this.changed = true;
		this.testDevice = testDevice;
	}
	public String getImei() {
		this.changed = true;
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public boolean isChanged() {
		return changed;
	}
	public void setChanged() {
		this.changed = true;
	}
	public DeviceInfoVO getDevice() {
		return device;
	}
	public void setDevice(DeviceInfoVO device) {
		this.device = device;
	}
	public List<AD> getPushedADs() {
		return pushedADs;
	}
	public void setPushedADs(List<AD> pushedADs) {
		this.pushedADs = pushedADs;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public AppInfo getAppInfo(String sid){
		if(appinfoMap!=null){
			return appinfoMap.get(sid);
		}
		return null;
	}
	public void setAppInfo(AppInfo app) {
		if(app==null){
			return;
		}
		if(appinfoMap==null){
			appinfoMap = new HashMap<String,AppInfo>();
		}
		appinfoMap.put(app.getSid(), app);
	}
	public void removeMatchedAd(Long idx) {
		if(matchedAdIdxes!=null){
			this.changed = true;
			matchedAdIdxes.remove(idx);
		}
	}	
}
