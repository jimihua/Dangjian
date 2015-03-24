package com.star.tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

public class PhoneInfo {
	private static final String FILE_CPU = "/proc/cpuinfo";
	private static final String FILE_MEMORY = "/proc/meminfo";
	private static final String TAG = PhoneInfo.class.getSimpleName();
	public String mConnectTypeName;
	public String mCupInfo;
	public long mFreeMem;
	public String mIMEI;
	public boolean mIsOnLine;
	public String mManufacturerName;
	public String mModelName;
	public String mNetWorkCountryIso;
	public String mNetWorkOperator;
	public String mNetWorkOperatorName;
	public int mNetWorkType;
	public int mPhoneType;
	public String mProductName;
	public int mSysVersion;
	public long mTotalMem;

	public static String getConnectTypeName(Context paramContext) {
		if (!isOnline(paramContext))
			return "OFFLINE";
		NetworkInfo localNetworkInfo = ((ConnectivityManager) paramContext
				.getSystemService("connectivity")).getActiveNetworkInfo();
		if (localNetworkInfo != null)
			return localNetworkInfo.getTypeName();
		return "OFFLINE";
	}

	public static String getCpuInfo() {
		try {
			String str = new BufferedReader(new FileReader("/proc/cpuinfo"))
					.readLine();
			String[] arrayOfString = str.split(":\\s+", 2);
			for (int i = 0;; i++) {
				if (i >= arrayOfString.length) {
					Log.w(TAG, str);
					return arrayOfString[1];
				}
				//Log.w(TAG, " .....  " + arrayOfString[i]);
			}
		} catch (FileNotFoundException localFileNotFoundException) {
			localFileNotFoundException.printStackTrace();
			return null;
		} catch (IOException localIOException) {
			while (true)
				localIOException.printStackTrace();
		}
	}

	public static long getFreeMem(Context paramContext) {
		ActivityManager localActivityManager = (ActivityManager) paramContext
				.getSystemService("activity");
		ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
		localActivityManager.getMemoryInfo(localMemoryInfo);
		return localMemoryInfo.availMem / 1024L / 1024L;
	}

	public static String getIMEI(Context paramContext) {
		TelephonyManager localTelephonyManager = (TelephonyManager) paramContext
				.getSystemService("phone");
		if (paramContext.getPackageManager().checkPermission(
				"android.permission.READ_PHONE_STATE",
				paramContext.getPackageName()) == 0)
			return localTelephonyManager.getDeviceId();
		return null;
	}

	public static String getManufacturerName() {
		return Build.MANUFACTURER;
	}

	public static String getModelName() {
		return Build.MODEL;
	}

	public static String getNetWorkCountryIso(Context paramContext) {
		return ((TelephonyManager) paramContext.getSystemService("phone"))
				.getNetworkCountryIso();
	}

	public static String getNetWorkOperator(Context paramContext) {
		return ((TelephonyManager) paramContext.getSystemService("phone"))
				.getNetworkOperator();
	}

	public static String getNetWorkOperatorName(Context paramContext) {
		return ((TelephonyManager) paramContext.getSystemService("phone"))
				.getNetworkOperatorName();
	}

	public static int getNetworkType(Context paramContext) {
		return ((TelephonyManager) paramContext.getSystemService("phone"))
				.getNetworkType();
	}

	public static PhoneInfo getPhoneInfo(Context paramContext) {
		PhoneInfo localPhoneInfo = new PhoneInfo();
		localPhoneInfo.mIMEI = getIMEI(paramContext);
		localPhoneInfo.mPhoneType = getPhoneType(paramContext);
		localPhoneInfo.mSysVersion = getSysVersion();
		localPhoneInfo.mNetWorkCountryIso = getNetWorkCountryIso(paramContext);
		localPhoneInfo.mNetWorkOperator = getNetWorkOperator(paramContext);
		localPhoneInfo.mNetWorkOperatorName = getNetWorkOperatorName(paramContext);
		localPhoneInfo.mNetWorkType = getNetworkType(paramContext);
		localPhoneInfo.mIsOnLine = isOnline(paramContext);
		localPhoneInfo.mConnectTypeName = getConnectTypeName(paramContext);
		localPhoneInfo.mFreeMem = getFreeMem(paramContext);
		localPhoneInfo.mTotalMem = getTotalMem(paramContext);
		localPhoneInfo.mCupInfo = getCpuInfo();
		localPhoneInfo.mProductName = getProductName();
		localPhoneInfo.mModelName = getModelName();
		localPhoneInfo.mManufacturerName = getManufacturerName();
		return localPhoneInfo;
	}

	public static int getPhoneType(Context paramContext) {
		return ((TelephonyManager) paramContext.getSystemService("phone"))
				.getPhoneType();
	}

	public static String getProductName() {
		return Build.PRODUCT;
	}

	public static int getSysVersion() {
		return Build.VERSION.SDK_INT;
	}

	public static long getTotalMem(Context paramContext) {
		try {
			String str = new BufferedReader(new FileReader("/proc/meminfo"))
					.readLine();
			String[] arrayOfString = str.split("\\s+");
			//Log.w(TAG, str);
			long l = Long.valueOf(arrayOfString[1]).longValue() / 1024L;
			return l;
		} catch (FileNotFoundException localFileNotFoundException) {
			localFileNotFoundException.printStackTrace();
			return -1L;
		} catch (IOException localIOException) {
			while (true)
				localIOException.printStackTrace();
		}
	}

	public static boolean isOnline(Context paramContext) {
		NetworkInfo localNetworkInfo = ((ConnectivityManager) paramContext
				.getSystemService("connectivity")).getActiveNetworkInfo();
		return (localNetworkInfo != null) && (localNetworkInfo.isConnected());
	}

	public String toString() {
		StringBuilder localStringBuilder = new StringBuilder();
		localStringBuilder.append("IMEI : " + this.mIMEI + "\n");
		localStringBuilder.append("mPhoneType : " + this.mPhoneType + "\n");
		localStringBuilder.append("mSysVersion : " + this.mSysVersion + "\n");
		localStringBuilder.append("mNetWorkCountryIso : "
				+ this.mNetWorkCountryIso + "\n");
		localStringBuilder.append("mNetWorkOperator : " + this.mNetWorkOperator
				+ "\n");
		localStringBuilder.append("mNetWorkOperatorName : "
				+ this.mNetWorkOperatorName + "\n");
		localStringBuilder.append("mNetWorkType : " + this.mNetWorkType + "\n");
		localStringBuilder.append("mIsOnLine : " + this.mIsOnLine + "\n");
		localStringBuilder.append("mConnectTypeName : " + this.mConnectTypeName
				+ "\n");
		localStringBuilder.append("mFreeMem : " + this.mFreeMem + "M\n");
		localStringBuilder.append("mTotalMem : " + this.mTotalMem + "M\n");
		localStringBuilder.append("mCupInfo : " + this.mCupInfo + "\n");
		localStringBuilder.append("mProductName : " + this.mProductName + "\n");
		localStringBuilder.append("mModelName : " + this.mModelName + "\n");
		localStringBuilder.append("mManufacturerName : "
				+ this.mManufacturerName + "\n");
		return localStringBuilder.toString();
	}
}

