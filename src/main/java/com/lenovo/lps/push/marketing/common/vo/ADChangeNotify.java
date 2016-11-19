package com.lenovo.lps.push.marketing.common.vo;

import java.io.Serializable;

public class ADChangeNotify implements Serializable{
	private static final long serialVersionUID = -6252279810878021614L;

	private String adid;
	private int adState;
	
	public ADChangeNotify(String adid, int adState) {
		super();
		this.adid = adid;
		this.adState = adState;
	}
	
	public String getAdid() {
		return adid;
	}
	public void setAdid(String adid) {
		this.adid = adid;
	}
	public int getAdState() {
		return adState;
	}
	public void setAdState(int adState) {
		this.adState = adState;
	}
}
