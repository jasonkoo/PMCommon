package com.lenovo.lps.push.marketing.common.compatibility.vo;



import org.apache.log4j.Logger;

import com.lenovo.lps.push.marketing.common.compatibility.vo.vtd.PollCommitVTDHandler;
public class PollCommitVO implements XmlSerializable{

	private static final Logger logger = Logger.getLogger(PollCommitVO.class);
	    
	    private Device device;
	    
	    private AppInfo appInfo;
	    
	    private AppInfo channelInfo;
		
		private DynamicData dynamicData;
		
		private DeviceID deviceID;
		
		private FeedbackData feedBackData;
		
		private AppInstall appInstall;
	

		public static PollCommitVO readFrom(String msgsXml)  {
			String xml=String.valueOf(msgsXml);
			PollCommitVO vo=null;
			try{
				vo=PollCommitVTDHandler.parse(msgsXml.getBytes("utf-8"));
			}catch(Exception e){
				logger.warn(e,e);
				logger.warn("PollCommitVTDHandler.parse FAIL!msgsXml["+xml+"]");
			}
			return vo;
		}

		public Device getDevice() {
			return device;
		}

		public void setDevice(Device device) {
			this.device = device;
		}

		public AppInfo getAppInfo() {
			return appInfo;
		}

		public void setAppInfo(AppInfo appInfo) {
			this.appInfo = appInfo;
		}

		public AppInfo getChannelInfo() {
			return channelInfo;
		}

		public void setChannelInfo(AppInfo channelInfo) {
			this.channelInfo = channelInfo;
		}

		public DynamicData getDynamicData() {
			return dynamicData;
		}

		public void setDynamicData(DynamicData dynamicData) {
			this.dynamicData = dynamicData;
		}

		public DeviceID getDeviceID() {
			return deviceID;
		}

		public void setDeviceID(DeviceID deviceID) {
			this.deviceID = deviceID;
		}

		public FeedbackData getFeedBackData() {
			return feedBackData;
		}

		public void setFeedBackData(FeedbackData feedBackData) {
			this.feedBackData = feedBackData;
		}

		public AppInstall getAppInstall() {
			return appInstall;
		}

		public void setAppInstall(AppInstall appInstall) {
			this.appInstall = appInstall;
		}

		public String toXML() {
			return null;
		}
		
		public static void main(String[] args){
			String xml = "<StaData><DynaData><CellInfo><SysID /><LocID /><CellID /><Latitude /><Longitude /></CellInfo><DataAccInfo><NetMode>wifi</NetMode><IP>fe80::d622:3fff:fe6f:314a%wlan0</IP><APN>D8676B</APN><OperCode>46000</OperCode><IMSI>460000691158861</IMSI></DataAccInfo></DynaData><FBData><DisMessages>rinter2_2c91bc5448301e6601487929be9401ef</DisMessages><ClicMessages>rinter2_2c91bc5448301e6601487929be9401ef</ClicMessages><AppInstalls /><AppDownloads /><EngUpgrades /><NacDataList><NacData><MessageFBID>rinter2_2c91bc5448301e6601487929be9401ef</MessageFBID><Result>Success</Result></NacData></NacDataList></FBData></StaData>";
			PollCommitVO vo = readFrom(xml);
			System.out.println(vo);
		}
	}
