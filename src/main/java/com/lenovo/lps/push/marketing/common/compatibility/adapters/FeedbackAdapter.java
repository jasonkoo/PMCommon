package com.lenovo.lps.push.marketing.common.compatibility.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.lenovo.lps.push.common.PushConsts;
import com.lenovo.lps.push.common.vo.FeedbackEvent;
import com.lenovo.lps.push.common.vo.FeedbackEvent.EventName;
import com.lenovo.lps.push.marketing.common.compatibility.vo.AppInstall;
import com.lenovo.lps.push.marketing.common.compatibility.vo.FeedbackData;
import com.lenovo.lps.push.marketing.common.vo.DeviceInfoVO;
import com.lenovo.lps.push.marketing.common.vo.Feedback;
import com.lenovo.lps.push.marketing.common.vo.FeedbackError;

public class FeedbackAdapter {

	private static final Logger logger = Logger
			.getLogger(FeedbackAdapter.class);

	private static final String DISPLAYED_TWO_SURFIX = "_d";
	private static final String CLICKED_TWO_SURFIX = "_c";
	//private static final String INSTALLED_SURFIX = "_INS_e";
	private static final String ACTIVATED_SURFIX = "_ATI";
	private static final String UNDERLINE = "_";
	private static final int MAX_UNDERLINE_INDEX_IN_VALID_ID = 7;

	private static final String UNDERLINE_E_SURFIX = "_e";
	
	private static final Map<String,Integer> EVENT_CODE_MAPPING = new HashMap<String,Integer>();
	
	static{
		EVENT_CODE_MAPPING.put(EventName.activate.toString(), Feedback.ARRIVED);
		EVENT_CODE_MAPPING.put(EventName.display.toString(), Feedback.DISPLAYED);
		EVENT_CODE_MAPPING.put(EventName.click.toString(), Feedback.CLICKED);
		EVENT_CODE_MAPPING.put(EventName.display2nd.toString(), Feedback.DISPLAYED_TWO);
		EVENT_CODE_MAPPING.put(EventName.click2nd.toString(), Feedback.CLICKED_TWO);
		EVENT_CODE_MAPPING.put(EventName.download.toString(), Feedback.DOWNLOADED);
		EVENT_CODE_MAPPING.put(EventName.install.toString(), Feedback.INSTALLED);
		EVENT_CODE_MAPPING.put(EventName.activate.toString(), Feedback.ACTIVATED);
		EVENT_CODE_MAPPING.put(EventName.engineUpgrade.toString(), Feedback.ENGINE_UPGRADE);
		EVENT_CODE_MAPPING.put(EventName.displayInstall.toString(), Feedback.DISPLAY_INSTALL);
		EVENT_CODE_MAPPING.put(EventName.exist.toString(), Feedback.EXIST);
		EVENT_CODE_MAPPING.put(EventName.nacData.toString(), Feedback.NAC_DATA);
	}

	/**
	 * 将FeedBackData转换成多条Feedback数据
	 * 
	 * @param feedBackData
	 * @return
	 */
	public static AdaptedResult convert(FeedbackData feedbackData,
			DeviceInfoVO deviceInfo) {
		boolean noError = true; 
		// liuhk2: 实现该方法；和 正军 确认 字段代表的意思
		List<Feedback> feedbackList = null;
		List<FeedbackError> errorList = null;
		if (feedbackData != null) {
			feedbackList = new ArrayList<Feedback>();
			errorList = new ArrayList<FeedbackError>();
			String displayIds = feedbackData.getDisplayMessageIds();
			if (StringUtils.isNotEmpty(displayIds)) {
				String[] displayIdArray = displayIds.split(",");
				for (int i = 0; i < displayIdArray.length; i++) {
					String id = displayIdArray[i].trim();
					if (StringUtils.isNotEmpty(id)) {
						boolean addBoolean = addToList(parseDisplayId(id), feedbackList, deviceInfo);
						noError = noError && addBoolean;
					}
				}
			}
			String clickIds = feedbackData.getClickMessagesIds();
			if (StringUtils.isNotEmpty(clickIds)) {
				String[] clickIdArray = clickIds.split(",");
				for (int i = 0; i < clickIdArray.length; i++) {
					String id = clickIdArray[i].trim();
					if (StringUtils.isNotEmpty(id)) {
						boolean addBoolean = addToList(parseClickId(id), feedbackList, deviceInfo);
						noError = noError && addBoolean;
					}
				}
			}
			List<AppInstall> downloadApps = feedbackData.getDownloadApps();
			if (downloadApps != null) {
				for (AppInstall ai : downloadApps) {
					boolean addBoolean = addToList(parseDownloadedApp(ai), feedbackList, deviceInfo);
					noError = noError && addBoolean;
					addToList(parseErrorApp(ai, FeedbackError.TYPE_DOWNLOAD_ERROR), errorList, deviceInfo);
				}
			}
			List<AppInstall> appInstalls = feedbackData.getAppInstalls();
			if (appInstalls != null) {
				for (AppInstall ai : appInstalls) {
					boolean addBoolean = addToList(parseInstalledApp(ai), feedbackList, deviceInfo);
					noError = noError && addBoolean;
					addToList(parseErrorApp(ai,FeedbackError.TYPE_INSTALL_ERROR), errorList, deviceInfo);
				}
			}
			if(!noError && feedbackList.size()==0 && errorList.size() == 0){
				logger.info("Unexpected feedback:   " + feedbackData.toString());
			}
			AdaptedResult ar = new AdaptedResult();
			ar.setFeedbackList(feedbackList);
			ar.setErrorList(errorList);
			return ar;
		}
		return null;
	}
	
