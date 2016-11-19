package com.lenovo.lps.push.marketing.common.vo.expression;

import java.io.Serializable;
import java.lang.reflect.Field;

import com.lenovo.lps.push.common.vo.AppInfo;
import com.lenovo.lps.push.marketing.common.vo.DeviceInfoVO;


public class Condition implements Serializable{
	private static final long serialVersionUID = -5317902952483491729L;
	public static enum Operator{
		in,range,like
	}; 
	
	private String fieldName;
	private Operator operator;
	private String expression;
	private boolean reverseMatch = false;
	private ConditionMatcher matcher;
	
	public Condition(String fieldName, Operator operator, String expression,
			boolean reverseMatch) {
		this.fieldName = fieldName;
		this.operator = operator;
		this.expression = expression;
		this.reverseMatch = reverseMatch;
	}
	
	public Condition(){}

	public boolean matchs(DeviceInfoVO deviceInfo,AppInfo appInfo){
		if(matcher==null){
			matcher = buildMatcher();
		}
		try {
			String value = ExpressionContext.getVlaue(fieldName, deviceInfo, appInfo);
			boolean result =  matcher.matchs(value);
			return reverseMatch ? !result : result;
		} catch (Exception e) {
			return false;
		} 
	}
	
	private ConditionMatcher buildMatcher() {
		if(Operator.in.equals(operator)){
			return new InMatcher(expression);
		}
		if(Operator.like.equals(operator)){
			return new LikeMatcher(expression);
		}
		if(Operator.range.equals(operator)){
			return new RangeMatcher(expression);
		}
		return null;
	}

	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public Operator getOperator() {
		return operator;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public boolean isReverseMatch() {
		return reverseMatch;
	}
	public void setReverseMatch(boolean reverseMatch) {
		this.reverseMatch = reverseMatch;
	}
}
