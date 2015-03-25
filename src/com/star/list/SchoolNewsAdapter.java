/**
 * @author Kimi
 */
package com.star.list;

import java.util.List;

import com.example.zakerdemo.R;
import com.star.base.CommonAdapter;
import com.star.model.News;

import android.content.Context;
import android.widget.BaseAdapter;

/**
 * @author Kimi
 * 
 */
public class SchoolNewsAdapter extends CommonAdapter<News> {

	/**
	 * @param mContext
	 * @param mDatas
	 * @param itemLayoutId
	 */
	public SchoolNewsAdapter(Context mContext, List<News> mDatas, int itemLayoutId) {
		super(mContext, mDatas, itemLayoutId);
		// TODO Auto-generated constructor stub
	}

	/*
	 * @author Kimi
	 */
	@Override
	public void convert(ViewHolder mViewHolder, News news) {
		// TODO Auto-generated method stub
		mViewHolder.setText(R.id.news_title, news.getTitle());
		mViewHolder.setText(R.id.news_des, news.getDes());

	}

}
