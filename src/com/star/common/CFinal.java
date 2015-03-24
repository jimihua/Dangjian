package com.star.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import net.tsz.afinal.FinalHttp;

public class CFinal extends FinalHttp {
	private Context mContext;
	private static SharedPreferences sp;

	public static final FinalHttp fh = new FinalHttp();
	
	private static String[] putMsg;

	public static void setPutMsg(String[] putMsg) {
		// TODO Auto-generated method stub
		CFinal.putMsg = putMsg;
	}

	public static void setFromClasses(Class[] classes) {
		// TODO Auto-generated method stub
	}

	public static String[] getPutMsg() {
		// TODO Auto-generated method stub
		return putMsg;
	}

	public static String GetComXml(Context mContext, String key) {
		// TODO Auto-generated method stub
		sp = mContext.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
		return sp.getString(key, null);
	}

	public static boolean SetComXml(Context mContext, String key, String string) {
		// TODO Auto-generated method stub
		sp = mContext.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString(key, string);
		return editor.commit();
	}

}
