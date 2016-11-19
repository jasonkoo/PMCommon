package com.lenovo.lps.push.marketing.common.compatibility.adapters;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.lenovo.lps.push.marketing.common.vo.DeviceInfoVO;
import com.lenovo.lps.push.marketing.common.vo.expression.Condition;
import com.lenovo.lps.push.marketing.common.vo.expression.ConditionsBuilder;

public class ConditionTest {
	@Test
	public void testConditionBuilder(){
		//编码测试:
		ConditionsBuilder cb = new ConditionsBuilder();
		cb.addGTCondition("pe_vercode", 2, false); //[0]
		cb.addGTCondition("pe_vercode", 2, true);  //[1]
		cb.addLTCondition("pe_vercode", 10, false);//[2]
		cb.addLTCondition("pe_vercode", 10, true); //[3]
		
		cb.addRangeCondition("pe_vercode", 2, 10, true, false); //[4]
		cb.addRangeCondition("pe_vercode", 2, 10, false, false); //[5]
		cb.addRangeCondition("pe_vercode", 2, 10, false, true); //[6]
		
		
		Set<String> valueSet = new HashSet<String>();
		valueSet.add("xxx");
		valueSet.add("~!@#$%^&*()_+`1234567890-=[],./,./<>?");
		valueSet.add("zz");
		
		cb.addInCondition("device_model", valueSet); //[7]
		
		
		cb.addLikeCondition("deviceid_type", "%xy*zz", false); //[8]
		
		System.out.println(cb.toString());
		
		DeviceInfoVO deviceInfo = new DeviceInfoVO();
		deviceInfo.setPe_vercode("02");
		
		
		//解码和匹配测试:
		Gson g = new Gson();
		Condition[] conditions = g.fromJson(cb.toString(), cb.getConditions().getClass());
		System.out.println(conditions.length);
		
		Assert.assertFalse(conditions[0].matchs(deviceInfo,null));
		Assert.assertTrue(conditions[1].matchs(deviceInfo,null));
		deviceInfo.setPe_vercode("10");
		Assert.assertFalse(conditions[2].matchs(deviceInfo,null));
		Assert.assertTrue(conditions[3].matchs(deviceInfo,null));
		
		deviceInfo.setDeviceid_type("123xy4zz");
		
		Assert.assertTrue(conditions[8].matchs(deviceInfo,null));
		deviceInfo.setDeviceid_type("xy4zz");
		Assert.assertTrue(conditions[8].matchs(deviceInfo,null));
		
		deviceInfo.setDeviceid_type("123xyzz");
		Assert.assertFalse(conditions[8].matchs(deviceInfo,null));
		
		deviceInfo.setDevice_model("~!@#$%^&*()_+`1234567890-=[],./,./<>?");
		Assert.assertTrue(conditions[7].matchs(deviceInfo,null));
		
		deviceInfo.setDevice_model("not-in-set");
		Assert.assertFalse(conditions[7].matchs(deviceInfo,null));
		
		conditions = g.fromJson("[{\"fieldName\":\"device_model\",\"operator\":\"in\",\"expression\":\"[\\\"Lenovo S650\\\",\\\"Lenovo A889\\\"]\",\"reverseMatch\":false},{\"fieldName\":\"pe_vercode\",\"operator\":\"range\",\"expression\":\"(402000770,∞)\",\"reverseMatch\":false}]", conditions.getClass());
		deviceInfo.setPe_vercode("402000770");
		Assert.assertFalse(conditions[1].matchs(deviceInfo,null));
		deviceInfo.setPe_vercode("402000769");
		Assert.assertFalse(conditions[1].matchs(deviceInfo,null));
		deviceInfo.setPe_vercode(null);
		Assert.assertFalse(conditions[1].matchs(deviceInfo,null));
		deviceInfo.setPe_vercode("kasdkiei");
		Assert.assertFalse(conditions[1].matchs(deviceInfo,null));
		deviceInfo.setPe_vercode("402000771");
		Assert.assertTrue(conditions[1].matchs(deviceInfo,null));
		
	}
}
