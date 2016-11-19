package com.lenovo.lps.push.marketing.common.compatibility.adapters;


import java.io.UnsupportedEncodingException;
import java.util.Date;

import java_cup.internal_error;

import org.junit.Test;

import com.lenovo.czlib.nodex.conf.ZKProperties;
import com.lenovo.lps.push.common.eventbus.PushEventBus;
import com.lenovo.lps.push.common.eventbus.Topics;
import com.lenovo.lps.push.marketing.common.vo.dataentry.AppDataEntry;
import com.lenovo.lps.push.marketing.common.vo.dataentry.AppFeedbackDataEntry;
import com.lenovo.lps.push.marketing.common.vo.dataentry.DataEntrySerializers;
import com.lenovo.lps.push.marketing.common.vo.dataentry.HitDataEntry;
import com.lenovo.lps.push.marketing.common.vo.dataentry.NewFeedbackDataEntry;

public class DataEntrySerializersTest {
	
	
	
	@Test
	public void testHitDataEntry() throws UnsupportedEncodingException {
		long ts = System.currentTimeMillis();
		HitDataEntry hitDataEntry = new HitDataEntry();
		hitDataEntry.setHitTime(new Date());
		hitDataEntry.setPid("pid-" + ts);
		hitDataEntry.setAdId("adId-" + ts);
		hitDataEntry.setResult("result-" + ts);
		hitDataEntry.setDeviceId("deviceId-" + ts);
		hitDataEntry.setDeviceModel("deviceModel-" + ts);
		
		hitDataEntry.setDate(new Date());
		hitDataEntry.setAccessNum(ts);
		hitDataEntry.setApn("apn-" + ts);
		hitDataEntry.setCellId("cellId-" + ts);
		hitDataEntry.setChannelName("channelName-" + ts);
		hitDataEntry.setChargeStatus("chargeStatus-" + ts);
		hitDataEntry.setCityName("cityName-" + ts);
		hitDataEntry.setCountryCode("countryCode-" + ts);
		hitDataEntry.setCreateDate(new Date());
		hitDataEntry.setCustVersion("custVersion-" + ts);
		hitDataEntry.setDeviceIMSI("deviceIMSI-" + ts);
		hitDataEntry.setDeviceIdType("deviceIdType-" + ts);
		hitDataEntry.setIp("ip-" + ts);
		hitDataEntry.setLatitude("latitude-" + ts);
		hitDataEntry.setLocId("locId-" + ts);
		hitDataEntry.setLongitude("longitude-" + ts);
		hitDataEntry.setModifyDate(new Date());
		hitDataEntry.setNetaccessType("netaccessType-" + ts);
		hitDataEntry.setOperationType("operationType-" + ts);
		hitDataEntry.setOperatorCode("operatorCode-" + ts);
		hitDataEntry.setOsVersion("osVersion-" + ts);
		hitDataEntry.setPePkgName("pePkgName" + ts);
		hitDataEntry.setPeVerCode("peVerCode-" + ts);
		hitDataEntry.setPeVersion("peVersion-" + ts);
		hitDataEntry.setPePollVersion("pePollVersion" + ts);
		hitDataEntry.setSysId("sysId-" + ts);
		hitDataEntry.setArrivalTime(new Date());
		
		
		byte[] data = DataEntrySerializers.HIT_DATAENTRY_SERIALIZER.serialize(hitDataEntry);
		System.out.println(new String(data,"UTF-8"));
	}
	
	@Test
	public void testAppDataEntry() throws UnsupportedEncodingException {
		long ts = System.currentTimeMillis();
		AppDataEntry appDataEntry = new AppDataEntry();
		appDataEntry.setPid("pid-" + ts);
		appDataEntry.setSid("sid-" + ts);
		appDataEntry.setAppVerName("appVerName-" + ts);
		appDataEntry.setAppVerCode("appVerCode-" + ts);
		appDataEntry.setLogTime(new Date());
		appDataEntry.setDeviceId("deviceId-" + ts);
		appDataEntry.setDeviceModel("deviceModel-" + ts);
		
		appDataEntry.setDate(new Date());
		appDataEntry.setAppPkgName("appPkgName-" + ts);
		appDataEntry.setIntegratedMode("integratedMode-" + ts);
		appDataEntry.setEngineWorkMode("engineWorkMode-" + ts);
		appDataEntry.setAccessNum(ts);
		appDataEntry.setApn("apn-" + ts);
		appDataEntry.setCellId("cellId-" + ts);
		appDataEntry.setChannelName("channelName-" + ts);
		appDataEntry.setChargeStatus("chargeStatus-" + ts);
		appDataEntry.setCityName("cityName-" + ts);
		appDataEntry.setCountryCode("countryCode-" + ts);
		appDataEntry.setCreateDate(new Date());
		appDataEntry.setCustVersion("custVersion-" + ts);
		appDataEntry.setDeviceIMSI("deviceIMSI-" + ts);
		appDataEntry.setDeviceIdType("deviceIdType-" + ts);
		appDataEntry.setIp("ip-" + ts);
		appDataEntry.setLatitude("latitude-" + ts);
		appDataEntry.setLocId("locId-" + ts);
		appDataEntry.setLongitude("longitude-" + ts);
		appDataEntry.setModifyDate(new Date());
		appDataEntry.setNetaccessType("netaccessType-" + ts);
		appDataEntry.setOperationType("operationType-" + ts);
		appDataEntry.setOperatorCode("operatorCode-" + ts);
		appDataEntry.setOsVersion("osVersion-" + ts);
		appDataEntry.setPePkgName("pePkgName" + ts);
		appDataEntry.setPeVerCode("peVerCode-" + ts);
		appDataEntry.setPeVersion("peVersion-" + ts);
		appDataEntry.setPePollVersion("pePollVersion" + ts);
		appDataEntry.setSysId("sysId-" + ts);
		appDataEntry.setArrivalTime(new Date());
		
		byte[] data = DataEntrySerializers.APP_DATAENTRY_SERIALIZER.serialize(appDataEntry);
		System.out.println(new String(data,"UTF-8"));
	}
	
