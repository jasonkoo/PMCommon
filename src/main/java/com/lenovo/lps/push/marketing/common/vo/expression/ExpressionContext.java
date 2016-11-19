package com.lenovo.lps.push.marketing.common.vo.expression;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.lenovo.lps.push.common.vo.AppInfo;
import com.lenovo.lps.push.marketing.common.vo.DeviceInfoVO;

public class ExpressionContext {
	
	private static final Map<String,Field> dimFields = new HashMap<String,Field>();
	
	static{
		loadFields(DeviceInfoVO.class);
		loadFields(AppInfo.class);
	}
	
	private static void loadFields(Class<?> clazz){
		Field[] fields = clazz.getDeclaredFields();
		for(Field f:fields){
			if(f.getType().equals(String.class)){
				f.setAccessible(true);
				dimFields.put(f.getName(), f);
			}
		}
	}
	
	public	static String getVlaue(String fieldName,DeviceInfoVO device,AppInfo appInfo) throws Exception{
		Field field = dimFields.get(fieldName);
		if(field==null){
			throw new IllegalArgumentException("Neither DeviceInfoVO nor AppInfo contains field name : " + fieldName);
		}
		if(device!=null && field.getDeclaringClass().isInstance(device)){
			return (String)field.get(device).toString();
		}
		if(appInfo!=null && field.getDeclaringClass().isInstance(appInfo)){
			return (String)field.get(appInfo).toString();
		}
		return null;
	} 
}
