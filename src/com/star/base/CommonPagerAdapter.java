/**   
 *    
 * 项目名称：Dangjian     
 * 方法描述:   
 * 创建人：Administrator   
 * 创建时间：2015-3-26 下午2:03:17   
 * 修改人：Administrator   
 * 修改时间：2015-3-26 下午2:03:17   
 * 修改备注：   
 * @version    
 *    
 */
package com.star.base;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * 方法描述:
 * 
 * @param
 * @author KIMI 创建时间：2015-3-26 下午2:03:17
 * @version
 * 
 */
public class CommonPagerAdapter extends PagerAdapter {
	private List<View> mList;
	protected Context mContext;

	public CommonPagerAdapter(Context mContext, List<View> mList) {
		// TODO Auto-generated constructor stub
		this.mList = mList;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		// TODO Auto-generated method stub
		return view == object;
	}

	public void destroyItem(ViewGroup view, int position, Object object) {
		view.removeView(mList.get(position));
	}

	/**
	 * 方法描述:
	 * 
	 * @param
	 * @author KIMI 创建时间：2015-3-26 下午2:20:10
	 * @version
	 * 
	 */
	@Override
	public Object instantiateItem(ViewGroup view, int position) {
		view.addView(mList.get(position), 0);

		return mList.get(position);
	}

}
