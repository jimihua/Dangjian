/**   
 *    
 * 项目名称：Dangjian     
 * 方法描述:   
 * 创建人：Administrator   
 * 创建时间：2015-3-26 下午4:52:11   
 * 修改人：Administrator   
 * 修改时间：2015-3-26 下午4:52:11   
 * 修改备注：   
 * @version    
 *    
 */
package com.star.dangjian;

import java.util.List;

import net.tsz.afinal.annotation.view.ViewInject;
import android.os.Bundle;
import android.widget.ListView;

import com.example.zakerdemo.R;
import com.star.base.BaseActivity;
import com.star.common.CFinal;
import com.star.model.YuLu;

/**
 * 方法描述:习大大经典之语
 * 
 * @param
 * @author KIMI 创建时间：2015-3-26 下午4:52:11
 * @version
 * 
 */
public class XiDaDaJingdianActivity extends BaseActivity<YuLu> {

	@ViewInject(id = R.id.listview)
	ListView listView;

	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		setContentView(R.layout.activity_xdd_jdyulu);
		getItems();
	}

	private void getItems() {
		mList = CFinal.getFinalDb(mContext).findAll(YuLu.class, "cls='习近平经典之语'");
		for (YuLu yuLu : mList) {
			System.out.println(yuLu.getOrigin());
		}
	}
}
