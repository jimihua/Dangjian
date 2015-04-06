/**
 * @author Kimi
 */
package com.star.dangjian;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.Inflater;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.example.zakerdemo.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.star.base.BaseActionActivity;
import com.star.base.BaseActivity;
import com.star.common.CFinal;
import com.star.common.Urls;
import com.star.list.SchoolNewsAdapter;
import com.star.model.News;
import com.star.tools.MyUtil;
import com.star.view.XListView;
import com.star.view.XListView.IXListViewListener;

/**
 * @author Kimi
 * 
 */
@SuppressWarnings("all")
public class SchoolNewsActivity extends BaseActionActivity<News> implements IXListViewListener {

	@ViewInject(id = R.id.listview)
	XListView mListView;

	private Handler mHandler;

	private int page = 1;

	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		setContentView(R.layout.activity_schoolnews);
		FinalActivity.initInjectedView(this);
		mHandler = new Handler();
		this.mListView.setPullLoadEnable(true);
		getItems();
		this.mListView.setXListViewListener(this);
		this.mListView.setDividerHeight(0);
		this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View view, int position, long paramAnonymousLong) {
				MyUtil.JumpPages(mContext, SchoolNewsDetail.class, "url", mList.get(position - 1).getLink());

			}
		});
	}

	private void getItems() {
		this.mProgressDialog = ProgressDialog.show(this, "请稍后", "正在努力加载中...", true);
		this.mProgressDialog.setCancelable(true);

		CFinal.getFh("GBK").post(Urls.SCHOOLNEWS + page, new AjaxCallBack() {
			public void onFailure(Throwable throwable, int param, String string) {
				super.onFailure(throwable, param, string);
				mProgressDialog.dismiss();
			}

			public void onSuccess(Object object) {
				super.onSuccess(object);
				doc = Jsoup.parse(object.toString());
				// lists 临时的list
				List<News> lists = new ArrayList<News>();

				Elements texts = doc.getElementsByClass("article_show");
				for (Element element : texts) {
					News news = new News();
					news.setDes(element.text());
					lists.add(news);
				}

				Elements as = doc.select("a:not(.mypager,.green,[title],[href=default.aspx],[disabled=true])");

				if (lists.size() == as.size() && lists.size() != 0) {
					for (int i = 0; i < as.size(); i++) {

						lists.get(i).setTitle(as.get(i).text());
						lists.get(i).setLink("http://www.zjnu.edu.cn/news/common/" + as.get(i).attr("href"));

					}
				}

				mList.addAll(lists);
				if (isFirst) {
					mAdapter = new SchoolNewsAdapter(mContext, mList, R.layout.schollnews_item);
					mListView.setAdapter(mAdapter);
				}
				mAdapter.notifyDataSetChanged();
				mProgressDialog.dismiss();
			}
		});
	}

	private void onLoad() {
		this.mListView.stopRefresh();
		this.mListView.stopLoadMore();
		this.mListView.setRefreshTime(this.sdf.format(new Date()));
	}

	public void onLoadMore() {
		this.mHandler.postDelayed(new Runnable() {
			public void run() {
				page++;
				getItems();
				onLoad();
			}
		}, 100L);
	}

	public void onRefresh() {
		this.mHandler.postDelayed(new Runnable() {
			public void run() {

				isFirst = true;
				page = 1;
				mList.clear();
				getItems();
				onLoad();
			}
		}, 100L);
	}

	
}
