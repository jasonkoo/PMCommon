package com.lenovo.lps.push.marketing.common.compatibility.vo;
import java.util.ArrayList;
import java.util.List;


public class Device {
	public String deviceModel;
	public String deviceOSVer;
	public String deviceCustVer;
	private String osName="android";
	private AppInfo appInfo;
	private AppInfo channelInfo;
	
	public String mac;
	public String sn;
	public List<DeviceID> deviceIDs=new ArrayList<DeviceID>();

	
	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public AppInfo getAppInfo() {
		return appInfo;
	}

	public void setAppInfo(AppInfo appInfo) {
		this.appInfo = appInfo;
	}

	public String getDeviceOSVer() {
		return deviceOSVer;
	}

	public void setDeviceOSVer(String deviceOSVer) {
		this.deviceOSVer = deviceOSVer;
	}

	public String getDeviceCustVer() {
		return deviceCustVer;
	}

	public void setDeviceCustVer(String deviceCustVer) {
		this.deviceCustVer = deviceCustVer;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	
	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public List<DeviceID> getDeviceIDs() {
		return deviceIDs;
	}

	public void setDeviceIDs(List<DeviceID> deviceIDs) {
		this.deviceIDs = deviceIDs;
	}

	public AppInfo getChannelInfo() {
		return channelInfo;
	}

	public void setChannelInfo(AppInfo channelInfo) {
		this.channelInfo = channelInfo;
	}
	
	
	
}



 