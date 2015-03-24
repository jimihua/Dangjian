package com.star.common;

import java.util.regex.Pattern;

import android.text.TextUtils;

public class StringUtils {
	/*
	 * public static String IDCardMatch(String paramString) { int i = 0;
	 * String[] arrayOfString1 = { "1", "0", "x", "9", "8", "7", "6", "5", "4",
	 * "3", "2" }; String[] arrayOfString2 = { "7", "9", "10", "5", "8", "4",
	 * "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2" }; String str1 =
	 * ""; if ((paramString.length() == 0) || ("".equals(paramString))) return
	 * "请填写身份证"; if ((paramString.length() != 15) && (paramString.length() !=
	 * 18)) return "身份证号码长度应该为15位或18位。"; if (paramString.length() == 18) str1 =
	 * paramString.substring(0, 17); while (!isNumeric(str1)) { return
	 * "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。"; } if (paramString.length() == 15)
	 * str1 = paramString.substring(0, 6) + "19" + paramString.substring(6, 15);
	 * 
	 * String str2 = str1.substring(6, 10); String str3 = str1.substring(10,
	 * 12); String str4 = str1.substring(12, 14); GregorianCalendar
	 * localGregorianCalendar = new GregorianCalendar(); SimpleDateFormat
	 * localSimpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd"); try { try {
	 * if ((localGregorianCalendar.get(1) - Integer.parseInt(str2) >= 100) ||
	 * (localGregorianCalendar.getTime().getTime() -
	 * localSimpleDateFormat.parse( str2 + "-" + str3 + "-" + str4) .getTime() <
	 * 0L)) return "身份证生日不在有效范围。"; } catch (java.text.ParseException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); } } catch
	 * (NumberFormatException localNumberFormatException) {
	 * localNumberFormatException.printStackTrace(); if ((Integer.parseInt(str3)
	 * > 12) || (Integer.parseInt(str3) == 0)) return "身份证月份无效"; } catch
	 * (ParseException localParseException) { if ((Integer.parseInt(str4) > 31)
	 * || (Integer.parseInt(str4) == 0)) return "身份证日期无效";
	 * 
	 * if (!isDate(str2 + "-" + str3 + "-" + str4)) return "身份证生日无效。";
	 * 
	 * if (isProvince().get(str1.substring(0, 2)) == null) return "身份证地区编码错误。";
	 * for (int j = 0;; j++) { if (j >= 17) { String str5 = arrayOfString1[(i %
	 * 11)]; String str6 = str1 + str5; if (paramString.length() != 18) break;
	 * if (str6.equals(paramString.replace("X", "x"))) break; return
	 * "身份证无效，不是合法的身份证号码"; } i +=
	 * Integer.parseInt(String.valueOf(str1.charAt(j)))
	 * Integer.parseInt(arrayOfString2[j]); } return ""; } return "";
	 * 
	 * }
	 */
	public static String isIDCard(String paramString) {

		if ((paramString.length() == 0) || ("".equals(paramString)))
			return "请填写身份证";
		if ((paramString.length() != 15) && (paramString.length() != 18))
			return "身份证号码长度应该为15位或18位。";

		if (paramString.length() == 18)
			if (!Pattern
					.compile(
							"^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$")
					.matcher(paramString).matches()) {
				return "身份证号码填写有问题";
			}
		if (paramString.length() == 15)
			if (!Pattern
					.compile(
							"^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$")
					.matcher(paramString).matches()) {
				return "身份证号码填写有问题";
			}

		return "";

	}

	public static boolean isDate(String paramString) {
		return Pattern
				.compile(
						"^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$")
				.matcher(paramString).matches();
	}

	public static boolean isNumeric(String numberic) {
		return Pattern.compile("[0-9]*").matcher(numberic).matches();
	}

	public static boolean isEmail(String email) {
		return Pattern.compile("\\w+@\\w+\\.(com\\.cn)|\\w+@\\w+\\.(com|cn)")
				.matcher(email).matches();
	}

	public static String isPhoneNumber(String phoneNumber) {
		if (TextUtils.isEmpty(phoneNumber)) {
			return "请填写手机号码";
		}
		if (!Pattern.compile("1[3,4,5,8]{1}\\d{9}").matcher(phoneNumber)
				.matches()) {
			return "手机号码格式有问题";
		}
		return "";
	}

	public static String isMoney(String money) {
		String string = "";
		if (TextUtils.isEmpty(money)) {
			return string = "借款金额不能为空";
		} else if (!isNumeric(money)) {
			return string = "借款金额格式出错";
		}

		return string;

	}

}
