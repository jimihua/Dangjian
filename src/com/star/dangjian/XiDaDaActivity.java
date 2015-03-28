/**   
 *    
 * 项目名称：Dangjian     
 * 方法描述:   
 * 创建人：Administrator   
 * 创建时间：2015-3-26 下午1:06:23   
 * 修改人：Administrator   
 * 修改时间：2015-3-26 下午1:06:23   
 * 修改备注：   
 * @version    
 *    
 */
package com.star.dangjian;

import net.tsz.afinal.annotation.view.ViewInject;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.zakerdemo.R;
import com.star.base.BaseActivity;
import com.star.base.CommonPagerAdapter;
import com.star.list.ImageAdapter;
import com.star.tools.MyUtil;

/**
 * 方法描述:
 * 
 * @param
 * @author KIMI 创建时间：2015-3-26 下午1:06:23
 * @version
 * 
 */
@SuppressWarnings("all")
public class XiDaDaActivity extends BaseActivity {

	@ViewInject(id = R.id.viewpager)
	ViewPager viewpager;

	/**
	 * 习大大法制语录
	 */
	@ViewInject(id = R.id.xdd_fzyl, click = "onClick")
	LinearLayout xdd_fzul;
	/**
	 * 习大大语录
	 */
	@ViewInject(id = R.id.xdd_yulu, click = "onClick")
	LinearLayout xdd_yulu;
	/**
	 * 习大大经典之语
	 */
	@ViewInject(id = R.id.xdd_jdzy, click = "onClick")
	LinearLayout xdd_jdzy;
	/**
	 * 装点点的ImageView数组
	 */
	private ImageView[] tips;
	private int[] imgId = { R.drawable.xidada1, R.drawable.xidada2, R.drawable.xidada3 };
	private CommonPagerAdapter mAdapter;

	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		setContentView(R.layout.activity_xidada);
		getItems();
	}

	private void getItems() {
		for (int id : imgId) {
			ImageView imageView = new ImageView(mContext);
			imageView.setBackgroundResource(id);
			mList.add(imageView);
		}
		viewpager.setAdapter(new ImageAdapter(mContext, mList));

	};

	public void onClick(View view) {
		
		switch (view.getId()) {

		case R.id.xdd_jdzy:

			MyUtil.JumpPages(mContext, XiDaDaJingdianActivity.class, "jingdian");
			break;
		case R.id.xdd_yulu:
			MyUtil.JumpPages(mContext, XiDaDaYuluActivity.class, "jingdian");

			break;
		case R.id.xdd_fzyl:
			MyUtil.JumpPages(mContext, XiDaDaFazhiYuluActivity.class, "jingdian");

			break;
		default:
			break;
		}
	}
}
