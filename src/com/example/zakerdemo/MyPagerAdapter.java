package com.example.zakerdemo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.games.game2048.MainGameActivity;
import com.star.dangjian.DangZhang;
import com.star.dangjian.Dangshi;
import com.star.dangjian.NewsActivity;
import com.star.dangjian.Practice;
import com.star.dangjian.SchoolNewsActivity;
import com.star.dangjian.XiDaDaActivity;
import com.star.tools.MyUtil;

/**
 * 自定义适配器
 * 
 * @author wulianghuan
 * 
 */
public class MyPagerAdapter extends PagerAdapter {
	Vibrator vibrator;
	ArrayList<ImageInfo> data;
	Activity activity;
	LayoutParams params;

	public MyPagerAdapter(Activity activity, ArrayList<ImageInfo> data) {
		this.activity = activity;
		this.data = data;
		vibrator = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, final int index) {
		MyUtil.Log(index);

		View view = activity.getLayoutInflater().inflate(R.layout.grid, null);
		GridView gridView = (GridView) view.findViewById(R.id.gridView1);
		gridView.setNumColumns(2);
		gridView.setVerticalSpacing(5);
		gridView.setHorizontalSpacing(5);
		gridView.setAdapter(new BaseAdapter() {

			@Override
			public int getCount() {
				return 6;
			}

			@Override
			public Object getItem(int position) {
				return position;
			}

			@Override
			public long getItemId(int position) {
				return position;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View item = LayoutInflater.from(activity).inflate(R.layout.grid_item, null);
				ImageView iv = (ImageView) item.findViewById(R.id.news);
				RelativeLayout relativeLayout = (RelativeLayout) item.findViewById(R.id.relativeLayout);
				iv.setImageResource((data.get(position)).imageId);
				relativeLayout.setBackgroundResource((data.get(position)).bgId);
				relativeLayout.getBackground().setAlpha(225);
				TextView tv = (TextView) item.findViewById(R.id.msg);
				tv.setText((data.get(position)).imageMsg);
				return item;
			}
		});

		gridView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				View view = arg1;
				arg1.setVisibility(View.INVISIBLE);
				MyUtil.Log(arg3);
				params = new WindowManager.LayoutParams();
				// activity.getWindowManager().addView(view, params);
				vibrator.vibrate(2500);
				return true;
			}
		});

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					MyUtil.JumpPages(activity, Dangshi.class, "dangshi");
					break;
				case 1:
					MyUtil.JumpPages(activity, NewsActivity.class, "news");
					break;
				case 2:
					MyUtil.JumpPages(activity, Practice.class, "practice", "makesi");
					break;
				case 3:
					MyUtil.JumpPages(activity, SchoolNewsActivity.class, "school");
					break;
				case 4:

					MyUtil.JumpPages(activity, MainGameActivity.class, "2048");
					break;
				case 5:
					MyUtil.JumpPages(activity, XiDaDaActivity.class, "2048");
					break;
				default:
					break;
				}

			}
		});
		// gridView.setOnTouchListener(new View.OnTouchListener() {
		//
		// @Override
		// public boolean onTouch(View v, MotionEvent event) {
		//
		// return true;
		// }
		// });
		((ViewPager) container).addView(view);

		return view;
	}
}
