/**
 * @author Kimi
 */
package com.star.list;

import java.util.List;

import android.content.Context;

import com.example.zakerdemo.R;
import com.star.base.CommonAdapter;
import com.star.model.YuLu;

/**
 * @author Kimi
 * 
 */
public class XddFazhiAdapter extends CommonAdapter<YuLu> {

	/**
	 * @param mContext
	 * @param mDatas
	 * @param itemLayoutId
	 */
	public XddFazhiAdapter(Context mContext, List<YuLu> mDatas, int itemLayoutId) {
		super(mContext, mDatas, itemLayoutId);
		// TODO Auto-generated constructor stub
	}

	/*
	 * @author Kimi
	 */
	@Override
	public void convert(ViewHolder mViewHolder, YuLu yulu) {
		// TODO Auto-generated method stub
		mViewHolder.setText(R.id.word, yulu.getWord());
		mViewHolder.setText(R.id.origin, yulu.getOrigin());


	}

}
