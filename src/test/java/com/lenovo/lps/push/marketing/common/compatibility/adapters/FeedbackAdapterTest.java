package com.lenovo.lps.push.marketing.common.compatibility.adapters;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.lenovo.lps.push.marketing.common.compatibility.vo.AppInstall;
import com.lenovo.lps.push.marketing.common.compatibility.vo.FeedbackData;
import com.lenovo.lps.push.marketing.common.vo.DeviceInfoVO;
import com.lenovo.lps.push.marketing.common.vo.Feedback;
import com.lenovo.lps.push.marketing.common.vo.FeedbackError;

public class FeedbackAdapterTest {

	// private FeedbackAdapter feedbackAdapter;
	private FeedbackData feedbackData;
	private DeviceInfoVO deviceInfo;

	@Before
	public void setUp() throws Exception {
		//feedbackData = new FeedbackData();

		deviceInfo = new DeviceInfoVO();
		Long pid = 1L;
		deviceInfo.setPid(pid);
		String channelname = "";
		deviceInfo.setChannelname(channelname);
		String cityName = "";
		deviceInfo.setCity_name(cityName);
		String deviceModel = "";
		deviceInfo.setDevice_model(deviceModel);
		String deviceid = "";
		deviceInfo.setDeviceid(deviceid);
		String deviceidType = "";
		deviceInfo.setDeviceid_type(deviceidType);
		String operationType = "";

		deviceInfo.setOperation_type(operationType);
		String osVersion = "";
		deviceInfo.setOs_version(osVersion);
		String pePkgname = "";
		deviceInfo.setPe_pkgname(pePkgname);
		String peVercode = "";
		deviceInfo.setPe_vercode(peVercode);
		String peVersion = "";
		deviceInfo.setPe_version(peVersion);
		String pepollversion = "";
		deviceInfo.setPepollversion(pepollversion);
	}

	@Test
	public void testConvert() {

		test001();
		test002();
		test003();
		test004();
		test005();
	}
	
	private void test005() {
		feedbackData = new FeedbackData();
		String displayMessageIds = "rinter2_2c91bc55451c8a4001452bd3ba5e0018,rinter2_2c91bc55451c8a4001452bd3ba5e0018_d";
		feedbackData.setDisplayMessageIds(displayMessageIds);
		String clickMessagesIds = "1396514749752,1396514749752_c,1396514749752_ATI,1396514749752_ATI,";
		feedbackData.setClickMessagesIds(clickMessagesIds);
		List<AppInstall> downloadApps = new ArrayList<AppInstall>();
		List<AppInstall> installApps = new ArrayList<AppInstall>();
		feedbackData.setDownloadApps(downloadApps);
		AppInstall ai1 = new AppInstall();
		String messageFBID1 = "rinter2_1396514749732";
		ai1.setMessageFBID(messageFBID1);
		String result1 = "Success";
		ai1.setResult(result1);
		ai1.setTargetVersion("targetVersion1");
		ai1.setPackageName("com.asdasf.a");

		AppInstall ai2 = new AppInstall();
		String messageFBID2 = "rinter2_1396514749752";
		ai2.setMessageFBID(messageFBID2);
		String result2 = "ERROR_UNKNOWN";
		ai2.setResult(result2);
		ai2.setTargetVersion("targetVersion2");
		ai2.setPackageName("com.asdasf.b");
		
		AppInstall ai3 = new AppInstall();
		String messageFBID3 = null;
		ai3.setMessageFBID(messageFBID3);
		String result3 = "Success";
		ai3.setResult(result3);
		ai3.setTargetVersion("targetVersion3");
		ai3.setPackageName("com.asdasf.3");

		downloadApps.add(ai1);
		downloadApps.add(ai2);
		downloadApps.add(ai3);
		
		installApps.add(ai1);
		installApps.add(ai2);
		installApps.add(ai3);
		feedbackData.setDownloadApps(downloadApps);
		feedbackData.setAppInstalls(installApps);
		AdaptedResult ar = FeedbackAdapter.convert(feedbackData, deviceInfo);
		List<Feedback> list = ar.getFeedbackList();
		assertEquals(5, list.size());
		List<FeedbackError> list1 = ar.getErrorList();
		assertEquals(2, list1.size());

	}

	private void test004() {
		feedbackData = new FeedbackData();
		String displayMessageIds = "rinter2_2c91bc55451c8a4001452bd3ba5e0018,rinter2_2c91bc55451c8a4001452bd3ba5e0018_d";
		feedbackData.setDisplayMessageIds(displayMessageIds);
		String clickMessagesIds = "rinter2_2c91bc55451c8a4001452bd3ba5e0018";
		feedbackData.setClickMessagesIds(clickMessagesIds);
		List<Feedback> list = FeedbackAdapter.convert(feedbackData, deviceInfo).getFeedbackList();
		assertEquals(3, list.size());

	}

	private void test003() {
		feedbackData = new FeedbackData();
		String displayMessageIds = "rinter2_1396514749752,rinter2_1396514749752_d,rinter2_1396514749752_INS_e";
		feedbackData.setDisplayMessageIds(displayMessageIds);
		String clickMessagesIds = "rinter2_2c91bc55451c8a4001452bd3ba5e0018";
		feedbackData.setClickMessagesIds(clickMessagesIds);
		List<Feedback> list = FeedbackAdapter.convert(feedbackData, deviceInfo).getFeedbackList();
		assertEquals(3, list.size());

	}

	private void test002() {
		feedbackData = new FeedbackData();
		String displayMessageIds = "rinter2_2c91bc55451c8a4001452bd3ba5e0018,rinter2_2c91bc55451c8a4001452bd3ba5e0018_d";
		feedbackData.setDisplayMessageIds(displayMessageIds);
		String clickMessagesIds = "rinter2_1396514749752,rinter2_1396514749752_c,rinter2_1396514749752_ATI,rinter2_1396514749752_ATI,";
		feedbackData.setClickMessagesIds(clickMessagesIds);
		List<AppInstall> downloadApps = new ArrayList<AppInstall>();
		feedbackData.setDownloadApps(downloadApps);
		AppInstall ai1 = new AppInstall();
		String messageFBID1 = "rinter2_1396514749732";
		ai1.setMessageFBID(messageFBID1);
		String result1 = "Success";
		ai1.setResult(result1);

		AppInstall ai2 = new AppInstall();
		String messageFBID2 = "rinter2_1396514749752";
		ai2.setMessageFBID(messageFBID2);
		String result2 = "ERROR_UNKNOWN";
		ai2.setResult(result2);

		downloadApps.add(ai1);
		downloadApps.add(ai2);
		feedbackData.setDownloadApps(downloadApps);
		List<Feedback> list = FeedbackAdapter.convert(feedbackData, deviceInfo).getFeedbackList();
		assertEquals(7, list.size());

	}

	private void test001() {
		feedbackData = new FeedbackData();
		String displayMessageIds = ",rinter2_2c91bc55451c8a4001452bd3ba5e0018,rinter2_2c91bc55451c8a4001452bd3ba5e0018_d,null_null";
		feedbackData.setDisplayMessageIds(displayMessageIds);
		String clickMessagesIds = "rinter2_2c91bc55451c8a4001452bd3ba5e0018";
		feedbackData.setClickMessagesIds(clickMessagesIds);
		
		List<Feedback> list = FeedbackAdapter.convert(feedbackData, deviceInfo).getFeedbackList();
		assertEquals(3, list.size());
	}
}