	private static List<FeedbackError> parseErrorApp(AppInstall ai,String type) {
		if (ai!=null) {
			String result = ai.getResult();
			if (result!=null) {
				String[] results = result.split(",");
				if (results!=null) {
					List<FeedbackError> list = new ArrayList<FeedbackError>();
					for (String r : results) {
						if (r!=null) {
							String rTmp = r.trim();
							if (StringUtils.isNotEmpty(rTmp)) {
								FeedbackError error = new FeedbackError();
								error.setAdId(ai.getMessageFBID());
								error.setResult(rTmp);
								error.setType(type);
								if (FeedbackError.TYPE_INSTALL_ERROR.equals(type)) {
									error.setPackageName(ai.getPackageName());
									error.setTargetVersion(ai.getTargetVersion());
								}
								list.add(error);
							}
						}
					}
					return list;
				}
			}
		}
		return null;
	}

	private static boolean isValidErrorResult(String result) {
		return StringUtils.isNotEmpty(result) && result.toLowerCase().contains("error");
	}

	private static void addToList(List<FeedbackError> list,
			List<FeedbackError> errorList, DeviceInfoVO deviceInfo) {
		if (list != null) {
			for (FeedbackError error : list) {
				if (error != null && error != null && deviceInfo != null) {
					error.setDeviceInfo(deviceInfo);
					error.setPid(deviceInfo.getPid());
					error.setLogTime(System.currentTimeMillis());
					if (isValid(error)) {
						errorList.add(error);
					}
				}
			}
		}
	}
	
	private static boolean isValid(FeedbackError error) {

		if (error != null) {
			String adId = error.getAdId();
			String result = error.getResult();
			if (validAdId(adId) && isValidErrorResult(result)) {
				return true;
			}
		} else {
			logger.warn("feedbackError is null");
		}
		return false;
	}

	private static boolean addToList(Feedback feedback,
			List<Feedback> feedbackList, DeviceInfoVO deviceInfo) {
		if (feedback != null && feedbackList != null && deviceInfo != null) {
			feedback.setAc_id("ac_id");
			feedback.setAd_type("ad_type");
			feedback.setDeviceInfo(deviceInfo);
			feedback.setPid(deviceInfo.getPid());
			feedback.setLog_time(System.currentTimeMillis());
			if (isValid(feedback)) {
				// liuhk2: 这里没有去重逻辑
				feedbackList.add(feedback);
				return true;
			}
		}
		return false;
	}

	private static boolean isValid(Feedback feedback) {

		if (feedback != null) {
			String adId = feedback.getAd_id();
			// liuhk2: 目前只检查id，以后可以检查其他field
			if (validAdId(adId)) {
				return true;
			}
		} else {
			logger.warn("feedback is null");
		}
		return false;
	}

	private static boolean validAdId(String adId) {
		if (Feedback.AD_ID_USER_INSTALL_APP.equals(adId)) {
			return true;
		}
		
		if (adId == null
				|| !adId.contains("rinter2")
				|| adId.toLowerCase().contains("null")
				|| adId.toLowerCase().contains("ERROR_UNKNOWN")
				//|| (adId.endsWith(UNDERLINE_E_SURFIX) && !adId.endsWith(INSTALLED_SURFIX))) {
				|| adId.endsWith(UNDERLINE_E_SURFIX)) {
			return false;
		}
		return true;
	}
	
	private static Feedback parseInstalledApp(AppInstall ai) {
		boolean suc = isSuccessfullyInstalled(ai.result);
		if (suc) {
			String adId = ai.messageFBID;
			if (StringUtils.isNotEmpty(adId)) {
				Feedback feedback = new Feedback();
				feedback.setAd_id(adId);
				feedback.setEvent_type(Feedback.INSTALLED);
				return feedback;
			} else {
				// 用户手动安装的应用
				Feedback feedback = new Feedback();
				feedback.setAd_id(Feedback.AD_ID_USER_INSTALL_APP);
				feedback.setEvent_type(Feedback.INSTALLED);
				return feedback;
			}
		}
		return null;
	}
	
	private static boolean isSuccessfullyInstalled(String result) {
		return result != null && !result.toLowerCase().contains("error");
	}

	private static Feedback parseDownloadedApp(AppInstall ai) {
		boolean suc = isSuccessfullyDownloaded(ai.result);
		if (suc) {
			String adId = ai.messageFBID;
			if (StringUtils.isNotEmpty(adId)) {
				Feedback feedback = new Feedback();
				feedback.setAd_id(adId);
				feedback.setEvent_type(Feedback.DOWNLOADED);
				return feedback;
			}
		}
		return null;
	}

