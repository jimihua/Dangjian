package com.star.tools;

import java.io.Serializable;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.star.utils.WarnUtils;

public class MyUtil {
	public static int LENGTH_LONG = 1;
	public static int LENGTH_SHORT = 0;
	public static int res = 0;
	private static Toast toast = null;

	public static void CallPhone(Context paramContext, String paramString) {
		paramContext.startActivity(new Intent("android.intent.action.CALL", Uri
				.parse("tel:" + paramString)));
	}

	public static void ImageToast(Context paramContext, int paramInt1,
			CharSequence paramCharSequence, int paramInt2) {
		toast = Toast.makeText(paramContext, paramCharSequence, 1);
		toast.setGravity(17, 0, 0);
		View localView = toast.getView();
		ImageView localImageView = new ImageView(paramContext);
		localImageView.setImageResource(paramInt1);
		LinearLayout localLinearLayout = new LinearLayout(paramContext);
		localLinearLayout.addView(localImageView);
		localLinearLayout.addView(localView);
		toast.setView(localLinearLayout);
		toast.show();
	}

	public static void JumpPages(Context paramContext, Class<?> paramClass,
			String paramString) {
		Intent localIntent = new Intent();
		localIntent.setClass(paramContext, paramClass);
		localIntent.putExtra(paramString, true);
		paramContext.startActivity(localIntent);
	}

	
	/**
	 * @param paramContext
	 * @param paramClass
	 * @param name 传值得名称
	 * @param value 传值得内容
	 */
	public static void JumpPages(Context paramContext, Class<?> paramClass,
			String name,String value) {
		Intent localIntent = new Intent();
		localIntent.setClass(paramContext, paramClass);
		localIntent.putExtra(name,value);
		paramContext.startActivity(localIntent);
	}
	public static void JumpPages(Context paramContext, Class<?> paramClass,
			String paramString, int paramInt) {
		Intent localIntent = new Intent();
		localIntent.setClass(paramContext, paramClass);
		localIntent.putExtra(paramString, paramInt);
		paramContext.startActivity(localIntent);
	}

	public static void JumpPages(Context paramContext, Class<?> paramClass,
			String paramString, Serializable serializable) {
		Intent localIntent = new Intent();
		localIntent.setClass(paramContext, paramClass);
		localIntent.putExtra(paramString, serializable);
		paramContext.startActivity(localIntent);
	}

	

	public static void JumpPages(Context paramContext, Class<?> paramClass,
			String paramString, ArrayList<String> paramArrayList) {
		Intent localIntent = new Intent();
		localIntent.setClass(paramContext, paramClass);
		localIntent.putStringArrayListExtra(paramString, paramArrayList);
		paramContext.startActivity(localIntent);
	}

	public static void JumpPages(Context paramContext, Class<?> paramClass,
			String paramString, String[] paramArrayOfString) {
		Intent localIntent = new Intent();
		localIntent.setClass(paramContext, paramClass);
		localIntent.putExtra(paramString, paramArrayOfString);
		paramContext.startActivity(localIntent);
	}

	public static void SendMsg(Context paramContext, String paramString1,
			String paramString2) {
		Intent localIntent = new Intent("android.intent.action.SENDTO",
				Uri.parse("smsto:" + paramString1));
		localIntent.putExtra("sms_body", paramString2);
		paramContext.startActivity(localIntent);
	}

	public static void ShowWindows(Context paramContext, String paramString1,
			String paramString2) {
		AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
		localBuilder.setTitle(paramString1);
		localBuilder.setMessage(paramString2);
		localBuilder.setPositiveButton("确认",
				new DialogInterface.OnClickListener() {
					public void onClick(
							DialogInterface paramAnonymousDialogInterface,
							int paramAnonymousInt) {
						paramAnonymousDialogInterface.dismiss();
					}
				});
		localBuilder.create().show();
	}

	public static void TextToast(Context paramContext,
			CharSequence paramCharSequence, int paramInt) {
		toast = Toast.makeText(paramContext, paramCharSequence, paramInt);
		toast.setGravity(17, 0, 0);
		toast.show();
	}

	public static String insertComma(String money, int length) {
		if ((money == null) || (money.length() < 1))
			return "";
		if ("0".equals(money))
			return "0.00";
		if ("0.0000".equals(money))
			return "0.00";
		double d = Double.parseDouble(money);
		DecimalFormat localDecimalFormat = null;
		if (length == 0) {
			localDecimalFormat = new DecimalFormat("###,###");
			return localDecimalFormat.format(d);
		}
		StringBuffer localStringBuffer = new StringBuffer();
		localStringBuffer.append("###,###.00");
		for (int i = 0; i <= length; i++) {
			localStringBuffer.append("#");
			localDecimalFormat = new DecimalFormat(localStringBuffer.toString());
		}
		return localDecimalFormat.format(d);
	}

	public static void showJumpDialog(final Context paramContext,
			String paramString1, String paramString2, Activity paramActivity) {

	}

	public static boolean checkNulls(Context context, EditText editText) {
		boolean bool = true;
		String str = editText.getText().toString();
		if (str == null) {
			bool = true;
		}
		if (str.length() >= 1) {
			bool = false;

		}
		if (bool)
			ShowWindows(context, "提示", editText.getHint() + "必须提供！");
		return bool;

	}

	public static boolean checkNulls(Context context, TextView textView) {
		boolean bool = true;
		String str = textView.getText().toString();
		if (str == null) {
			bool = true;
		}
		if (str.length() >= 1) {
			bool = false;

		}
		if (bool)
			ShowWindows(context, "提示", textView.getHint() + "必须提供！");
		return bool;

	}

	public static boolean checkSubmit(Context context, TextView textView,
			String str) {
		boolean bool = true;
		if (TextUtils.isEmpty(textView.getText().toString().trim())) {
			WarnUtils.toast(context, str);
			bool = true;
		} else {
			bool = false;
		}
		return bool;
	}

	public static String getDate(Context context) {
		final Calendar calendar = Calendar.getInstance();
		String date = (calendar.get(Calendar.MONTH)+1) + "."
				+ calendar.get(Calendar.DAY_OF_MONTH);

		return date;
	}

	
	
	
	 /**
	 * 方法描述:   输出error 日志信息
	 * @param  
	 * @author KIMI
	 * 创建时间：2014-10-17 上午11:44:18   
	 * @version    
	 *    
	*/
	public static String Log(Object log) {
		Log.e("-------->>>>" + log, String.valueOf(log));
		return String.valueOf(log);

	}

}
