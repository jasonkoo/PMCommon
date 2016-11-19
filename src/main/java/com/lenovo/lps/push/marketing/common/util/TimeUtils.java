package com.lenovo.lps.push.marketing.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimeUtils {
	private static final DateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd"); 
	private static String currentDateStr;
	private static int currentHour;
	private static int currentMin;
	private static long todayBegin;
	
	private TimeUtils(){}
	
	static{
		calculate();
		Timer syncADTimer = new Timer("SYNC_AD_Timer");
		syncADTimer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				calculate();
			}
		}, 0, 1000);
	}
	
	private synchronized static void calculate(){
		Calendar c = Calendar.getInstance();
		currentHour = c.get(Calendar.HOUR_OF_DAY);
		currentMin = c.get(Calendar.MINUTE);
		currentDateStr = dateFormate.format(c.getTime());
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		todayBegin = c.getTimeInMillis();
	}
	
	public final static String DATE_TIME_PATTERN = "yyyyMMdd HH:mm:ss";
	public final static String DATE_PATTERN = "yyyyMMdd";
	
	public final static String MIN_PATTERN = "HHmm";
	public final static String HOUR_PATTERN = "HH";

	public static String dateToString(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		} else {
			return null;
		}
	}

	public static Date stringToDate(String dateStr, String pattern) throws ParseException {
		if (dateStr != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.parse(dateStr);
		} else {
			return null;
		}
	}
	
	public static String getCurrentdatestr() {
		return currentDateStr;
	}
	public static int getCurrenthour() {
		return currentHour;
	}
	public static int getCurrentmin() {
		return currentMin;
	}
	public static boolean isToday(long timeMillis){
		if(timeMillis>=todayBegin){
			return true;
		}
		return false;
	}
}
