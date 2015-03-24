/**
 * @author Kimi
 */
package com.star.service;

import com.example.zakerdemo.MainActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

/**
 * @author Kimi
 * 
 */
public class BootBroadcast extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent mintent) {
//		String action = "android.intent.action.MAIN";
//		String category = "android.intent.category.LAUNCHER";
//		Intent myi = new Intent(context, MainActivity.class);
//		myi.setAction(action);
//		myi.addCategory(category);
//		myi.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		context.startActivity(myi);
		if (Intent.ACTION_BOOT_COMPLETED.equals(mintent.getAction())) {
			// 启动完成
			Intent intent = new Intent(context, AlarmReceiver.class);
			intent.setAction("arui.alarm.action");
			PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
			long firstime = SystemClock.elapsedRealtime();
			AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

			// 10秒一个周期，不停的发送广播
			am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstime, 10 * 1000, sender);
		}

	}
}
