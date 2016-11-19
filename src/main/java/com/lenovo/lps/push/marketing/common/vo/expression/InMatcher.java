package com.lenovo.lps.push.marketing.common.vo.expression;

import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class InMatcher implements ConditionMatcher {
	private Set<String> valueSet;
	
	public InMatcher(String expression) {
		Gson g = new Gson();
		valueSet = g.fromJson(expression,new TypeToken<Set<String>>(){}.getType());
	}

	@Override
	public boolean matchs(String value) {
		return valueSet.contains(value);
	}
}
