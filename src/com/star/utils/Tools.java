package com.star.utils;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.util.Log;

public class Tools {
	private static double tolmoney;
	private static double servrate;
	private static int borrowtime;
	private static float mouthrate;
	private static DecimalFormat df = new java.text.DecimalFormat("#.##");

	public static String getMonthMoney(String money, String serrate, String time) {
		tolmoney = Double.valueOf(money);
		servrate = Float.valueOf(serrate);
		borrowtime = Integer.valueOf(time);
		double monthmoney = tolmoney / borrowtime + tolmoney * servrate / 100;
		Log.e("monthmoney", String.valueOf(monthmoney));
		return String.valueOf(df.format(monthmoney));

	}

	public static String getSumMoney(String money, String serrate, String time,
			String mrate) {
		tolmoney = Double.valueOf(money);
		servrate = Float.valueOf(serrate);
		borrowtime = Integer.valueOf(time);
		mouthrate = Float.valueOf(mrate) / 100;

		double monthMoney = Double.valueOf(getMonthMoney(money, serrate, time));

		double summoney = monthMoney
				* ((Math.pow(1.0 + mouthrate, borrowtime) - 1)) / mouthrate
				/ Math.pow(1.0 + mouthrate, borrowtime);
		Log.e("summoney", String.valueOf(summoney));
		return String.valueOf(df.format(summoney));

	}

	public static String fileRename(String path, String type, String time) {
		String renamePath = null;
		if (path != null) {
			renamePath = path.substring(path.lastIndexOf("/") + 1) + type
					+ time + ".png";
		}

		return renamePath;
	}

	public static String getPicType(String type) {
		String picType = null;
		switch (Integer.valueOf(type)) {
		case 1:
			picType = "idhead";
			break;

		case 2:
			picType = "idback";
			break;

		case 3:
			picType = "bankwater";
			break;

		case 4:
			picType = "creditreport";
			break;

		default:
			break;
		}

		return picType;
	}

	public static String numFormat(double Number) {
		return String.valueOf(df.format(Number));
	}

	/***
	 * 澧炲姞澶╂暟
	 * 
	 * 
	 */

	public static Date addDate(Date date, int days) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);// 鎶婃棩鏈熷線鍚庡鍔犱竴澶�鏁存暟寰�悗鎺�璐熸暟寰�墠绉诲姩
		date = calendar.getTime(); // 杩欎釜鏃堕棿灏辨槸鏃ユ湡寰�悗鎺ㄤ竴澶╃殑缁撴灉
		return date;
	}

	public static String receipts(String money, String serrate) {
		tolmoney = Double.valueOf(money);
		servrate = Double.valueOf(serrate);

		double receipt = tolmoney * servrate / 100;

		return String.valueOf(df.format(receipt));

	}

}
