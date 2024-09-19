package com.tw.utility;
import java.text.DecimalFormat;
import java.math.RoundingMode;

public class DecimalNumber {

	private static DecimalFormat df2 = new DecimalFormat(".##");
	
	public static double converttoDecimal(double num) {
		String number =df2.format(num);
		double decimal=Double.parseDouble(number);
		return decimal;
	}
	
	public static double roundnumberup(double num) {
		df2.setRoundingMode(RoundingMode.UP);
		String number =df2.format(num);
		double decimal=Double.parseDouble(number);
		return decimal;
	}
	
	public static double roundnumberdown(double num) {
		df2.setRoundingMode(RoundingMode.DOWN);
		String number =df2.format(num);
		double decimal=Double.parseDouble(number);
		return decimal;
	}
}
