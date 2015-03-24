/**   
 *    
 * 项目名称：Dangjian     
 * 方法描述:   
 * 创建人：Administrator   
 * 创建时间：2015-3-24 下午1:36:41   
 * 修改人：Administrator   
 * 修改时间：2015-3-24 下午1:36:41   
 * 修改备注：   
 * @version    
 *    
 */
package com.star.dangjian;

import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.widget.TextView;

import com.example.zakerdemo.R;
import com.star.base.BaseActivity;
import com.star.common.CFinal;
import com.star.utils.MyWebView;

/**
 * 方法描述:
 * 
 * @param
 * @author KIMI 创建时间：2015-3-24 下午1:36:41
 * @version
 * 
 */
public class NewsDetailActivity_v2 extends BaseActivity {

	@ViewInject(id = R.id.webview)
	MyWebView webview;
	@ViewInject(id = R.id.news_title)
	TextView news_title;
	@ViewInject(id = R.id.news_content)
	TextView news_content;
	private String url = "";
	private Document doc;

	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		setContentView(R.layout.star_dangshi_main);
		url = getIntent().getStringExtra("url");

		webview.loadUrl(url);
	}

}
