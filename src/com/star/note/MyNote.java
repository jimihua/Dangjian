package com.star.note;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.widget.RemoteViews;

import com.example.zakerdemo.R;
import com.star.dangjian.Dangshi;

public class MyNote {
	private Context mContext;
	private NotificationManager manager;

	public MyNote(Context mContext) {
		this.mContext = mContext;
		manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

	}

	public void addNote(int noteid, String contextText) {
		Notification mNotification = new Notification();
		Intent intent = new Intent(mContext, Dangshi.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_ONE_SHOT);
		// 点击状态栏的图标出现的提示信息设置

		RemoteViews contentView = new RemoteViews(mContext.getPackageName(), R.layout.star_dangshi_note);
		contentView.setImageViewResource(R.id.ds_img_image, R.drawable.ic_launcher);
		contentView.setTextViewText(R.id.ds_tv_bt, "党史上的今天");
		contentView.setTextViewText(R.id.ds_tv_nr, contextText);
		BigTextStyle textStyle = new BigTextStyle();
		textStyle.setBigContentTitle("党史上的今天").setSummaryText("党史上的今天")
				.bigText("激素就是高度分化的内分泌细胞合成并直接分泌入血的化学信息物质，它通过调节各种组织细胞的代谢活动来影响人体的生理活动。由内分泌腺或内分泌细胞分泌的高效生物活性物质，在体内作为信使传递信息，对机体生理过程起调节作用的物质称为激素。它是我们生命中的重要物质。");
		Builder builder = new Notification.Builder(mContext);
		builder.setTicker("党史上的今天");
		builder.setSmallIcon(R.drawable.ic_launcher);
		builder.setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_launcher));
		// builder.setContentTitle("党史上的今天");
		// builder.setContentText("激素就是高度分化的内分泌细胞合成并直接分泌入血的化学信息物质，它通过调节各种组织细胞的代谢活动来影响人体的生理活动。由内分泌腺或内分泌细胞分泌的高效生物活性物质，在体内作为信使传递信息，对机体生理过程起调节作用的物质称为激素。它是我们生命中的重要物质。");
		builder.setContentIntent(pendingIntent);
		// builder.setStyle(textStyle);
		builder.setContent(contentView);
		builder.setAutoCancel(true);
		mNotification = builder.build();

		manager.notify(noteid, mNotification);

	}
}
