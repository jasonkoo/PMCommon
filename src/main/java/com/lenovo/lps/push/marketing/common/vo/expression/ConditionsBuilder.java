package com.lenovo.lps.push.marketing.common.vo.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.lenovo.lps.push.marketing.common.vo.expression.Condition.Operator;

/**
 * 用于创建Condition数组，及其对应的JSON。
 * 【注意】这个类不是线程安全的，每次创建Conditon数组时需要创建新的ConditionsBuilder。
 * @author chenzhao1
 *
 */
public class ConditionsBuilder {
	private List<Condition> conditions = new ArrayList<Condition>();
	
	public ConditionsBuilder addInCondition(String fieldName,Set<String> valueSet){
		return addInCondition(fieldName,valueSet,false);
	}
	
	public ConditionsBuilder addInCondition(String fieldName,Set<String> valueSet,boolean reverseMatch){
		Condition condition = new Condition();
		condition.setOperator(Operator.in);
		Gson g = new Gson();
		condition.setExpression(g.toJson(valueSet));
		condition.setFieldName(fieldName);
		conditions.add(condition);
		return this;
	}
	
	public ConditionsBuilder addGTCondition(String fieldName,long gt,boolean include){
		String expression = 
				(include ? "[" : "(")  
				+ gt 
				+ ",∞)";
		conditions.add(new Condition(fieldName, Operator.range, expression, false));
		return this;
	}
	
	public ConditionsBuilder addLTCondition(String fieldName,long lt,boolean include){
		String expression = 
				"(∞," 
				+ lt 
				+ (include ? "]" : ")") ;
		conditions.add(new Condition(fieldName, Operator.range, expression, false));
		return this;
	}
	
	public ConditionsBuilder addRangeCondition(String fieldName,long gt,long lt,boolean includeGt,boolean includeLt){
		String expression = 
				(includeGt ? "[" : "(") 
				+ lt  + "," + gt
				+ (includeLt ? "]" : ")") ;
		conditions.add(new Condition(fieldName, Operator.range, expression, false));
		return this;
	}
	
	public ConditionsBuilder addLikeCondition(String fieldName,String likeExpression,boolean reverseMatch){
		return addCondition(fieldName, Operator.like, likeExpression, reverseMatch);
	}
	
	public ConditionsBuilder addCondition(String fieldName, Operator operator, String expression,boolean reverseMatch){
		conditions.add(new Condition(fieldName, operator, expression, reverseMatch));
		return this;
	}
	
	public String toString(){
		Gson g = new Gson();
		return g.toJson(conditions);
	}
	
	public Condition[] getConditions(){
		return conditions.toArray(new Condition[0]);
	}
}
