package com.lenovo.lps.push.marketing.common.vo;

import java.io.Serializable;
import java.util.Set;

/**
 * 维度条件
 * @author chenzhao1
 *
 */
public class DIMCondition implements Serializable{
	private static final long serialVersionUID = -1965329951476805348L;
	
	private String dimensionality;
	private Set<String> dataset;
	private boolean reverseMatch = false;
	
	public DIMCondition(String dimensionality, Set<String> dataset,
			boolean reverseMatch) {
		super();
		this.dimensionality = dimensionality;
		this.dataset = dataset;
		this.reverseMatch = reverseMatch;
	}
	
	public DIMCondition(){
		
	}

	public boolean matches(String value){
		boolean result = false;
		if(dataset!=null && dataset.size()>0){
			result = dataset.contains(value);
		}
		if(reverseMatch){
			return !result;
		}
		return result;
	}

	public String getDimensionality() {
		return dimensionality;
	}

	public void setDimensionality(String dimensionality) {
		this.dimensionality = dimensionality;
	}

	public Set<String> getDataset() {
		return dataset;
	}

	public void setDataset(Set<String> dataset) {
		this.dataset = dataset;
	}

	public boolean isReverseMatch() {
		return reverseMatch;
	}

	public void setReverseMatch(boolean reverseMatch) {
		this.reverseMatch = reverseMatch;
	}
}
