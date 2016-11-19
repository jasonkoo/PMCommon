package com.lenovo.lps.push.marketing.common.vo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lenovo.lps.push.marketing.common.util.TimeUtils;
import com.lenovo.lps.push.marketing.common.vo.expression.Condition;

/**
 * 营销广告
 * @author chenzhao1
 *
 */
@Entity
@Table(name = "ad")
public class AD implements Serializable{
	private static final long serialVersionUID = -611633709378698818L;
	public static final int PUSH_STATE_STANDBY = 0;
	public static final int PUSH_STATE_ACTIVE = 1;
	public static final int PUSH_STATE_SUSPEND = 2;
	public static final int PUSH_STATE_CLOSED = 3;
	
	public static final int APPLY_STATE_NEW = -3;
	public static final int APPLY_STATE_SUBMITED = -2;
	public static final int APPLY_STATE_REJECTED = -1;
	public static final int APPLY_STATE_PASSED = 0;
	
	public static final int GOAL_TYPE_SEND = 0;
	public static final int GOAL_TYPE_DISPLAY = 1;
	public static final int GOAL_TYPE_CLICK = 2;
	public static final int GOAL_TYPE_DOWNLOAD = 3;
	public static final int GOAL_TYPE_INSTALL = 4;
	public static final int GOAL_TYPE_ACTIVATION = 5;
	
	public static final String LIST_TYPE_PID="10";
	public static final String LIST_TYPE_IMEI="11";
	
	public static final String SYS_MSG_SID = "rinter2"; 
	
	/**
	 * 广告id
	 */
	@Id
	private String id = UUID.randomUUID().toString();
	
	private Long idx;
	
	/**
	 * 活动ID
	 */
	private String activity_id;
	
	/**
	 * 广告名称
	 */
	private String ad_name;
	
	/**
	 * 广告描述
	 */
	private String description;
	
	/**
	 * 消息报文
	 */
	private String message;
	
	private String fc_key;
	
	/**
	 * 广告起始时间
	 */
	private Long begin_time = -1L;
	
	/**
	 * 广告结束时间
	 */
	private Long end_time = -1L;
	
	/**
	 * 达到目标条件类型
	 */
	private Integer push_goal_type = GOAL_TYPE_DISPLAY;
	
	/**
	 * 达到目标条件值
	 */
	private Long push_goal_value = 1000L;
	
	private String push_hours = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23";
	
	private Boolean skip_distrub_check = false;
	
	private Date create_time = new Date();
	
	private Date modify_time = new Date();
	
	private Integer push_state = PUSH_STATE_SUSPEND;
	
	private Integer apply_state = APPLY_STATE_NEW;
	
	private String dim_conditions;
	private String black_list_id;
	private String black_list_type;
	private String assig_list_id;
	private String assig_list_type;
	private String sid;
	private String app_package_name,app_version_name,app_version_code;
	
	/**
	 * 最快推送速率(设备/分钟)，广告主可能要求广告按照一定的频率发送，而不是尽快发送完毕
	 */
	private Integer max_push_rate;
	
	/**
	 * 广告不被接收度，用户讨厌该广告的程度
	 */
	private Integer boring;
	
	private String ad_owner;
	
	private transient int[] pushHours; 
	private transient boolean isPushhour = false;
	private transient int lastCheckHour = -1;
	private transient Condition[] dimConditions;
	
	public boolean isPushTime(){
		if(pushHours==null){
			if(push_hours==null){
				pushHours = new int[0];
			}else{
				String[] parts = push_hours.split(",");
				int[] tmpPushHours = new int[parts.length];
				for(int i=0;i<parts.length;i++){
					tmpPushHours[i] = Integer.parseInt(parts[i].trim());
				}
				pushHours = tmpPushHours;
			}
		}
		if(lastCheckHour!=TimeUtils.getCurrenthour()){
			if(Arrays.binarySearch(pushHours, TimeUtils.getCurrenthour())<0){
				isPushhour = false;
			}else{
				isPushhour = true;
			}
		}
		return isPushhour;
	}
	
	public Long getIdx() {
		return idx;
	}

	public void setIdx(Long idx) {
		this.idx = idx;
	}



	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAd_name() {
		return ad_name;
	}

	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getBegin_time() {
		return begin_time;
	}

	public void setBegin_time(Long begin_time) {
		this.begin_time = begin_time;
	}

