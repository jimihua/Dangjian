package com.star.base;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.view.WindowCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.zakerdemo.R;
import com.google.gson.Gson;
import com.star.common.AppManager;

@SuppressWarnings("all")
public class BaseActionActivity<T> extends ActionBarActivity {

	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private boolean allowDestroy = true;
	private boolean allowFullScreen = true;
	protected String TAG = this.getClass().getSimpleName();
	protected Gson gson;
	protected boolean isFirst = true;
	protected ProgressDialog mProgressDialog = null;
	protected List<T> mList = new ArrayList<T>();
	protected CommonAdapter mAdapter;
	protected Document doc;
	BroadcastReceiver connectionReceiver = new BroadcastReceiver() {
		public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent) {
			ConnectivityManager localConnectivityManager = (ConnectivityManager) BaseActionActivity.this.getSystemService("connectivity");
			NetworkInfo localNetworkInfo1 = localConnectivityManager.getNetworkInfo(0);
			NetworkInfo localNetworkInfo2 = localConnectivityManager.getNetworkInfo(1);
			// if ((!localNetworkInfo1.isConnected()) &&
			// (!localNetworkInfo2.isConnected()))
			// {
			// BaseActivity.this.setNerWorking(false);
			// WarnUtils.toast(BaseActivity.this.mContext, "获取不到网络数据，请检查网络");
			// return;
			// }
			BaseActionActivity.this.setNerWorking(true);
		}
	};

	private boolean isNerWorking = false;
	protected Context mContext;
	private View view;

	public boolean isAllowFullScreen() {
		return this.allowFullScreen;
	}

	public boolean isNerWorking() {
		return this.isNerWorking;
	}

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		supportRequestWindowFeature(WindowCompat.FEATURE_ACTION_BAR);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(true); // 决定左上角图标的右侧是否有向左的小箭头, true
													// 有小箭头，并且图标可以点击
		actionBar.setDisplayShowHomeEnabled(false);
		// 使左上角图标是否显示，如果设成false，则没有程序图标，仅仅就个标题，
		// 否则，显示应用程序图标，对应id为android.R.id.home，对应ActionBar.DISPLAY_SHOW_HOME

		// force use of overflow menu on devices with menu button
		// 在actionbar中显示溢出菜单选项
		// http://stackoverflow.com/questions/9286822/how-to-force-use-of-overflow-menu-on-devices-with-menu-button

		this.mContext = this;
		this.allowFullScreen = true;
		AppManager.getAppManager().addActivity(this);
		this.gson = new Gson();

		IntentFilter localIntentFilter = new IntentFilter();
		localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
		registerReceiver(this.connectionReceiver, localIntentFilter);

	}

	protected void onDestroy() {
		super.onDestroy();
		if (this.connectionReceiver != null)
			unregisterReceiver(this.connectionReceiver);
		AppManager.getAppManager().finishActivity(this);
	}

	public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
		if ((paramInt == 4) && (this.view != null)) {
			this.view.onKeyDown(paramInt, paramKeyEvent);
			if (!this.allowDestroy)
				return false;
		}
		return super.onKeyDown(paramInt, paramKeyEvent);
	}

	public void onPause() {
		super.onPause();

	}

	public void onResume() {
		super.onResume();

	}

	protected void onStart() {
		super.onStart();
	}

	public void setAllowDestroy(boolean paramBoolean) {
		this.allowDestroy = paramBoolean;
	}

	public void setAllowDestroy(boolean paramBoolean, View paramView) {
		this.allowDestroy = paramBoolean;
		this.view = paramView;
	}

	public void setAllowFullScreen(boolean paramBoolean) {
		this.allowFullScreen = paramBoolean;
	}

	public void setNerWorking(boolean paramBoolean) {
		this.isNerWorking = paramBoolean;
	}

	/*
	 * @author Kimi
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub

		return super.onCreateOptionsMenu(menu);
	}

	/*
	 * @author Kimi
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
