/**   
 *    
 * 项目名称：TaurusClub     
 * 方法描述:   
 * 创建人：Administrator   
 * 创建时间：2015-3-12 下午3:41:10   
 * 修改人：Administrator   
 * 修改时间：2015-3-12 下午3:41:10   
 * 修改备注：   
 * @version    
 *    
 */
package com.star.base;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * 方法描述:
 * 
 * @param
 * @author KIMI 创建时间：2015-3-12 下午3:41:10
 * @version
 * 
 */
public class CommonFragPagerAdapter extends FragmentPagerAdapter {

	private List<Fragment> mList;

	/**
	 * @param fm
	 */
	public CommonFragPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	public CommonFragPagerAdapter(FragmentManager fm, List<Fragment> mList) {
		super(fm);
		// TODO Auto-generated constructor stub
		this.mList = mList;
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

}
