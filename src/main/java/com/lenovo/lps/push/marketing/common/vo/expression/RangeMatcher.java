package com.lenovo.lps.push.marketing.common.vo.expression;

import org.apache.commons.lang.StringUtils;


public class RangeMatcher implements ConditionMatcher {
	private Long rangeStart;
	private Long rangeEnd;
	private boolean includeStart;
	private boolean includeEnd;
	
	public RangeMatcher(String expression) {
		String exp = expression.trim();
		char startChar = exp.charAt(0);
		char endChar = exp.charAt(exp.length()-1);
		if(startChar=='('){
			includeStart = false;
		}else if(startChar=='['){
			includeStart = true;
		}else{
			throw new IllegalArgumentException("Illegal Range Expression : " + expression);
		}
		
		if(endChar==')'){
			includeEnd = false;
		}else if(endChar==']'){
			includeEnd = true;
		}else{
			throw new IllegalArgumentException("Illegal Range Expression : " + expression);
		}
		
		String[] rangeStrs = exp.substring(1, exp.length()-1).split(",");
		if(rangeStrs.length==2){
			rangeStart = toLongVal(rangeStrs[0]);
			rangeEnd = toLongVal(rangeStrs[1]);
		}else{
			throw new IllegalArgumentException("Illegal Range Expression : " + expression);
		}
		
		if(rangeStart==null && rangeEnd ==null){
			throw new IllegalArgumentException("Illegal Range Expression : " + expression);
		}
		
		if(rangeStart!=null && rangeEnd !=null && rangeEnd < rangeStart){
			throw new IllegalArgumentException("Illegal Range Expression (rangeEnd mast greater than rangeStart) : " + expression);
		}
	}
	
	private Long toLongVal(String string) {
		String numStr = string.trim();
		if("∞".equals(string)){
			return null;
		}else{
			try {
				return Long.valueOf(numStr);
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException(e);
			}
		}
	}

	@Override
	public boolean matchs(String value) {
		if(value==null){
			return false;
		}
		long numberVal = Long.parseLong(value);
		boolean result = true;
		if(rangeStart!=null){
			if(includeStart){
				result = result && numberVal >= rangeStart;
			}else{
				result = result && numberVal > rangeStart;
			}
		}
		if(rangeEnd!=null){
			if(includeEnd){
				result = result && numberVal <= rangeEnd;
			}else{
				result = result && numberVal < rangeEnd;
			}
		}
		return result;
	}

}
