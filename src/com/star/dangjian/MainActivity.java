package com.star.dangjian;

import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.games.game2048.MainGameActivity;
import com.example.zakerdemo.R;
import com.star.base.BaseActivity;
import com.star.db.DBManager;
import com.star.service.ActService;
import com.star.tools.MyUtil;

public class MainActivity extends BaseActivity {

	/**
	 * @author Kimi 新闻
	 */
	@ViewInject(id = R.id.news_layout, click = "onClick")
	RelativeLayout news_layout;

	/**
	 * @author Kimi 党史上的今天
	 */
	@ViewInject(id = R.id.history_layout, click = "onClick")
	RelativeLayout history_layout;
	/**
	 * 练习
	 * 
	 * @author Kimi
	 */
	@ViewInject(id = R.id.pratice_layout, click = "onClick")
	RelativeLayout pratice_layout;

	@ViewInject(id = R.id.game_layout, click = "onClick")
	RelativeLayout game_layout;

	/**
	 * 习大大
	 * 
	 * @author Kimi
	 */
	@ViewInject(id = R.id.xidada_layout, click = "onClick")
	RelativeLayout xidada_layout;
	private DBManager dbHelper;
	/*
	 * @author Kimi
	 */
	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		setContentView(R.layout.activity_pm_main);
		dbHelper = new DBManager(mContext);
		dbHelper.openDatabase();
		dbHelper.closeDatabase();
		Intent service = new Intent();
		service.setClass(mContext, ActService.class);
		startService(service);
	}



	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.game_layout:
			MyUtil.JumpPages(mContext, MainGameActivity.class, "2048");
			break;
		case R.id.news_layout:
			MyUtil.JumpPages(mContext, SchoolNewsActivity.class, "school");
			//MyUtil.JumpPages(mContext, NewsActivity.class, "news");
			break;
		case R.id.history_layout:
			MyUtil.JumpPages(mContext, Dangshi.class, "dangshi");
			break;
		case R.id.xidada_layout:
			MyUtil.JumpPages(mContext, XiDaDaActivity.class, "2048");
			break;
		case R.id.pratice_layout:
			MyUtil.JumpPages(mContext, Practice.class, "practice", "makesi");
			break;
		default:
			break;
		}
	}
}
