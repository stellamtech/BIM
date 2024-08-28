package com.tw.generics;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

public final class Utils {
	
	public static final String toUS = "yyyy-MM-dd HH:mm:ss";
	public static final String toIN = "dd-MM-yyyy";
	public static final String empty = "";
	public static final String _default = "default";
	private static final String OS = System.getProperty("os.name").toLowerCase();
	public static final String YES = "Yes";
	public static final String NO = "No";
	public static final String ACTIVE = "Active";
	public static final String INACTIVE = "InActive";
	public static final String MOBILE_C = "MOBILE";
	public static final String EMAIL_C = "EMAIL";
	public static final String ACTIVATED = "Activated";
	public static final String UNACTIVATED = "UnActivated";
	
	public static final int ANALYTICS_PREV_PERIOD = 5;
	
	public static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}
	
	public static boolean isUnix() {
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
	}

	public static TreeMap<Integer, String> csvAsTrimedIntTreeMap(String csv){
		TreeMap<Integer, String> map = new TreeMap<Integer, String>();
		if(csv == null || csv.length() < 1)
			return map;
		String[] arr=  csv.split(",");
		for(int i =0; i<arr.length; i++){
			String val = arr[i];
			map.put(i, val.trim());
		}
		return map;
	}
	
	/**
	 * This method returns true if the input array is null or its length is zero.
	 * @param array
	 * @return true | false
	 */
	public static boolean isArrayEmpty( Object[] array ){
		if( array == null || array.length == 0 ){
			return true;
		}
		return false;
	}
	
	public static boolean isEmpty(Integer toCheck){
		return toCheck == null;
	}
	
	public static boolean isNotEmpty(Integer toCheck){
		return toCheck != null;
	}
	
	public static boolean isEmpty(Long toCheck){
		return toCheck == null;
	}
	
	public static boolean isNotEmpty(Long toCheck){
		return toCheck != null;
	}
	
	public static String capitalize(String target){
		return StringUtils.capitalize(target);
	}
	
	public static String deCapitalize(String target){
		if(Utils.isEmpty(target)){
			return "";
		}else{
			if(target.length() > 1){
				StringBuilder sb = new StringBuilder();
				sb.append(Character.toLowerCase(target.charAt(0))).append(target.substring(1));
				return sb.toString();
			}else{
				return String.valueOf(Character.toLowerCase(target.charAt(0)));
			}
		}
	}
	
	public static String getFieldName(String getterOrSetter){
		return Utils.deCapitalize(getterOrSetter.substring(3));
	}
	
	public static String emptyString(){
		return StringUtils.EMPTY;
	}
	
	public static List<Long> csvToLongList(String csv){
		String[] arr = csv.split(",");
		List<Long> ret = new ArrayList<Long>();
		for(String s : arr){
			if(isNotEmpty(csv)){
				if(s.isEmpty() || s.equals("undefined"))
				{
					
				}
				else
				{
				ret.add(Long.parseLong(s));
				}
			}
		}
		return ret;
	}
	
	public static Double calculateAverage(List <Double> marks) {
		  Double sum = (double) 0;
		  if(!marks.isEmpty()) {
		    for (Double mark : marks) {
		        sum += mark;
		    }
		    return sum.doubleValue() / marks.size();
		  }
		  return sum;
		}
	
	public static String getDaoBeanName(String moduleName){
		StringBuilder sb = new StringBuilder();
		sb.append("I").append(capitalize(moduleName));
		return sb.toString();
	}
	
	public static String getIsoDate(Calendar date){
		if(date != null){
			SimpleDateFormat formatIso = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
			String dateStr = formatIso.format(date.getTime());
			return dateStr;
		}else{
			return null;
		}
		
	}
	
	public static Calendar getCalendar(String dateString){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			sdf.parse(dateString);
		} catch (java.text.ParseException e) { e.printStackTrace(); }
		return sdf.getCalendar();
	}
	
	
	public static Calendar getCalendar_two(String dateString){
		SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss z");
		try {
			sdf.parse(dateString);
		} catch (java.text.ParseException e) { e.printStackTrace(); }
		return sdf.getCalendar();
	}
	
	public static int getSecondsRemainsToday() {
		Calendar today = Calendar.getInstance();
		long current = today.getTimeInMillis();
		today.set(Calendar.HOUR_OF_DAY, 23);
	    today.set(Calendar.MINUTE, 59);
	    today.set(Calendar.SECOND, 59);
	    long last = today.getTimeInMillis();
		return (int) (last - current)/1000;
	}
	
	public static Calendar getCalendar(Timestamp timestamp){
		if(timestamp != null){
			Calendar calendar = Calendar.getInstance();
	        calendar.setTimeInMillis(timestamp.getTime());
			return calendar;
		}else{
			return null;
		}
	}
	
	public static Calendar startDt(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}
	
	public static Calendar endDt(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal;
	}
	
	public static Calendar startDt(Calendar cal){
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}
	
	public static Calendar endDt(Calendar cal){
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal;
	}
	
	public static String getAnalyticsDateFormate(Calendar cal) {
		SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yy");
		String r = fmt.format(cal.getTime());
		return r;
	}
	
	public static String getStartOfWeek(Calendar cal) {
		Calendar r = Calendar.getInstance();
		r.setTimeInMillis(cal.getTimeInMillis());
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		r.add(Calendar.DATE, - dayOfWeek);
		SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yy");
		String startOfWeek = fmt.format(r.getTime());
		return startOfWeek;
	}
	
	public static String getMonthYearFormat(Calendar cal) {
		SimpleDateFormat fmt = new SimpleDateFormat("MMM-yy");
		String monthYear = fmt.format(cal.getTime());
		return monthYear;
	}
	
	public static Calendar getCalendarCopyInstance(Calendar cal) {
		Calendar r = Calendar.getInstance();
		r.setTimeInMillis(cal.getTimeInMillis());
		return r;
	}
	
	public static Calendar getCalendar(int year, int month, int date) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, year);
		cal.set(Calendar.DATE, month);
		cal.set(Calendar.DATE, date);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}
	
	public static Calendar getCalendar(int weekOfyear) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.WEEK_OF_YEAR, weekOfyear);        
		cal.set(Calendar.HOUR, 0);
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);
		
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		return cal;
	}
	
	
	public static Calendar getStartOfToday() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c;
    }
	
	public static Calendar getEndOfToday() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c;
    }
	
	public static Calendar getMonthOfCalendar(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR, 0);
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);
		
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		return cal;
	}
	
	public static String getMonthName(Calendar cal) {
		SimpleDateFormat fmt = new SimpleDateFormat("MMMMM");
		String monthName = fmt.format(cal.getTime());
		return monthName;
	}
	
	public static String dateIn(Calendar date){
		StringBuilder sb = new StringBuilder(); 
		sb.append(date.get(Calendar.DATE)).append("-");
		sb.append(date.get(Calendar.MONTH) + 1).append("-");
		sb.append(date.get(Calendar.YEAR));
		String dateIn =  sb.toString();
		return dateIn;
	}
	
	public static String getNewFormatedDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		return sdf.format(new Date());
	}
	
	public static String dateWithMarathiDay(Calendar date){
		StringBuilder sb = new StringBuilder();
		sb.append(dateIn(date));
		sb.append(" ( ");
		int day = date.get(Calendar.DAY_OF_WEEK);
		switch(day){
			case 2: {
				sb.append("सोमवार");
				break;
			}
			case 3: {
				sb.append("मंगळवार");
				break;
			}
			case 4: {
				sb.append("बुधवार");
				break;
			}
			case 5: {
				sb.append("गुरुवार");
				break;
			}
			case 6: {
				sb.append("शुक्रवार");
				break;
			}
			case 7: {
				sb.append("शनिवार");
				break;
			}
			case 1: {
				sb.append("रविवार");
				break;
			}
		}
		sb.append(" )");
		return sb.toString();
	}
	
	public static Double zeroIfNull(Double d){
		if(d == null)
			return 0.0;
		else
			return d;
	}
	
	public static boolean falseIfNull(Boolean b) {
		if(b == null) {
			return false;
		}else {
			return b;
		}
	}
	
	public static Integer zeroIfNull(Integer d){
		if(d == null)
			return 0;
		else
			return d;
	}
	
	public static boolean isEmpty(Double d){
		return d == null;
	}
	
	public static String blankIfNull(String s){
		if(s == null)
			return empty;
		else
			return s;
	}
	
	public static Integer parseInt(String toInt){
		Integer r = null;
		try{
			r = Integer.parseInt(toInt);
		}catch(Exception e){
			e.printStackTrace();
		}
		return r;
	}

	public static boolean isEmpty(String toCheck){
		
		if(toCheck == null || "".equals(toCheck)){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isNotEmpty(String toCheck){
		return !isEmpty(toCheck);
	}
	
	public static Long intToLong(Integer toLong){
		return toLong.longValue();
	}
	
	
	public static Boolean isDesc(String fieldName){
		Character c = fieldName.charAt(0);
		if(c.equals('-'))
			return true;
		else
			return false;
	}
	
	public static String trimDescField(String fieldName){
		return fieldName.substring(1);
	}
	
	public static Long castObjectToLong(Object object) {
		return Long.parseLong(object.toString());
	}
	
	public static Long castNullToZero(Long object) {
		if (object == null) { return 0l; }
		return object;
	}
	
	public static String sellerOrbuyer(String name) {
		if (name.equals("S")) {
			return "SELLER";
		} else if (name.equals("B")) { return "BUYER"; }
		
		return name;
	}
	
	public static Long bigIntToLong(Object o) {
		BigInteger bi = (BigInteger) o;
		return bi.longValue();
	}
	
	public static List<Long> bigIntListToListOfLong(@SuppressWarnings("rawtypes") List list) {
		List<Long> longList = new ArrayList<Long>();
		for(Object o : list) {
			longList.add(bigIntToLong(o));
		}
		return longList;
	}
	
	 public static <T> Set<T> listToSet(List<T> list){
		 Set<T> set = new HashSet<>(list);
		 return set;
	 }
	 
	 public static String getHiphenedValue(String name) {
		 String value = name.replaceAll("[^A-Za-z0-9]+", "-").toLowerCase();
		 return value;
	 }
	 
	 public static Double trunc2Decimal(Double d) {
		 DecimalFormat df =  new DecimalFormat("#.##");
		 String s = df.format(d);
		 return Double.valueOf(s);
	 }
	 public static String calenderToDate(Calendar cal) {
			SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
			String dateName = "";
			if (cal != null) {
				dateName = simpleDate.format(cal.getTime());
			}
			return dateName;
		}
}
