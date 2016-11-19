package com.lenovo.lps.push.marketing.common.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;



@Deprecated
public class DeviceInfoVO  implements Serializable {
	

	private static final long serialVersionUID = 645543022298766221L;

//	public static final String PEPOLL_NEWPOLL = null;

//	private String id;// ID
//	private Date createtime;// 创建日期
//	private Date updatetime;// 修改日期

//	@SearchableId
//	@Id
//	@Column(length = 32, nullable = true)
//	@GeneratedValue(generator = "uuid")
//	@GenericGenerator(name = "uuid", strategy = "uuid")
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}

//	@SearchableProperty(store = Store.YES)
//	@Column(updatable = false)
//	public Date getcreatedate() {
//		return createdate;
//	}
//
//	public void setcreatedate(Date createdate) {
//		this.createdate = createdate;
//	}
//
//	@SearchableProperty(store = Store.YES)
//	public Date getmodifydate() {
//		return modifydate;
//	}
//
//	public void setmodifydate(Date modifydate) {
//		this.modifydate = modifydate;
//	}

	@Override
	public int hashCode() {
		return pid == null ? System.identityHashCode(this) : pid.hashCode();
	}

//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj) {
//			return true;
//		}
//		if (obj == null) {
//			return false;
//		}
//		if (getClass().getPackage() != obj.getClass().getPackage()) {
//			return false;
//		}
//		final BaseEntity other = (BaseEntity) obj;
//		if (pid == null) {
//			if (other.getId() != null) {
//				return false;
//			}
//		} else if (!pid.equals(other.getId())) {
//			return false;
//		}
//		return true;
//	}

	
	// 推送设备标识 
	private Long pid;

	// 节点ID 
	//private String nodeid;

	
	//初次请求时间 
	//private Long createtime;// 创建日期
	
	//最近一次请求时间
	//private Long updatetime;// 修改日期
	
	//初次请求时间 
	private Date createdate;// 创建日期
	//最近一次请求时间
	private Date modifydate;// 修改日期
	
	//机型
	private String device_model;
	
	
	//(指定)设备号类型(IMSI\IMEI\SN\MAC)
	private String deviceid_type;
	
	//增加数据平台统计分析字段
	//(指定)设备号(IMSI\IMEI\SN\MAC)
	private String deviceid;
	
	//(指定)设备号(IMSI
	private String device_imsi;
	
	//OS版本：osversion
	private String os_version;
	
	//OS定制版本：custversion
	private String cust_version;
	
	//PushEngine版本名称：peversion
	private String pe_version;
	
	//PushEngine版本号：pevercode
	private String pe_vercode;
	
	//PushEngine包名：pepkgname
	private String pe_pkgname;
	
	//客户端ip
	private String ip;
	
	//地域标识(内部地域范围字典表)
	//private String area_id;
	
	//地域名称：通过IP转换
	//private String area_name;
	
	//地域标识(内部地域范围字典表)
	private String country_code;
	
	//城市名称：通过IP转换
	private String city_name;
	

	
	//运营商类型(01:移动/02:电信/03:联通/04:铁通)：通过IP转换
	public static final String OPERATION_MOB="01";
	public static final String OPERATION_TEL="02";
	public static final String OPERATION_UNI="03";
	public static final String OPERATION_TIE="04";
	
	private String operation_type;
	
	//数据网络接入类型(1：离线、2：wifi、3：3g4g、4：2g)：通过IP转换
	public static final String NETACCESS_OFFLINE_DESC="offline";
	public static final String NETACCESS_WIFI_DESC="wifi";
	public static final String NETACCESS_3G4G_DESC="3g4g";
	public static final String NETACCESS_3G_DESC="3g";
	public static final String NETACCESS_2G_DESC="2g";
	
	public static final String NETACCESS_OFFLINE="1";
	public static final String NETACCESS_WIFI="2";
	public static final String NETACCESS_3G4G="3";
	public static final String NETACCESS_2G="4";
	private String netaccess_type;
	
	//充电状态(01:充电中状态、02：非充电状态)
	public static final String CHARGE_ISON="01";
	public static final String CHARGE_ISOFF="02";
	private String charge_status;
	
	//网络接入点
	private String apn;
	
	//运营商code
	private String operator_code;
	
	/*
	 *    `sysid` varchar(32) DEFAULT NULL,
		  `locid` varchar(32) DEFAULT NULL,
		  `cellid` varchar(32) DEFAULT NULL,
	 */
	
	
	//基站信息-系统编号
	private String sysid;
	//基站信息-网络编号
	private String locid;
	//基站信息-小区编号
	private String cellid;
	//基站信息-基站经度
	private String latitude;
	//基站信息-基站纬度
	private String longitude;
	//设备包含的应用
	private Set<String> sids;
	
	
	//在线状态(01:在线状态、02：离线状态)
	public static final String DEVICE_ONLINE="01";
	public static final String DEVICE_OFFLINE="02";
	//private String online_status;
	
	//ADD BY YINZJ4 增加Push设备请求Poll版本号:用于区分是否集成了数据采集功能
	public  String pepollversion;
	public static final String PEPOLL_OLDPOLL="01";
	public static final String PEPOLL_NEW21POLL="02";
	public static final String PEPOLL_NEW22POLL="03";
	
	
	public String getPepollversion() {
		return pepollversion;
	}

	public void setPepollversion(String pepollversion) {
		this.pepollversion = pepollversion;
	}
	
	public  String channelname;
	
	//ADD by chenzhao:
	public  String channel_version;
	public  String channel_vercode;
	//END ^
	
	public String getChannelname() {
		return channelname;
	}

	public void setChannelname(String channelname) {
		this.channelname = channelname;
	}
	
	//增加访问次数，便于在线时长、使用频次的统计
	public int accessnum=0;
	
	public int getAccessnum() {
		return accessnum;
	}

	public void setAccessnum(int accessnum) {
		this.accessnum = accessnum;
	}
	
	/*
	//运营商类型(01：移动/02:联通/03:电信/04:铁通)：通过IP转换
	private Operation_type operation_type;
	
	//数据网络接入类型(01：离线、02：wifi、03：3g4g、04：2g)：通过IP转换
	private Netaccess_type netaccess_type;
	
	//充电状态(01:充电中状态、02：非充电状态)
	private Charge_status charge_status;
	
	//在线状态(01:在线状态、02：离线状态)
	private Online_status online_status;
	

	
	// 数据网络接入类型(01：离线、02：wifi、03：3g4g、04：2g)：通过IP转换
	public enum Operation_type {
		MOB,TEL,TIE,UNI
	};
	
	
	// 数据网络接入类型(01：离线、02：wifi、03：3g4g、04：2g)：通过IP转换
	public enum Netaccess_type {
		offline, wifi, _3g4g, _2g
	};

	// 充电状态(01:充电中状态、02：非充电状态)
	public enum Charge_status {
		ischarging,notcharging
	};

	// 在线状态(01:在线状态、02：离线状态)
	public enum Online_status {
		isonline,isoffline
	};

	


	public Operation_type getOperation_type() {
		return operation_type;
	}

	public void setOperation_type(Operation_type operationType) {
		operation_type = operationType;
	}

	public Netaccess_type getNetaccess_type() {
		return netaccess_type;
	}

	public void setNetaccess_type(Netaccess_type netaccessType) {
		netaccess_type = netaccessType;
	}

	public Charge_status getCharge_status() {
		return charge_status;
	}

	public void setCharge_status(Charge_status chargeStatus) {
		charge_status = chargeStatus;
	}

	public Online_status getOnline_status() {
		return online_status;
	}

	public void setOnline_status(Online_status onlineStatus) {
		online_status = onlineStatus;
	}
	*/



	public String getChannel_version() {
		return channel_version;
	}

	public void setChannel_version(String channel_version) {
		this.channel_version = channel_version;
	}

	public String getChannel_vercode() {
		return channel_vercode;
	}

	public void setChannel_vercode(String channel_vercode) {
		this.channel_vercode = channel_vercode;
	}

	public String getOperation_type() {
		return operation_type;
	}

	public void setOperation_type(String operationType) {
		operation_type = operationType;
	}

	public String getNetaccess_type() {
		return netaccess_type;
	}

	public void setNetaccess_type(String netaccessType) {
		netaccess_type = netaccessType;
	}

	public String getCharge_status() {
		return charge_status;
	}

	public void setCharge_status(String chargeStatus) {
		charge_status = chargeStatus;
	}

