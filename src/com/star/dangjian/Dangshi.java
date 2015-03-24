package com.star.dangjian;

import java.util.ArrayList;

import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.example.zakerdemo.R;
import com.star.base.BaseActivity;
import com.star.db.DBManager;
import com.star.tools.MyUtil;
import com.star.utils.MyWebView;

public class Dangshi extends BaseActivity {

	private SQLiteDatabase database;
	private String url;
	private ArrayList<String> todayDs;
	private Context mContext;
	@ViewInject(id = R.id.webview)
	MyWebView webview;

	private void initView() {
		// TODO Auto-generated method stub

		LinearLayout.LayoutParams mWebViewLP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
		webview.setLayoutParams(mWebViewLP);
		webview.setInitialScale(25);
		WebSettings settings = webview.getSettings();
		// 适应屏幕
		settings.setUseWideViewPort(true);
		settings.setSupportZoom(true);
		// settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		// settings.setLoadWithOverviewMode(true);
		settings.setBuiltInZoomControls(true);

		database = SQLiteDatabase.openOrCreateDatabase(DBManager.DB_PATH + "/" + DBManager.DB_NAME, null);
		todayDs = getUrl(mContext);
		if (todayDs != null) {
			url = todayDs.get(0);
		} else {
			url = "http://www.baidu.com";
		}
		database.close();
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

	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.star_dangshi_main);
		mContext = this;
		initView();
		
		webview.loadUrl(url);

	}

}
