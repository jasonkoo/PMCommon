package com.lenovo.lps.push.marketing.common.vo.dataentry;

import com.lenovo.lps.push.common.eventbus.serializer.impl.FieldFormator;
import com.lenovo.lps.push.common.eventbus.serializer.impl.HiveRecordSerializer;
import com.lenovo.lps.push.marketing.common.util.TimeUtils;

public class DataEntrySerializers {
	public static final HiveRecordSerializer<HitDataEntry> HIT_DATAENTRY_SERIALIZER = new HiveRecordSerializer<HitDataEntry>("pushmarketing.hit","hitTime",new FieldFormator<HitDataEntry>(){
		@Override
		public String formatFieldValue(String fieldName, HitDataEntry hitDataEntry) {
			if(("hitTime").equals(fieldName)){
				return TimeUtils.dateToString(hitDataEntry.getHitTime(), "yyyyMMdd HH:mm:ss");
			}else if(("date").equals(fieldName)){
				return TimeUtils.dateToString(hitDataEntry.getDate(), TimeUtils.DATE_PATTERN);
			}else if(("createDate").equals(fieldName)){
				return TimeUtils.dateToString(hitDataEntry.getCreateDate(),TimeUtils.DATE_TIME_PATTERN);
			}else if(("modifyDate").equals(fieldName)){
				return TimeUtils.dateToString(hitDataEntry.getModifyDate(),TimeUtils.DATE_TIME_PATTERN);
			}else if(("arrivalTime").equals(fieldName)){
				return TimeUtils.dateToString(hitDataEntry.getArrivalTime(),TimeUtils.DATE_TIME_PATTERN);
			}
			return null;
		}},new String[]{"date","pid","adId","result","hitTime","accessNum","apn","cellId","channelName","chargeStatus","cityName","countryCode","createDate","custVersion","deviceIMSI","deviceModel","deviceId","deviceIdType","ip","latitude","locId","longitude","modifyDate","netaccessType","operationType","operatorCode","osVersion","pePkgName","peVerCode","peVersion","pePollVersion","sysId","arrivalTime"});
	
	public static final HiveRecordSerializer<AppDataEntry> APP_DATAENTRY_SERIALIZER = new HiveRecordSerializer<AppDataEntry>("pushmarketing.appinfo","logTime",new FieldFormator<AppDataEntry>(){
		@Override
		public String formatFieldValue(String fieldName, AppDataEntry dataEntry) {
			if(("logTime").equals(fieldName)){
				return TimeUtils.dateToString(dataEntry.getLogTime(), "yyyyMMdd HH:mm:ss");
			}else if(("date").equals(fieldName)){
				return TimeUtils.dateToString(dataEntry.getLogTime(), TimeUtils.DATE_PATTERN);
			}else if(("createDate").equals(fieldName)){
				return TimeUtils.dateToString(dataEntry.getCreateDate(),TimeUtils.DATE_TIME_PATTERN);
			}else if(("modifyDate").equals(fieldName)){
				return TimeUtils.dateToString(dataEntry.getModifyDate(),TimeUtils.DATE_TIME_PATTERN);
			}else if(("arrivalTime").equals(fieldName)){
				return TimeUtils.dateToString(dataEntry.getArrivalTime(),TimeUtils.DATE_TIME_PATTERN);
			}
			return null;
		}},new String[]{"date","pid","sid","appPkgName","appVerName","appVerCode","integratedMode","engineWorkMode","accessNum","apn","cellId","channelName","chargeStatus","cityName","countryCode","createDate","custVersion","deviceIMSI","deviceModel","deviceId","deviceIdType","ip","latitude","locId","longitude","modifyDate","netaccessType","operationType","operatorCode","osVersion","pePkgName","peVerCode","peVersion","pePollVersion","sysId","arrivalTime"});

	public static final HiveRecordSerializer<AppFeedbackDataEntry> APPFB_DATAENTRY_SERIALIZER = new HiveRecordSerializer<AppFeedbackDataEntry>("pushmarketing.appfeedback",null,new FieldFormator<AppFeedbackDataEntry>(){
		@Override
		public String formatFieldValue(String fieldName, AppFeedbackDataEntry dataEntry) {
			if(("date").equals(fieldName)){
				return TimeUtils.dateToString(dataEntry.getLogTime(), TimeUtils.DATE_PATTERN);
			}else if(("createDate").equals(fieldName)){
				return TimeUtils.dateToString(dataEntry.getCreateDate(),TimeUtils.DATE_TIME_PATTERN);
			}else if(("modifyDate").equals(fieldName)){
				return TimeUtils.dateToString(dataEntry.getModifyDate(),TimeUtils.DATE_TIME_PATTERN);
			}else if(("arrivalTime").equals(fieldName)){
				return TimeUtils.dateToString(dataEntry.getArrivalTime(),TimeUtils.DATE_TIME_PATTERN);
			}
			return null;
		}},new String[]{"date","pid","sid","adId","success","errCode","packName","currVer","targetVer","eventName","value","accessNum","apn","cellId","channelName","chargeStatus","cityName","countryCode","createDate","custVersion","deviceIMSI","deviceModel","deviceId","deviceIdType","ip","latitude","locId","longitude","modifyDate","netaccessType","operationType","operatorCode","osVersion","pePkgName","peVerCode","peVersion","pePollVersion","sysId","arrivalTime"});
	
	public static final HiveRecordSerializer<NewFeedbackDataEntry> NEWFB_DATAENTRY_SERIALIZER = new HiveRecordSerializer<NewFeedbackDataEntry>("push.feedback", null,  new FieldFormator<NewFeedbackDataEntry>(){
		@Override
		public String formatFieldValue(String fieldName, NewFeedbackDataEntry dataEntry) {
			if(("date").equals(fieldName)){
				return TimeUtils.dateToString(dataEntry.getDate(), TimeUtils.DATE_PATTERN);
			}else if(("createDate").equals(fieldName)){
				return TimeUtils.dateToString(dataEntry.getCreateDate(),TimeUtils.DATE_TIME_PATTERN);
			}else if(("modifyDate").equals(fieldName)){
				return TimeUtils.dateToString(dataEntry.getModifyDate(),TimeUtils.DATE_TIME_PATTERN);
			}else if(("arrivalTime").equals(fieldName)){
				return TimeUtils.dateToString(dataEntry.getArrivalTime(),TimeUtils.DATE_TIME_PATTERN);
			}
			return null;
		}}, new String[]{"pid", "bizType", "eventName", "feedbackId", "success", "sid", "errCode", "packName", "currVer", "targetVer", "value"} );
	
}