	private static boolean isSuccessfullyDownloaded(String result) {
		return result != null && !result.toLowerCase().contains("error");
	}

	private static Feedback parseClickId(String id) {
		if (StringUtils.isNotEmpty(id)) {
			if (id.endsWith(CLICKED_TWO_SURFIX)) {
				Feedback feedback = new Feedback();
				feedback.setAd_id(id.substring(0, id.length()
						- CLICKED_TWO_SURFIX.length()));
				feedback.setEvent_type(Feedback.CLICKED_TWO);
				return feedback;
			} else if (id.endsWith(ACTIVATED_SURFIX)) {
				Feedback feedback = new Feedback();
				feedback.setAd_id(id.substring(0, id.length()
						- ACTIVATED_SURFIX.length()));
				feedback.setEvent_type(Feedback.ACTIVATED);
				return feedback;
			} else {
				int index = id.indexOf(UNDERLINE);
				if (index == -1 || index <= MAX_UNDERLINE_INDEX_IN_VALID_ID) {
					Feedback feedback = new Feedback();
					feedback.setAd_id(id);
					feedback.setEvent_type(Feedback.CLICKED);
					return feedback;
				} else {
					logger.warn("illegal ad_id: " + id);
				}
			}
		}
		return null;
	}

	private static Feedback parseDisplayId(String id) {
		if (StringUtils.isNotEmpty(id)) {
			if (id.endsWith(DISPLAYED_TWO_SURFIX)) {
				Feedback feedback = new Feedback();
				feedback.setAd_id(id.substring(0, id.length()
						- DISPLAYED_TWO_SURFIX.length()));
				feedback.setEvent_type(Feedback.DISPLAYED_TWO);
				return feedback;
// 不再用后缀形式判断安装成功				
//			} else if (id.endsWith(INSTALLED_SURFIX)) {
//				Feedback feedback = new Feedback();
//				feedback.setAd_id(id.substring(0, id.length()
//						- INSTALLED_SURFIX.length()));
//				feedback.setEvent_type(Feedback.INSTALLED);
//				return feedback;
			} else {
				int index = id.indexOf(UNDERLINE);
				if (index == -1 || index <= MAX_UNDERLINE_INDEX_IN_VALID_ID) {
					Feedback feedback = new Feedback();
					feedback.setAd_id(id);
					feedback.setEvent_type(Feedback.DISPLAYED);
					return feedback;
				} else {
					logger.warn("illegal ad_id: " + id);
				}
			}
		}
		return null;
	}

	public static Feedback convert(FeedbackEvent appFbEvent,
			DeviceInfoVO deviceInfo) {
		if(appFbEvent!=null && 
				appFbEvent.isSuccess() && 
				appFbEvent.getSid()!=null && 
				appFbEvent.getSid().startsWith(PushConsts.SYS_SID_PREFIX) ){
				Feedback feedback = new Feedback();
				if(FeedbackEvent.BIZ_FAKE.equals(appFbEvent.getBizType())){
					if(appFbEvent.getValue()==null){
						feedback.setAc_id(FeedbackEvent.BIZ_FAKE + "_");
					}else{
						feedback.setAc_id(FeedbackEvent.BIZ_FAKE +  "_" + appFbEvent.getValue());
					}
				}else{
					feedback.setAc_id("ac_id");
				}
				feedback.setAd_type("ad_type");
				feedback.setDeviceInfo(deviceInfo);
				feedback.setPid(deviceInfo.getPid());
				feedback.setLog_time(System.currentTimeMillis());
				feedback.setEvent_type(EVENT_CODE_MAPPING.get(appFbEvent.getEventName()));
				feedback.setAd_id(appFbEvent.getFeedbackId());
				feedback.setLog_time(System.currentTimeMillis());
				return feedback;
		}
		throw new IllegalArgumentException("Illegal argument : appFbEvent . ");
	}

	public static FeedbackError convertFBError(FeedbackEvent appFbEvent,
			DeviceInfoVO deviceInfo) {
		if(appFbEvent!=null && 
				!appFbEvent.isSuccess() && 
				appFbEvent.getSid()!=null && 
				appFbEvent.getSid().startsWith(PushConsts.SYS_SID_PREFIX) ){
			FeedbackError error = new FeedbackError();
			if("install".equals(appFbEvent.getEventName())){
				error.setType(FeedbackError.TYPE_INSTALL_ERROR);
			}else if("download".equals(appFbEvent.getEventName())){
				error.setType(FeedbackError.TYPE_DOWNLOAD_ERROR);
			}else{
				error.setType("");
			}
			error.setResult(appFbEvent.getErrCode());
			error.setPackageName(appFbEvent.getPackName());
			error.setDeviceInfo(deviceInfo);
			error.setPid(deviceInfo.getPid());
			error.setLogTime(System.currentTimeMillis());
			error.setTargetVersion(appFbEvent.getTargetVer());
			error.setAdId(appFbEvent.getFeedbackId());
			return error;
		}
		throw new IllegalArgumentException("Illegal argument : appFbEvent . ");
	}

}