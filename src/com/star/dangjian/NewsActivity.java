/**   
 *    
 * 项目名称：ZakerDemo     
 * 方法描述:   
 * 创建人：Administrator   
 * 创建时间：2014-10-17 下午12:41:45   
 * 修改人：Administrator   
 * 修改时间：2014-10-17 下午12:41:45   
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
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.zakerdemo.R;
import com.star.base.BaseActivity;
import com.star.common.CFinal;
import com.star.common.LogUtils;
import com.star.list.NewsAdapter;
import com.star.model.News;
import com.star.tools.MyUtil;

/**
 * 方法描述: 新闻
 * 
 * @param
 * @author KIMI 创建时间：2014-10-17 下午12:41:45
 * @version
 * 
 */
public class NewsActivity extends BaseActivity<News> {
	private static String url = "http://cpc.people.com.cn/";
	private Document doc = null;
	@ViewInject(id = R.id.listview)
	ListView listView;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);

		getItems();
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				MyUtil.JumpPages(mContext, NewsDetailActivity_v2.class, "url", mList.get(position).getLink());
			}
		});
	}

	private void getItems() {
		CFinal.fh.configCharset("GBK");
		CFinal.fh.get(url, new AjaxCallBack<Object>() {

			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				// TODO Auto-generated method stub
				super.onFailure(t, errorNo, strMsg);

			}

			@Override
			public void onSuccess(Object object) {
				// TODO Auto-generated method stub
				super.onSuccess(object);

				doc = Jsoup.parse(object.toString());

				Elements p1_l_li = doc.select(".p1_l_left li");
				for (Element element : p1_l_li) {

					News news = new News();
					news.setTitle(element.select("img").attr("alt"));
					news.setImgsrc("http://cpc.people.com.cn/" + element.select("img").attr("src"));

					news.setLink(element.select("a").attr("href"));
					mList.add(news);
				}
				mAdapter = new NewsAdapter(mContext, mList, R.layout.listview_item);
				listView.setAdapter(mAdapter);
			}
		});
	}

}
