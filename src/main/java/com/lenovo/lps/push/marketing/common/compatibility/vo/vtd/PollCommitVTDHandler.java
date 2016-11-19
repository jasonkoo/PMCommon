package com.lenovo.lps.push.marketing.common.compatibility.vo.vtd;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.lenovo.lps.push.marketing.common.compatibility.vo.AppInfo;
import com.lenovo.lps.push.marketing.common.compatibility.vo.AppInstall;
import com.lenovo.lps.push.marketing.common.compatibility.vo.Device;
import com.lenovo.lps.push.marketing.common.compatibility.vo.DeviceID;
import com.lenovo.lps.push.marketing.common.compatibility.vo.DynamicData;
import com.lenovo.lps.push.marketing.common.compatibility.vo.FeedbackData;
import com.lenovo.lps.push.marketing.common.compatibility.vo.PollCommitVO;
import com.lenovo.lps.push.marketing.common.compatibility.vo.DynamicData.CellInfo;
import com.lenovo.lps.push.marketing.common.compatibility.vo.DynamicData.DataAccessInfo;
import com.lenovo.lps.push.marketing.common.compatibility.vo.DynamicData.StateChange;
import com.ximpleware.AutoPilot;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;

public final class PollCommitVTDHandler  {
	private PollCommitVTDHandler(){
		
	}
	
	private static final String TAG_STADATA = "StaData";

	private static final String TAG_DEVICE = "Device";

	private static final String TAG_DEVMOD = "DevMod";

	private static final String TAG_DEVVER = "DevVer";
	
	private static final String TAG_OSVER = "OSVer";
	
	private static final String TAG_CUSTVER = "CustVer";
	
	private static final String TAG_OSNAME = "OSName";
	
	private static final String TAG_APPINFO = "AppInfo";
	
	private static final String TAG_CHANNELINFO = "ChannelInfo";
	
	private static final String TAG_VERNAME = "VerName";
	
	private static final String TAG_VERCODE = "VerCode";

	private static final String TAG_PKGNAME = "PkgName";
	
	private static final String TAG_MAC = "MAC";

	private static final String TAG_SN = "SN";
	
	private static final String TAG_DEVIDS = "DevIDs";
	
	private static final String TAG_DEVID = "DevID";

	private static final String TAG_DEVSTAND = "DevStand";
	
	private static final String TAG_IMEI = "IMEI";
	
	private static final String TAG_DYNADATA = "DynaData";
	
	private static final String TAG_CELLINFO = "CellInfo";

	private static final String TAG_SYSID = "SysID";

	private static final String TAG_LOCID = "LocID";

	private static final String TAG_CELLID = "CellID";
	
	private static final String TAG_LATITUDE= "Latitude";

	private static final String TAG_LONGITUDE = "Longitude";

	private static final String TAG_DATAACCINFO = "DataAccInfo";
	
	private static final String TAG_NETMODE = "NetMode";

	private static final String TAG_IP = "IP";
	
	private static final String TAG_APN = "APN";
	
	private static final String TAG_OPERCODE= "OperCode";

	private static final String TAG_IMSI = "IMSI";
	
	private static final String TAG_STATECHARGE = "StateCharge";
	
	private static final String TAG_FBDATA = "FBData";
	
	private static final String TAG_DISMESSAGES = "DisMessages";
	private static final String TAG_CLICMESSAGES = "ClicMessages";
	
	private static final String TAG_APPINSTALLS = "AppInstalls";
	private static final String TAG_APP = "App";
	
	private static final String TAG_MESSAGEFBID = "MessageFBID";
	private static final String TAG_PACKNAME = "PackName";
	private static final String TAG_CURRVER = "CurrVer";
	private static final String TAG_TARGETVER = "TargetVer";
	private static final String TAG_RESULT = "Result";
	private static final String TAG_ERRCODE = "ErrCode";
	
	private static final String TAG_APPDOWNLOADS = "AppDownloads";
	private static final String TAG_DOWNLOADAPP = "DownloadApp";
	
	private static final String TAG_ENGUPGRADES = "EngUpgrades";
//	private static final String TAG_UPGRADE = "Upgrade";
	
