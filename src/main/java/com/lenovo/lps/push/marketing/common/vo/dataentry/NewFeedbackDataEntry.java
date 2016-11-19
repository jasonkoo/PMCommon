package com.lenovo.lps.push.marketing.common.vo.dataentry;

public class NewFeedbackDataEntry  extends UserDataEntry{
	private String pid;
	private String bizType;
	private String eventName;
	private String feedbackId;
	private boolean success;
	private String sid;
	private String errCode;
	private String packName;
	private String currVer;
	private String targetVer;
	private String value;
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(String feedbackId) {
		this.feedbackId = feedbackId;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getPackName() {
		return packName;
	}
	public void setPackName(String packName) {
		this.packName = packName;
	}
	public String getCurrVer() {
		return currVer;
	}
	public void setCurrVer(String currVer) {
		this.currVer = currVer;
	}
	public String getTargetVer() {
		return targetVer;
	}
	public void setTargetVer(String targetVer) {
		this.targetVer = targetVer;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}	
}
