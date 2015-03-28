package com.star.dangjian;

import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

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
	@ViewInject(id = R.id.news, click = "onClick")
	ImageView news;

	/**
	 * @author Kimi 党史上的今天
	 */
	@ViewInject(id = R.id.history, click = "onClick")
	ImageView history;
	/**
	 * 练习
	 * 
	 * @author Kimi
	 */
	@ViewInject(id = R.id.exercise, click = "onClick")
	ImageView exercise;

	@ViewInject(id = R.id.game, click = "onClick")
	ImageView game;

	/**
	 * 习大大
	 * 
	 * @author Kimi
	 */
	@ViewInject(id = R.id.xidada, click = "onClick")
	ImageView xidada;
	private DBManager dbHelper;
	/*
	 * @author Kimi
	 */
	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		setContentView(R.layout.activity_center);
		dbHelper = new DBManager(mContext);
		dbHelper.openDatabase();
		dbHelper.closeDatabase();
		Intent service = new Intent();
		service.setClass(mContext, ActService.class);
		startService(service);
	}



	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.game:
			MyUtil.JumpPages(mContext, MainGameActivity.class, "2048");
			break;
		case R.id.news:
			MyUtil.JumpPages(mContext, SchoolNewsActivity.class, "school");
			//MyUtil.JumpPages(mContext, NewsActivity.class, "news");
			break;
		case R.id.history:
			MyUtil.JumpPages(mContext, Dangshi.class, "dangshi");
			break;
		case R.id.xidada:
			MyUtil.JumpPages(mContext, XiDaDaActivity.class, "2048");
			break;
		case R.id.exercise:
			MyUtil.JumpPages(mContext, Practice.class, "practice", "makesi");
			break;
		default:
			break;
		}
	}
}
