package com.lenovo.lps.push.marketing.common.compatibility.vo;

import java.io.Serializable;

public class AppInstall implements Serializable{
	private static final long serialVersionUID = 3146006662614217211L;
	public String messageFBID;
	public String packageName;
	public String currentVersion;
	public String targetVersion;
	public String result;
	public String errorCode;
	public String getMessageFBID() {
		return messageFBID;
	}
	public void setMessageFBID(String messageFBID) {
		this.messageFBID = messageFBID;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getCurrentVersion() {
		return currentVersion;
	}
	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}
	public String getTargetVersion() {
		return targetVersion;
	}
	public void setTargetVersion(String targetVersion) {
		this.targetVersion = targetVersion;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AppInstall [messageFBID=");
		builder.append(messageFBID);
		builder.append(", packageName=");
		builder.append(packageName);
		builder.append(", currentVersion=");
		builder.append(currentVersion);
		builder.append(", targetVersion=");
		builder.append(targetVersion);
		builder.append(", result=");
		builder.append(result);
		builder.append(", errorCode=");
		builder.append(errorCode);
		builder.append("]");
		return builder.toString();
	}	
}