//	public String getOnline_status() {
//		return online_status;
//	}
//
//	public void setOnline_status(String onlineStatus) {
//		online_status = onlineStatus;
//	}
	
	

	public String getDevice_imsi() {
		return device_imsi;
	}

	public String getApn() {
		return apn;
	}

	public void setApn(String apn) {
		this.apn = apn;
	}

	public String getOperator_code() {
		return operator_code;
	}

	public void setOperator_code(String operatorCode) {
		operator_code = operatorCode;
	}

	public void setDevice_imsi(String deviceImsi) {
		device_imsi = deviceImsi;
	}

	public String getOs_version() {
		return os_version;
	}

	public void setOs_version(String osVersion) {
		os_version = osVersion;
	}

	public String getCust_version() {
		return cust_version;
	}

	public void setCust_version(String custVersion) {
		cust_version = custVersion;
	}

	public String getPe_version() {
		return pe_version;
	}

	public void setPe_version(String peVersion) {
		pe_version = peVersion;
	}

	public String getPe_vercode() {
		return pe_vercode;
	}

	public void setPe_vercode(String peVercode) {
		pe_vercode = peVercode;
	}

	public String getPe_pkgname() {
		return pe_pkgname;
	}

	public void setPe_pkgname(String pePkgname) {
		pe_pkgname = pePkgname;
	}


	public Long getPid() {
		return pid;
	}

	//@GeneratedValue(generator = "uuid")
	//@GenericGenerator(name = "uuid", strategy = "uuid")
	public void setPid(Long pid) {
		this.pid = pid;
	}