	private static final String RESULT_TEXT = "Success";

//	static PollCommitVO pollCommitVO =null;
//	static Device device=null;
//	static DynamicData dynamicData=null;
//	static CellInfo cellInfo=null;
//	static DataAccessInfo dataAccessInfo=null;
//	static StateChange stateChange=null;
//	
//	static FeedBackData feedBackData=null;
//	
//	static AppInfo appInfo=null;
//	static AppInfo channelInfo=null;
//	static List<DeviceID> deviceIDs=null;
//	static DeviceID deviceID=null;
//	
//	
//	static boolean APPINFO_TAG_FLAG=false;
//	static boolean CHANNELINFO_TAG_FLAG=false;
//	
//	static boolean APPINSTALLS_TAG_FLAG=false;
//	static boolean ENGUPGRADES_TAG_FLAG=false;
//	
//	static boolean APPDOWNLOADS_TAG_FLAG=false;
//	
//	static List<AppInstall> appInstalls=null;//data
//	static AppInstall appInstall=null;
//	static List<AppInstall> engineUpgrades=null;//data
//	static AppInstall engineUpgrade=null;
//	
//	static List<AppInstall> appDownloads=null;//data  
//	static AppInstall downloadApp=null;
	
	public static PollCommitVO parse(byte[] msgsBytes) throws Exception {
		
		//申明临时解析对象-开始
		PollCommitVO pollCommitVO =null;
		Device device=null;
		DynamicData dynamicData=null;
		CellInfo cellInfo=null;
		DataAccessInfo dataAccessInfo=null;
		StateChange stateChange=null;
		
		FeedbackData feedBackData=null;
		
		AppInfo appInfo=null;
		AppInfo channelInfo=null;
		List<DeviceID> deviceIDs=null;
		DeviceID deviceID=null;
		
		
		boolean APPINFO_TAG_FLAG=false;
		boolean CHANNELINFO_TAG_FLAG=false;
		
		boolean APPINSTALLS_TAG_FLAG=false;
		boolean ENGUPGRADES_TAG_FLAG=false;
		
		boolean APPDOWNLOADS_TAG_FLAG=false;
		
		List<AppInstall> appInstalls=null;//data
		AppInstall appInstall=null;
		List<AppInstall> engineUpgrades=null;//data
		AppInstall engineUpgrade=null;
		
		List<AppInstall> appDownloads=null;//data  
		AppInstall downloadApp=null;
		//申明临时解析对象-结束
		
		VTDGen vg = new VTDGen();
		vg.setDoc(msgsBytes);
		vg.parse(false);  			// set namespace awareness to true
		VTDNav vn = vg.getNav();
		AutoPilot ap = new AutoPilot(vn);
		ap.selectElement("*");
		String currentTag = null;
		
//		boolean DEVICE_TAG_FLAG=false;
//		boolean DYNADATA_TAG_FLAG=false;
//		boolean FBDATA_TAG_FLAG=false;
		
		//循环解析对象属性
		while(ap.iterate()){
			currentTag = vn.toString(vn.getCurrentIndex());
			if(TAG_STADATA.equals(currentTag)) {
				pollCommitVO = new PollCommitVO();
			}else if(TAG_DEVICE.equals(currentTag)){

				device=new Device();
				pollCommitVO.setDevice(device);
				
			}else if(TAG_APPINFO.equals(currentTag)){
				APPINFO_TAG_FLAG=true;
				appInfo=new AppInfo();
				pollCommitVO.setAppInfo(appInfo);
				
			}else if(TAG_CHANNELINFO.equals(currentTag)){
				APPINFO_TAG_FLAG=false; 
				CHANNELINFO_TAG_FLAG=true;
				channelInfo=new AppInfo();
				pollCommitVO.setChannelInfo(channelInfo);
				
			}else if(TAG_DEVIDS.equals(currentTag)){
				deviceIDs=new ArrayList<DeviceID>();
				pollCommitVO.setAppInfo(appInfo);
				pollCommitVO.setChannelInfo(channelInfo);
				
			}else if(TAG_DEVID.equals(currentTag)){
				deviceID=new DeviceID();
				deviceIDs.add(deviceID);
				device.setDeviceIDs(deviceIDs);
				
			}else if(TAG_DYNADATA.equals(currentTag)){
				
				dynamicData=new DynamicData();
				pollCommitVO.setDynamicData(dynamicData);
				
			}else if(TAG_CELLINFO.equals(currentTag)){
				cellInfo=new CellInfo();
				dynamicData.setCellInfo(cellInfo);
				
			}else if(TAG_DATAACCINFO.equals(currentTag)){
				dataAccessInfo=new DataAccessInfo();
				dynamicData.setDataAccessInfo(dataAccessInfo);
				
//			}else if(TAG_STATECHARGE.equals(currentTag)){
//				logger.debug("start parse poll log::TAG_STATECHARGE.equals(currentTag)["+currentTag+"]");
//				stateChange=new StateChange();
//				dynamicData.setStateChange(stateChange);
				
			}else if(TAG_FBDATA.equals(currentTag)){
				
				feedBackData=new FeedbackData();
				pollCommitVO.setFeedBackData(feedBackData);
				
			}else if(TAG_APPINSTALLS.equals(currentTag)){
				APPINSTALLS_TAG_FLAG=true;
				APPDOWNLOADS_TAG_FLAG=false;
				ENGUPGRADES_TAG_FLAG=false;
				
				appInstalls=new ArrayList<AppInstall>();
				feedBackData.setAppInstalls(appInstalls);
				
			}else if(TAG_APP.equals(currentTag)){
				
				appInstall=new AppInstall();
				appInstalls.add(appInstall); 
				
			}else if(TAG_APPDOWNLOADS.equals(currentTag)){
				APPINSTALLS_TAG_FLAG=false;
				APPDOWNLOADS_TAG_FLAG=true;
				ENGUPGRADES_TAG_FLAG=false;
				
				appDownloads=new ArrayList<AppInstall>();
				feedBackData.setDownloadApps(appDownloads);
				
			}else if(TAG_DOWNLOADAPP.equals(currentTag)){
				
				downloadApp=new AppInstall();
				appDownloads.add(downloadApp);
				
			}else if(TAG_ENGUPGRADES.equals(currentTag)){
				APPINSTALLS_TAG_FLAG=false;
				APPDOWNLOADS_TAG_FLAG=false;
				ENGUPGRADES_TAG_FLAG=true;
				
				engineUpgrades=new ArrayList<AppInstall>();
				feedBackData.setEngineUpgrades(engineUpgrades);
				
			}else if(TAG_APP.equals(currentTag)){
				engineUpgrade=new AppInstall();   
				engineUpgrades.add(engineUpgrade);
			}else{
				int t = vn.getText(); // get the index of the text (char data or CDATA)
				if (t!=-1){
					String value = vn.toString(t);
					//doText(pollCommitVO,currentTag,value);
					
					if (currentTag.equals(TAG_DEVMOD)) {
						device.setDeviceModel(value);
					} else if (currentTag.equals(TAG_DEVVER)) {
						//
					} else if (currentTag.equals(TAG_OSVER)) {
						device.setDeviceOSVer(value);
					} else if (currentTag.equals(TAG_CUSTVER)) {
						device.setDeviceCustVer(value);
					} else if (currentTag.equals(TAG_OSNAME)) {
						device.setOsName(value);
					} else if (currentTag.equals(TAG_MAC)) {
						device.setMac(value);
					} else if (currentTag.equals(TAG_SN)) {
						device.setSn(value);
					} else if (currentTag.equals(TAG_DEVIDS)) {
						//
					} else if (currentTag.equals(TAG_DEVID)) {
						//
					} else if (currentTag.equals(TAG_DEVSTAND)) {
						deviceID.setDevStand(value);
					} else if (currentTag.equals(TAG_IMEI)) {
						deviceID.setImei(value);
					} else if (currentTag.equals(TAG_APPINFO)) {
						//pollCommitVO.getDevice().setAppInfo(appInfo);
					} else if ((APPINFO_TAG_FLAG) && currentTag.equals(TAG_VERNAME)) {
						appInfo.setVerName(value);
					} else if ((APPINFO_TAG_FLAG) && currentTag.equals(TAG_VERCODE)) {
						appInfo.setVerCode(value);
					} else if ((APPINFO_TAG_FLAG) && currentTag.equals(TAG_PKGNAME)) {
						appInfo.setPkgName(value);
					} else if ((CHANNELINFO_TAG_FLAG) && currentTag.equals(TAG_VERNAME)) {
						channelInfo.setVerName(value);
					} else if ((CHANNELINFO_TAG_FLAG) && currentTag.equals(TAG_VERCODE)) {
						channelInfo.setVerCode(value);
					} else if ((CHANNELINFO_TAG_FLAG) && currentTag.equals(TAG_PKGNAME)) {
						channelInfo.setPkgName(value);
						
					}else if (currentTag.equals(TAG_SYSID)) {
						cellInfo.setSystemID(value);
					} else if (currentTag.equals(TAG_LOCID)) {
						cellInfo.setLocationID(value);
					} else if (currentTag.equals(TAG_CELLID)) {
						cellInfo.setCellID(value);
					} else if (currentTag.equals(TAG_LATITUDE)) {
						cellInfo.setLatitude(value);
					} else if (currentTag.equals(TAG_LONGITUDE)) {
						cellInfo.setLongitude(value);
					} else if (currentTag.equals(TAG_NETMODE)) {
						dataAccessInfo.setNetworkMode(value);
					} else if (currentTag.equals(TAG_IP)) {
						dataAccessInfo.setIp(value);
					} else if (currentTag.equals(TAG_APN)) {
						dataAccessInfo.setApn(value);
					} else if (currentTag.equals(TAG_OPERCODE)) {
						dataAccessInfo.setOperatorCode(value);
					} else if (currentTag.equals(TAG_IMSI)) {
						dataAccessInfo.setImsi(value);
					} else if (currentTag.equals(TAG_STATECHARGE)) {
						//<StaData><DynaData><StateCharge>true</StateCharge></DynaData></StaData> 
						stateChange=new StateChange();
						if("true".equals(value)){
							stateChange.setBatteryState(true);
						}else{
							stateChange.setBatteryState(false);
						}
						dynamicData.setStateChange(stateChange);
					} else if (currentTag.equals(TAG_DISMESSAGES)&&feedBackData!=null) {
						feedBackData.setDisplayMessageIds(value);
					} else if (currentTag.equals(TAG_CLICMESSAGES)&&feedBackData!=null) {
						feedBackData.setClickMessagesIds(value);
					} else if ((APPINSTALLS_TAG_FLAG)&&currentTag.equals(TAG_MESSAGEFBID)&&appInstall!=null) {
						appInstall.setMessageFBID(value);
					} else if ((APPINSTALLS_TAG_FLAG)&&currentTag.equals(TAG_PACKNAME)&&appInstall!=null) {
						appInstall.setPackageName(value);
					} else if ((APPINSTALLS_TAG_FLAG)&&currentTag.equals(TAG_CURRVER)&&appInstall!=null) {
						appInstall.setCurrentVersion(value);
					} else if ((APPINSTALLS_TAG_FLAG)&&currentTag.equals(TAG_TARGETVER)&&appInstall!=null) {
						appInstall.setTargetVersion(value);
					} else if ((APPINSTALLS_TAG_FLAG)&&currentTag.equals(TAG_RESULT)&&appInstall!=null) {
						appInstall.setResult(value);
					} else if ((APPINSTALLS_TAG_FLAG)&&currentTag.equals(TAG_ERRCODE)&&appInstall!=null) {
						appInstall.setErrorCode(value);
					} else if((APPDOWNLOADS_TAG_FLAG)&&currentTag.equals(TAG_PACKNAME)&&downloadApp!=null){
						downloadApp.setPackageName(value);
					} else if((APPDOWNLOADS_TAG_FLAG)&&currentTag.equals(TAG_CURRVER)&&downloadApp!=null){
						downloadApp.setCurrentVersion(value);
					} else if((APPDOWNLOADS_TAG_FLAG)&&currentTag.equals(TAG_MESSAGEFBID)&&downloadApp!=null){
						downloadApp.setMessageFBID(value);
					} else if((APPDOWNLOADS_TAG_FLAG)&&currentTag.equals(TAG_RESULT)&&downloadApp!=null){
						downloadApp.setResult(value);
					} else if ((ENGUPGRADES_TAG_FLAG)&&currentTag.equals(TAG_MESSAGEFBID)&&engineUpgrade!=null) {
						engineUpgrade.setMessageFBID(value);
					} else if ((ENGUPGRADES_TAG_FLAG)&&currentTag.equals(TAG_PACKNAME)&&engineUpgrade!=null) {
						engineUpgrade.setPackageName(value);
					} else if ((ENGUPGRADES_TAG_FLAG)&&currentTag.equals(TAG_CURRVER)&&engineUpgrade!=null) {
						engineUpgrade.setCurrentVersion(value);
					} else if ((ENGUPGRADES_TAG_FLAG)&&currentTag.equals(TAG_TARGETVER)&&engineUpgrade!=null) {
						engineUpgrade.setTargetVersion(value);
					} else if ((ENGUPGRADES_TAG_FLAG)&&currentTag.equals(TAG_RESULT)&&engineUpgrade!=null) {
						engineUpgrade.setResult(value);
					} else if ((ENGUPGRADES_TAG_FLAG)&&currentTag.equals(TAG_ERRCODE)&&engineUpgrade!=null) {
						engineUpgrade.setErrorCode(value);
					} 
				}
			}			
		}
		
//		if (null != pollCommitVO) {
//			FeedbackData feed = pollCommitVO.getFeedBackData();
//			if (null != feed) {
//				List<AppInstall> downloadApps = feed.getDownloadApps();
//				List<AppInstall> installApps = feed.getAppInstalls();
//				if (null != downloadApps && downloadApps.size() > 0) {
//					for (AppInstall item : downloadApps) {
//						if (StringUtils.isNotBlank(item.getResult()) && !RESULT_TEXT.equals(item.getResult())) {
//							item.setMessageFBID(item.getMessageFBID() + "_" + item.getResult());
//						}
//					}
//				}
//				if (null != installApps && installApps.size() > 0) {
//					for (AppInstall item : installApps) {
//						if (StringUtils.isNotBlank(item.getResult()) && !RESULT_TEXT.equals(item.getResult())) {
//							item.setMessageFBID(item.getMessageFBID() + "_" + item.getResult());
//						}
//					}
//				}
//			}
//		}
		return pollCommitVO;
	}
	
