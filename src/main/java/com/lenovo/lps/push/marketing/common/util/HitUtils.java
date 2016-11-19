package com.lenovo.lps.push.marketing.common.util;

import org.apache.log4j.Logger;

import com.lenovo.lps.push.common.vo.AppInfo;
import com.lenovo.lps.push.marketing.common.vo.AD;
import com.lenovo.lps.push.marketing.common.vo.DeviceInfoVO;
import com.lenovo.lps.push.marketing.common.vo.expression.Condition;

public class HitUtils {
	private static final Logger LOG = Logger.getLogger(HitUtils.class);
	
//	private static final Comparator<AD> PMArchivesComparator = new Comparator<AD>() {
//		public int compare(AD a1, AD a2) {
//			return a1.getWeight() - a2.getWeight();
//		}
//	};
	

//	public static AD electByWeight(List<AD> result){
//		if(result.size()==1){
//			return result.get(0);
//		}
//		Collections.sort(result, PMArchivesComparator);
//		int weightSum = 0;
//		for(AD archive:result){
//			weightSum += archive.getWeight();
//		}
//		int randomInt = RandomUtils.nextInt(0, weightSum);
//		int i = 0;
//		for(AD archive:result){
//			i += archive.getWeight();
//			if(i>=randomInt){
//				return archive;
//			}
//		}
//		return null;
//	}
	
	
	/**
	 * 推送维度匹配
	 * @param user
	 * @param ad
	 * @return
	 */
	public static boolean dimMatch(DeviceInfoVO user,AppInfo app,Condition[] dimConditions) {
		if(dimConditions!=null){
			if(dimConditions!=null){
				for(Condition condition:dimConditions){
					try {
						if(!condition.matchs(user,app)){
							return false;
						}
					} catch (Exception e) {
						LOG.warn(e);
						return false;
					} 
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断广告是否过期
	 * @param ad
	 * @param current
	 * @return
	 */
	public static  boolean outOfDate(AD ad) {
		long current = System.currentTimeMillis();
		boolean beforeBeginTime = ad.getBegin_time()>0 && ad.getBegin_time() > current;
		boolean afterEndTime = ad.getEnd_time()>0 && current >= ad.getEnd_time();
		return beforeBeginTime || afterEndTime;
	}

//	/**
//	 * 判断用户是否在广告黑名单内
//	 * @param user
//	 * @param ad
//	 * @return
//	 */
//	public static boolean inBlacklist(DeviceInfoVO user, AD ad) {
//		ADPushCondition pushCondition = ad.getPushCondition();
//		if(pushCondition!=null){
//			if(pushCondition.getBlack_list()!=null){
//				return pushCondition.getBlack_list().contains(user.getPid());
//			}
//		}
//		return false;
//	}
//	
//	/**
//	 * 判断用户是否在广告白名单内
//	 * @param user
//	 * @param ad
//	 * @return
//	 */
//	public static  boolean inAssiglist(DeviceInfoVO user, AD ad) {
//		ADPushCondition pushCondition = ad.getPushCondition();
//		if(pushCondition!=null){
//			if(pushCondition.getAssig_list()!=null){
//				return pushCondition.getAssig_list().contains(user.getPid());
//			}
//		}
//		return false;
//	}
}