	@Test
	public void testAppFBDataEntry() throws UnsupportedEncodingException {
		long ts = System.currentTimeMillis();
		AppFeedbackDataEntry dataEntry = new AppFeedbackDataEntry();
		dataEntry.setPid("pid-" + ts);
		dataEntry.setSid("sid-" + ts);
		dataEntry.setAdId("adId-" + ts);
		dataEntry.setSuccess(true);
		dataEntry.setPackName("packName-" + ts);
		dataEntry.setCustVersion("custVersion-" + ts);
		dataEntry.setEventName("eventName-" + ts);
		dataEntry.setLogTime(new Date());
		dataEntry.setDeviceId("deviceId-" + ts);
		dataEntry.setDeviceModel("deviceModel-" + ts);
		
		dataEntry.setDate(new Date());
		dataEntry.setErrCode("errCode-" + ts);
		dataEntry.setCurrVer("currVer-" + ts);
		dataEntry.setTargetVer("targetVer-" + ts);
		dataEntry.setValue("value-" + ts);
		dataEntry.setAccessNum(ts);
		dataEntry.setApn("apn-" + ts);
		dataEntry.setCellId("cellId-" + ts);
		dataEntry.setChannelName("channelName-" + ts);
		dataEntry.setChargeStatus("chargeStatus-" + ts);
		dataEntry.setCityName("cityName-" + ts);
		dataEntry.setCountryCode("countryCode-" + ts);
		dataEntry.setCreateDate(new Date());
		dataEntry.setCustVersion("custVersion-" + ts);
		dataEntry.setDeviceIMSI("deviceIMSI-" + ts);
		dataEntry.setDeviceIdType("deviceIdType-" + ts);
		dataEntry.setIp("ip-" + ts);
		dataEntry.setLatitude("latitude-" + ts);
		dataEntry.setLocId("locId-" + ts);
		dataEntry.setLongitude("longitude-" + ts);
		dataEntry.setModifyDate(new Date());
		dataEntry.setNetaccessType("netaccessType-" + ts);
		dataEntry.setOperationType("operationType-" + ts);
		dataEntry.setOperatorCode("operatorCode-" + ts);
		dataEntry.setOsVersion("osVersion-" + ts);
		dataEntry.setPePkgName("pePkgName" + ts);
		dataEntry.setPeVerCode("peVerCode-" + ts);
		dataEntry.setPeVersion("peVersion-" + ts);
		dataEntry.setPePollVersion("pePollVersion" + ts);
		dataEntry.setSysId("sysId-" + ts);
		dataEntry.setArrivalTime(new Date());
		
		
		byte[] data = DataEntrySerializers.APPFB_DATAENTRY_SERIALIZER.serialize(dataEntry);
		System.out.println(new String(data,"UTF-8"));
	}
	
	public static final PushEventBus DATA_EVENT_BUS = new PushEventBus(new ZKProperties(new String[] { "/data/eventbus" }, false));
	
	@Test
	public void testNewFeedbackDataEntry() throws UnsupportedEncodingException {
		for (int i = 0; i < 10; i++) {
		long ts = System.currentTimeMillis();
		NewFeedbackDataEntry dataEntry = new NewFeedbackDataEntry();
		dataEntry.setDate(new Date());
		dataEntry.setPid("pid-" + ts);
		dataEntry.setBizType("fake");
		dataEntry.setEventName("eventName-" + ts);
		dataEntry.setFeedbackId("feedbackId-" + ts);
		dataEntry.setSuccess(true);
		dataEntry.setSid("rsys" + ts);
		dataEntry.setErrCode("errCode-" + ts);
		dataEntry.setPackName("packName-" + ts);
		dataEntry.setCurrVer("currVer-" + ts);
		dataEntry.setTargetVer("targetVer-" + ts);
		dataEntry.setValue("value-" + ts);
		byte[] data = DataEntrySerializers.NEWFB_DATAENTRY_SERIALIZER.serialize(dataEntry);
		System.out.println(new String(data,"UTF-8"));
		
		
		DATA_EVENT_BUS.publish(Topics.PUSH_FEEDBACK, data);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		}
		
	}

}
