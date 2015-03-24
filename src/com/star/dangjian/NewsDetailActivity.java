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
public class NewsDetailActivity extends BaseActivity {

	@ViewInject(id = R.id.webview)
	MyWebView webView;
	@ViewInject(id = R.id.news_title)
	TextView news_title;
	@ViewInject(id = R.id.news_content)
	TextView news_content;
	private String url = "";
	private Document doc;

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				Bundle bundle = msg.getData();
				news_title.setText(Html.fromHtml(bundle.get("title").toString()));
				news_content.setText(Html.fromHtml(bundle.get("content").toString()));
				break;

			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		setContentView(R.layout.activity_newsdetail);
		url = getIntent().getStringExtra("url");
		getItems();
	}

	/**
	 * 方法描述:
	 * 
	 * @param
	 * @author KIMI 创建时间：2015-3-24 下午1:51:06
	 * @version
	 * 
	 */
	private void getItems() {
		// TODO Auto-generated method stub
		CFinal.fh.get(url, new AjaxCallBack<Object>() {

			@Override
			public void onSuccess(Object object) {
				// TODO Auto-generated method stub
				super.onSuccess(object);
				doc = Jsoup.parse(object.toString());

				Elements title = doc.getElementsByClass("text_c");
				for (Element element : title) {
					Bundle bundle = new Bundle();
					bundle.putString("title", element.select("h1").toString());
					bundle.putString("content", element.select(".text_show").toString());
					Message msg = new Message();
					msg.what = 1;
					msg.setData(bundle);
					mHandler.sendMessage(msg);

				}
			}
		});
	}
}
