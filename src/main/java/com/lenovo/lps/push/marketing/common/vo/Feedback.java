package com.lenovo.lps.push.marketing.common.vo;

import java.io.Serializable;

import com.lenovo.lps.push.marketing.common.compatibility.vo.AppInstall;

public class Feedback implements Serializable{
	private static final long serialVersionUID = -5183322633589808108L;
	public static final int ARRIVED = 0;
	public static final int DISPLAYED = 1;
	public static final int CLICKED = 2;
	public static final int DISPLAYED_TWO = 3;
	public static final int CLICKED_TWO = 4;
	public static final int DOWNLOADED = 5;
	public static final int INSTALLED = 6;
	public static final int ACTIVATED = 7;
	public static final int ENGINE_UPGRADE = 8;
	public static final int DISPLAY_INSTALL = 9;
	public static final int EXIST = 10;
	public static final int NAC_DATA = 11;
	
	public static final String AD_ID_USER_INSTALL_APP = "ad_id_user_install_app";
	

	
	private String ad_id;
	/**
	 * 活动ID
	 */
	private String ac_id;
	private long pid;
	private int event_type;
	
	private String ad_type;
	private long log_time;
	// liuhk2: 暂时不用
	public AppInstall appInstall;
	private DeviceInfoVO deviceInfo;
	
	public String getAd_id() {
		return ad_id;
	}

	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public int getEvent_type() {
		return event_type;
	}

	public void setEvent_type(int event_type) {
		this.event_type = event_type;
	}

	public String getAc_id() {
		return ac_id;
	}

	public void setAc_id(String ac_id) {
		this.ac_id = ac_id;
	}

	public String getAd_type() {
		return ad_type;
	}

	public void setAd_type(String ad_type) {
		this.ad_type = ad_type;
	}

	public long getLog_time() {
		return log_time;
	}

	public void setLog_time(long log_time) {
		this.log_time = log_time;
	}

	public DeviceInfoVO getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(DeviceInfoVO deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Feedback [ad_id=");
		builder.append(ad_id);
		builder.append(", ac_id=");
		builder.append(ac_id);
		builder.append(", pid=");
		builder.append(pid);
		builder.append(", event_type=");
		builder.append(event_type);
		builder.append(", ad_type=");
		builder.append(ad_type);
		builder.append(", log_time=");
		builder.append(log_time);
		builder.append(", appInstall=");
		builder.append(appInstall);
		builder.append(", deviceInfo=");
		builder.append(deviceInfo);
		builder.append("]");
		return builder.toString();
	}
}