	protected static void doText(PollCommitVO pollCommitVO,final String tag,final String value) {
		/*
		if (tag.equals(TAG_DEVMOD)) {
			device.setDeviceModel(value);
		} else if (tag.equals(TAG_DEVVER)) {
			//
		} else if (tag.equals(TAG_OSVER)) {
			device.setDeviceOSVer(value);
		} else if (tag.equals(TAG_CUSTVER)) {
			device.setDeviceCustVer(value);
		} else if (tag.equals(TAG_OSNAME)) {
			device.setOsName(value);
		} else if (tag.equals(TAG_MAC)) {
			device.setMac(value);
		} else if (tag.equals(TAG_SN)) {
			device.setSn(value);
		} else if (tag.equals(TAG_DEVIDS)) {
			//
		} else if (tag.equals(TAG_DEVID)) {
			//
		} else if (tag.equals(TAG_DEVSTAND)) {
			deviceID.setDevStand(value);
		} else if (tag.equals(TAG_IMEI)) {
			deviceID.setImei(value);
		} else if (tag.equals(TAG_APPINFO)) {
			//pollCommitVO.getDevice().setAppInfo(appInfo);
		} else if ((APPINFO_TAG_FLAG) && tag.equals(TAG_VERNAME)) {
			appInfo.setVerName(value);
		} else if ((APPINFO_TAG_FLAG) && tag.equals(TAG_VERCODE)) {
			appInfo.setVerCode(value);
		} else if ((APPINFO_TAG_FLAG) && tag.equals(TAG_PKGNAME)) {
			appInfo.setPkgName(value);
		} else if ((CHANNELINFO_TAG_FLAG) && tag.equals(TAG_VERNAME)) {
			channelInfo.setVerName(value);
		} else if ((CHANNELINFO_TAG_FLAG) && tag.equals(TAG_VERCODE)) {
			channelInfo.setVerCode(value);
		} else if ((CHANNELINFO_TAG_FLAG) && tag.equals(TAG_PKGNAME)) {
			channelInfo.setPkgName(value);
			
		}else if (tag.equals(TAG_SYSID)) {
			cellInfo.setSystemID(value);
		} else if (tag.equals(TAG_LOCID)) {
			cellInfo.setLocationID(value);
		} else if (tag.equals(TAG_CELLID)) {
			cellInfo.setCellID(value);
		} else if (tag.equals(TAG_LATITUDE)) {
			cellInfo.setLatitude(value);
		} else if (tag.equals(TAG_LONGITUDE)) {
			cellInfo.setLongitude(value);
		} else if (tag.equals(TAG_NETMODE)) {
			dataAccessInfo.setNetworkMode(value);
		} else if (tag.equals(TAG_IP)) {
			dataAccessInfo.setIp(value);
		} else if (tag.equals(TAG_APN)) {
			logger.debug("start parse poll log::tag.equals(TAG_APN) value["+value+"]");
			dataAccessInfo.setApn(value);
		} else if (tag.equals(TAG_OPERCODE)) {
			dataAccessInfo.setOperatorCode(value);
		} else if (tag.equals(TAG_IMSI)) {
			dataAccessInfo.setImsi(value);
		} else if (tag.equals(TAG_STATECHARGE)) {
			//<StaData><DynaData><StateCharge>true</StateCharge></DynaData></StaData> 
			logger.debug("start parse poll log::tag.equals(TAG_STATECHARGE) value["+value+"]");
			stateChange=new StateChange();
			if("true".equals(value)){
				stateChange.setBatteryState(true);
			}else{
				stateChange.setBatteryState(false);
			}
			dynamicData.setStateChange(stateChange);
		} else if (tag.equals(TAG_DISMESSAGES)) {
			feedBackData.setDisplayMessageIds(value);
		} else if (tag.equals(TAG_CLICMESSAGES)) {
			feedBackData.setClickMessagesIds(value);
		} else if ((APPINSTALLS_TAG_FLAG)&&tag.equals(TAG_MESSAGEFBID)) {
			appInstall.setMessageFBID(value);
		} else if ((APPINSTALLS_TAG_FLAG)&&tag.equals(TAG_PACKNAME)) {
			appInstall.setPackageName(value);
		} else if ((APPINSTALLS_TAG_FLAG)&&tag.equals(TAG_CURRVER)) {
			appInstall.setCurrentVersion(value);
		} else if ((APPINSTALLS_TAG_FLAG)&&tag.equals(TAG_TARGETVER)) {
			appInstall.setTargetVersion(value);
		} else if ((APPINSTALLS_TAG_FLAG)&&tag.equals(TAG_RESULT)) {
			appInstall.setResult(value);
		} else if ((APPINSTALLS_TAG_FLAG)&&tag.equals(TAG_ERRCODE)) {
			appInstall.setErrorCode(value);
		} else if((APPDOWNLOADS_TAG_FLAG)&&tag.equals(TAG_PACKNAME)){
			downloadApp.setPackageName(value);
		} else if((APPDOWNLOADS_TAG_FLAG)&&tag.equals(TAG_CURRVER)){
			downloadApp.setCurrentVersion(value);
		} else if((APPDOWNLOADS_TAG_FLAG)&&tag.equals(TAG_MESSAGEFBID)){
			downloadApp.setMessageFBID(value);
		} else if ((ENGUPGRADES_TAG_FLAG)&&tag.equals(TAG_MESSAGEFBID)) {
			engineUpgrade.setMessageFBID(value);
		} else if ((ENGUPGRADES_TAG_FLAG)&&tag.equals(TAG_PACKNAME)) {
			engineUpgrade.setPackageName(value);
		} else if ((ENGUPGRADES_TAG_FLAG)&&tag.equals(TAG_CURRVER)) {
			engineUpgrade.setCurrentVersion(value);
		} else if ((ENGUPGRADES_TAG_FLAG)&&tag.equals(TAG_TARGETVER)) {
			engineUpgrade.setTargetVersion(value);
		} else if ((ENGUPGRADES_TAG_FLAG)&&tag.equals(TAG_RESULT)) {
			engineUpgrade.setResult(value);
		} else if ((ENGUPGRADES_TAG_FLAG)&&tag.equals(TAG_ERRCODE)) {
			engineUpgrade.setErrorCode(value);
		} 
		*/
	}
	
//	public static void main(String[] args){
//		
//		String xx="<StaData><Device><DevMod>Lenovo_S680</DevMod><DevVer><OSVer>4.0.3</OSVer><CustVer>S-3-02</CustVer></DevVer><MAC /><OSName>android</OSName><AppInfo><VerName>1.1</VerName><VerCode>11</VerCode><PkgName>com.lenovo.leos.pushdemo1</PkgName></AppInfo><SN>201201</SN><DevIDs><DevID><DevStand>GSM</DevStand><IMEI>860904010014928</IMEI></DevID></DevIDs></Device><DynaData><CellInfo><SysID>46001</SysID><LocID>41208</LocID><CellID>8960717</CellID><Latitude /><Longitude /></CellInfo><DataAccInfo><NetMode>3G</NetMode><IP>fe80::fb5a:8863:c745:501b%rmnet0</IP><APN>uninet</APN><OperCode>46001</OperCode><IMSI>460010950606413</IMSI></DataAccInfo><StateCharge /></DynaData></StaData>";
//		
//		String xx2="<StaData><Device><DevMod>Lenovo_S680</DevMod><DevVer><OSVer>4.0.3</OSVer><CustVer>S-3-02</CustVer></DevVer><MAC /><OSName>android</OSName><AppInfo><VerName>1.1</VerName><VerCode>11</VerCode><PkgName>com.lenovo.leos.pushdemo1</PkgName></AppInfo><SN>201201</SN><DevIDs><DevID><DevStand>GSM</DevStand><IMEI>860904010014928</IMEI></DevID></DevIDs></Device><DynaData><CellInfo><SysID>46001</SysID><LocID>41208</LocID><CellID>8960717</CellID><Latitude /><Longitude /></CellInfo><DataAccInfo><NetMode>3G</NetMode><IP>fe80::fb5a:8863:c745:501b%rmnet0</IP><APN>uninet</APN><OperCode>46001</OperCode><IMSI>460010950606413</IMSI></DataAccInfo><StateCharge>true</StateCharge></DynaData></StaData>";
//		
//		try {
//			PollCommitVO vo = PollCommitVO.readFrom(xx);
//			
//			// System.out.println("VO getDeviceModel "+vo.getDevice().getDeviceModel());
//			
//			// System.out.println("VO getDeviceOSVer "+vo.getDevice().getDeviceOSVer());
//			// System.out.println("VO getDeviceCustVer "+vo.getDevice().getDeviceCustVer());
//			
//			// System.out.println("VO getMac "+vo.getDevice().getMac());
//			// System.out.println("VO getOsName "+vo.getDevice().getOsName());
//			// System.out.println("VO getSn "+vo.getDevice().getSn());
//			
//			// System.out.println("VO getVerName "+vo.getAppInfo().getVerName());
//			// System.out.println("VO getVerCode "+vo.getAppInfo().getVerCode());
//			// System.out.println("VO getPkgName "+vo.getAppInfo().getPkgName());
//			
//			// System.out.println("VO getDeviceIDs().get(0).getDevStand "+vo.getDevice().getDeviceIDs().get(0).getDevStand());
//			// System.out.println("VO getDeviceIDs().get(0).getImei "+vo.getDevice().getDeviceIDs().get(0).getImei());
//			// System.out.println("VO getDeviceIDs().get(0).getSim "+vo.getDevice().getDeviceIDs().get(0).getSim());
//			
//			// System.out.println("VO getDataAccessInfo().getNetworkMode "+vo.getDynamicData().getDataAccessInfo().getNetworkMode());
//			// System.out.println("VO getDataAccessInfo().getIp "+vo.getDynamicData().getDataAccessInfo().getIp());
//			// System.out.println("VO getDataAccessInfo().getApn "+vo.getDynamicData().getDataAccessInfo().getApn());
//			// System.out.println("VO getDataAccessInfo().getOperatorCode "+vo.getDynamicData().getDataAccessInfo().getOperatorCode());
//			// System.out.println("VO getDataAccessInfo().getImsi "+vo.getDynamicData().getDataAccessInfo().getImsi());
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		
//		
//	}
}
