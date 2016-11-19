package com.lenovo.lps.push.marketing.common.vo;


public class FeedbackError {
	
	private String adId;
	private String type;
	private String result;
	private String packageName;
	private String targetVersion;
	private long logTime;
	private Long pid;
	
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getTargetVersion() {
		return targetVersion;
	}
	public void setTargetVersion(String targetVersion) {
		this.targetVersion = targetVersion;
	}
	public final static String TYPE_DOWNLOAD_ERROR = "0";
	public final static String TYPE_INSTALL_ERROR = "1";
	
	private DeviceInfoVO deviceInfo;
	
	public String getAdId() {
		return adId;
	}
	public void setAdId(String adId) {
		this.adId = adId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public DeviceInfoVO getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(DeviceInfoVO deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	public long getLogTime() {
		return logTime;
	}
	public void setLogTime(long logTime) {
		this.logTime = logTime;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	
}