//	public String getNodeid() {
//		return nodeid;
//	}
//
//	public void setNodeid(String nodeid) {
//		this.nodeid = nodeid;
//	}

//	public Long getcreatedate() {
//		return createdate;
//	}
//
//	public void setcreatedate(Long createdate) {
//		this.createdate = createdate;
//	}
//
//	public Long getmodifydate() {
//		return modifydate;
//	}
//
//	public void setmodifydate(Long modifydate) {
//		this.modifydate = modifydate;
//	}



//	public Long getCreatetime() {
//		return createtime;
//	}
//
//	public void setCreatetime(Long createtime) {
//		this.createtime = createtime;
//	}
//
//	public Long getUpdatetime() {
//		return updatetime;
//	}
//
//	public void setUpdatetime(Long updatetime) {
//		this.updatetime = updatetime;
//	}
//
//	public void setDeviceid_type(String deviceidType) {
//		deviceid_type = deviceidType;
//	}


	public String getDeviceid_type() {
		return deviceid_type;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}
	
	public void setDeviceid_type(String deviceidType) {
		deviceid_type = deviceidType;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getDevice_model() {
		return device_model;
	}

	public void setDevice_model(String deviceModel) {
		if(deviceModel!=null){
			if(deviceModel.contains("Lenovo_")){
				this.device_model = deviceModel.replace("Lenovo_", "Lenovo ");
				return;
			}
		}
		this.device_model = deviceModel;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

//	public String getArea_id() {
//		return area_id;
//	}
//
//	public void setArea_id(String areaId) {
//		area_id = areaId;
//	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String countryCode) {
		country_code = countryCode;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String cityName) {
		city_name = cityName;
	}

	public String getSysid() {
		return sysid;
	}

	public void setSysid(String sysid) {
		this.sysid = sysid;
	}

	public String getLocid() {
		return locid;
	}

	public void setLocid(String locid) {
		this.locid = locid;
	}

	public String getCellid() {
		return cellid;
	}

	public void setCellid(String cellid) {
		this.cellid = cellid;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Set<String> getSids() {
		return sids;
	}

	public void setSids(Set<String> sids) {
		this.sids = sids;
	}

	
//	public String getArea_name() {
//		return area_name;
//	}
//
//	public void setArea_name(String areaName) {
//		area_name = areaName;
//	}

	


	
}
