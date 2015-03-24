/**   
 *    
 * 项目名称：Dangjian     
 * 方法描述:   
 * 创建人：Administrator   
 * 创建时间：2015-3-24 下午1:12:04   
 * 修改人：Administrator   
 * 修改时间：2015-3-24 下午1:12:04   
 * 修改备注：   
 * @version    
 *    
 */
package com.star.list;

import java.util.List;

import android.content.Context;

import com.example.zakerdemo.R;
import com.star.base.CommonAdapter;
import com.star.model.News;

/**
 * 方法描述:
 * 
 * @param
 * @author KIMI 创建时间：2015-3-24 下午1:12:04
 * @version
 * 
 */
public class NewsAdapter extends CommonAdapter<News> {

	private Context mContext;

	/**
	 * @param mContext
	 * @param mDatas
	 * @param itemLayoutId
	 */
	public NewsAdapter(Context mContext, List<News> mDatas, int itemLayoutId) {
		super(mContext, mDatas, itemLayoutId);
		// TODO Auto-generated constructor stub
		this.mContext = mContext;
	}

	/**
	 * 方法描述:
	 * 
	 * @param
	 * @author KIMI 创建时间：2015-3-24 下午1:12:20
	 * @version
	 * 
	 */
	@Override
	public void convert(ViewHolder mViewHolder, News news) {
		// TODO Auto-generated method stub
		mViewHolder.setImageByUrl(mContext, R.id.news_imageview, news.getImgsrc());
		mViewHolder.setText(R.id.news_title, news.getTitle());
	}

}
