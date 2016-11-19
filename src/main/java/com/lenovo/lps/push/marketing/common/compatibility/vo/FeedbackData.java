package com.lenovo.lps.push.marketing.common.compatibility.vo;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO chen (from liuhk2): rename the class name to FeedbackData, keeping the
 * consistency
 * 
 */
public class FeedbackData {
	public String displayMessageIds;
	public String clickMessagesIds;
	public List<AppInstall> downloadApps=new ArrayList<AppInstall>();//data
	public List<AppInstall> appInstalls=new ArrayList<AppInstall>();//data
	public List<AppInstall> engineUpgrades=new ArrayList<AppInstall>();//data
	
	public String getDisplayMessageIds() {
		return displayMessageIds;
	}
	public void setDisplayMessageIds(String displayMessageIds) {
		this.displayMessageIds = displayMessageIds;
	}
	public String getClickMessagesIds() {
		return clickMessagesIds;
	}
	public void setClickMessagesIds(String clickMessagesIds) {
		this.clickMessagesIds = clickMessagesIds;
	}
	public List<AppInstall> getDownloadApps() {
		return downloadApps;
	}
	public void setDownloadApps(List<AppInstall> downloadApps) {
		this.downloadApps = downloadApps;
	}
	public List<AppInstall> getAppInstalls() {
		return appInstalls;
	}
	public void setAppInstalls(List<AppInstall> appInstalls) {
		this.appInstalls = appInstalls;
	}
	public List<AppInstall> getEngineUpgrades() {
		return engineUpgrades;
	}
	public void setEngineUpgrades(List<AppInstall> engineUpgrades) {
		this.engineUpgrades = engineUpgrades;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FeedbackData [displayMessageIds=");
		builder.append(displayMessageIds);
		builder.append(", clickMessagesIds=");
		builder.append(clickMessagesIds);
		builder.append("] \r\n");
		if(downloadApps!=null){
			builder.append("downloadApps:  \r\n");
			for(AppInstall app:downloadApps){
				builder.append("    ").append(app.toString()).append("\r\n");
			}
		}
		if(appInstalls!=null){
			builder.append("appInstalls:  \r\n");
			for(AppInstall app:appInstalls){
				builder.append("    ").append(app.toString()).append("\r\n");
			}
		}
		if(engineUpgrades!=null){
			builder.append("engineUpgrades:  \r\n");
			for(AppInstall app:engineUpgrades){
				builder.append("    ").append(app.toString()).append("\r\n");
			}
		}
		return builder.toString();
	}
}
