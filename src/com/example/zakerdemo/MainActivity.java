package com.example.zakerdemo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.widget.TextView;

import com.star.db.DBManager;
import com.star.service.ActService;

public class MainActivity extends Activity {
	ArrayList<ImageInfo> data; // 菜单数据
	private DBManager dbHelper;
	private Context mContext;
	private int noteid = 201499;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mContext = this;
		dbHelper = new DBManager(mContext);
		dbHelper.openDatabase();
		dbHelper.closeDatabase();
		Intent service = new Intent();
		service.setClass(mContext, ActService.class);
		startService(service);
		// 初始化数据
		initData();
		ViewPager vpager = (ViewPager) findViewById(R.id.vPager);
		vpager.setAdapter(new MyPagerAdapter(MainActivity.this, data));
		vpager.setPageMargin(50);

	}

	private void initData() {
		data = new ArrayList<ImageInfo>();

		data.add(new ImageInfo("党的今天", R.drawable.rct256, R.drawable.icon_bg02));
		data.add(new ImageInfo("党的新闻", R.drawable.rct2048, R.drawable.icon_bg02));
		data.add(new ImageInfo("马哲练习", R.drawable.icon3, R.drawable.icon_bg02));
		data.add(new ImageInfo("党章练习", R.drawable.icon4, R.drawable.icon_bg02));
		data.add(new ImageInfo("党的2048", R.drawable.rct1024, R.drawable.icon_bg02));
		data.add(new ImageInfo("党章", R.drawable.rct128, R.drawable.icon_bg02));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