	public Long getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Long end_time) {
		this.end_time = end_time;
	}
	
	public Integer getPush_goal_type() {
		return push_goal_type;
	}

	public void setPush_goal_type(Integer push_goal_type) {
		this.push_goal_type = push_goal_type;
	}

	public Long getPush_goal_value() {
		return push_goal_value;
	}

	public void setPush_goal_value(Long push_goal_value) {
		this.push_goal_value = push_goal_value;
	}

	public String getAd_owner() {
		return ad_owner;
	}

	public void setAd_owner(String ad_owner) {
		this.ad_owner = ad_owner;
	}

	public String getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(String activity_id) {
		this.activity_id = activity_id;
	}

	public Integer getPush_state() {
		return push_state;
	}

	public void setPush_state(Integer push_state) {
		this.push_state = push_state;
	}

	public Integer getApply_state() {
		return apply_state;
	}

	public void setApply_state(Integer apply_state) {
		this.apply_state = apply_state;
	}
	
	public String getApp_package_name() {
		return app_package_name;
	}

	public void setApp_package_name(String app_package_name) {
		this.app_package_name = app_package_name;
	}

	public String getApp_version_name() {
		return app_version_name;
	}

	public void setApp_version_name(String app_version_name) {
		this.app_version_name = app_version_name;
	}

	public String getApp_version_code() {
		return app_version_code;
	}

	public void setApp_version_code(String app_version_code) {
		this.app_version_code = app_version_code;
	}

	public Integer getMax_push_rate() {
		return max_push_rate;
	}

	public void setMax_push_rate(Integer max_push_rate) {
		this.max_push_rate = max_push_rate;
	}

	public Integer getBoring() {
		return boring;
	}

	public void setBoring(Integer boring) {
		this.boring = boring;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getModify_time() {
		return modify_time;
	}

	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}

	public String getPush_hours() {
		return push_hours;
	}

	public void setPush_hours(String push_hours) {
		this.push_hours = push_hours;
	}

	public Boolean getSkip_distrub_check() {
		return skip_distrub_check;
	}

	public void setSkip_distrub_check(Boolean skip_distrub_check) {
		this.skip_distrub_check = skip_distrub_check;
	}
	
	public Condition[] getDimConditions() {
		return dimConditions;
	}

	public void setDimConditions(Condition[] dimConditions) {
		this.dimConditions = dimConditions;
	}

	public String getDim_conditions() {
		return dim_conditions;
	}

	public void setDim_conditions(String dim_conditions) {
		this.dim_conditions = dim_conditions;
	}

	public String getBlack_list_id() {
		return black_list_id;
	}

	public void setBlack_list_id(String black_list_id) {
		this.black_list_id = black_list_id;
	}

	public String getAssig_list_id() {
		return assig_list_id;
	}

	public void setAssig_list_id(String assig_list_id) {
		this.assig_list_id = assig_list_id;
	}

	public String getBlack_list_type() {
		return black_list_type;
	}

	public void setBlack_list_type(String black_list_type) {
		this.black_list_type = black_list_type;
	}

	public String getAssig_list_type() {
		return assig_list_type;
	}

	public void setAssig_list_type(String assig_list_type) {
		this.assig_list_type = assig_list_type;
	}
	
	public String getFc_key() {
		return fc_key;
	}

	public void setFc_key(String fc_key) {
		this.fc_key = fc_key;
	}
	
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AD other = (AD) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AD [id=");
		builder.append(id);
		builder.append("\r\n activity_id=");
		builder.append(activity_id);
		builder.append("\r\n ad_name=");
		builder.append(ad_name);
		builder.append("\r\n description=");
		builder.append(description);
		builder.append("\r\n message=");
		builder.append(message);
		builder.append("\r\n begin_time=");
		builder.append(begin_time);
		builder.append("\r\n end_time=");
		builder.append(end_time);
		builder.append("\r\n push_goal_type=");
		builder.append(push_goal_type);
		builder.append("\r\n push_goal_value=");
		builder.append(push_goal_value);
		builder.append("\r\n push_hours=");
		builder.append(push_hours);
		builder.append("\r\n skip_distrub_check=");
		builder.append(skip_distrub_check);
		builder.append("\r\n create_time=");
		builder.append(create_time);
		builder.append("\r\n modify_time=");
		builder.append(modify_time);
		builder.append("\r\n push_state=");
		builder.append(push_state);
		builder.append("\r\n apply_state=");
		builder.append(apply_state);
		builder.append("\r\n dim_conditions=");
		builder.append(dim_conditions);
		builder.append("\r\n black_list_id=");
		builder.append(black_list_id);
		builder.append("\r\n assig_list_id=");
		builder.append(assig_list_id);
		builder.append("\r\n app_package_name=");
		builder.append(app_package_name);
		builder.append("\r\n app_version_name=");
		builder.append(app_version_name);
		builder.append("\r\n app_version_code=");
		builder.append(app_version_code);
		builder.append("\r\n max_push_rate=");
		builder.append(max_push_rate);
		builder.append("\r\n boring=");
		builder.append(boring);
		builder.append("\r\n ad_owner=");
		builder.append(ad_owner);
		builder.append("]");
		return builder.toString();
	}

}
