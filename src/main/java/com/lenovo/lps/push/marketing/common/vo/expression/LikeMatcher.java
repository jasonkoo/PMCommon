package com.lenovo.lps.push.marketing.common.vo.expression;


public class LikeMatcher implements ConditionMatcher {
	private String regexp;
	
	public LikeMatcher(String expression) {
		this.regexp = (expression.replaceAll("\\*", ".").replaceAll("%", ".*"));
	}

	@Override
	public boolean matchs(String value) {
		return value.matches(regexp);
	}

}
