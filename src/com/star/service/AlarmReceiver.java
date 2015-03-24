/**   
 *    
 * 项目名称：TaurusDemo     
 * 方法描述:   
 * 创建人：Administrator   
 * 创建时间：2014-12-14 下午2:26:20   
 * 修改人：Administrator   
 * 修改时间：2014-12-14 下午2:26:20   
 * 修改备注：   
 * @version    
 *    
 */
package com.star.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 方法描述:
 * 
 * @param
 * @author KIMI 创建时间：2014-12-14 下午2:26:20
 * @version
 * 
 */
public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context mContext, Intent intent) {
		if (intent.getAction().equals("arui.alarm.action")) {
			Intent i = new Intent();
			i.setClass(mContext, ActService.class);
			// 启动service
			// 多次调用startService并不会启动多个service 而是会多次调用onStart
			mContext.startService(i);
			
		}
	}
}