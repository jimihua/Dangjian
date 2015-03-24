package com.star.service;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;

import com.star.base.BaseService;
import com.star.db.DBManager;
import com.star.note.MyNote;
import com.star.tools.MyUtil;

/**
 * 方法描述: 服务器推送消息
 * 
 * @param
 * @author KIMI 创建时间：2014-12-14 上午11:07:29
 * @version
 * 
 */
@SuppressWarnings("all")
public class ActService extends BaseService {
	/** 创建参数 */
	boolean threadDisable = false;
	int count;
	private SQLiteDatabase database;
	private String url = "http://www.baidu.com";
	private ArrayList<String> todayDs;
	private ArrayList<String> dsjt = new ArrayList<String>();
	private int noteid = 201499;
	NotificationManager notificationManager;

	private static Thread mThread;

	public void handleMessage(Message msg) {
		// process incoming messages here
	};

	public IBinder onBind(Intent intent) {
		return null;
	}

	public void onCreate() {

		super.onCreate();
		mContext = this;

		notificationManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
		/** 创建一个线程，每秒计数器加一，并在控制台进行Log输出 */
		getItems();

	}

	public void onDestroy() {
		super.onDestroy();
		/** 服务停止时，终止计数进程 */
		this.threadDisable = true;
		Intent localIntent = new Intent();
		localIntent.setClass(this, ActService.class); // 销毁时重新启动Service
		this.startService(localIntent);
	}

	public int getConunt() {
		return count;
	}

	/*
	 * @author Kimi
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return START_STICKY;
	}

	class ServiceBinder extends Binder {
		public ActService getService() {
			return ActService.this;
		}
	}

	private void getItems() {
		// TODO Auto-generated method stub

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				while (true) {
					database = SQLiteDatabase.openOrCreateDatabase(DBManager.DB_PATH + "/" + DBManager.DB_NAME, null);
					todayDs = getUrl(mContext);
					if (todayDs != null) {
						url = todayDs.get(0);
						Document doc = null;
						database.close();
						try {

							doc = Jsoup.connect(url).get();
							MyUtil.Log(doc.title());
							Elements paragraphs = doc.select("p");
							Elements titles = doc.select("h2");
							for (Element t : titles) {
								String param = null;
								if (t.toString().contains("<h2>") && !t.toString().contains("img")) {
									param = t.toString().replace("<h2>", "");
									param = param.replace("</h2>", "");
									param = param.replace("&nbsp;", " ");
									dsjt.add(param);
									new MyNote(mContext).addNote(noteid++, param);
								}

							}

							for (Element p : paragraphs) {
								String param = null;
								if (p.toString().contains("<p>")) {
									param = p.toString().substring(3);
								}
							}

							try {
								Thread.sleep(1000 * 3600*12);
							
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();

							}

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();

						}

					}
				}
			}
		};
		mThread = new Thread(runnable);
		mThread.start();

	}

	private ArrayList<String> getUrl(Context mContext) {
		// TODO Auto-generated method stub
		ArrayList<String> urls = new ArrayList<String>();

		String date = MyUtil.getDate(mContext);
		String sql = "select * from dangshi where date=" + date;
		Cursor cursor = database.rawQuery(sql, null);
		if (cursor != null) {

			while (cursor.moveToNext()) {
				String link = cursor.getString(cursor.getColumnIndex("link"));
				urls.add(link);
			}
			return urls;
		} else {

			return null;
		}
	}

}
