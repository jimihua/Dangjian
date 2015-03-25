/**
 * @author Kimi
 */
package com.star.dangjian;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;

import com.example.zakerdemo.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.star.base.BaseActivity;
import com.star.common.CFinal;
import com.star.common.Urls;
import com.star.model.News;
import com.star.view.XListView;
import com.star.view.XListView.IXListViewListener;

/**
 * @author Kimi
 * 
 */
@SuppressWarnings("all")
public class SchoolNewsActivity extends BaseActivity<News> implements IXListViewListener {

	@ViewInject(id = R.id.listview)
	XListView mListView;

	private Handler mHandler;

	public void setContentView(int layoutResID) {
		// TODO Auto-generated method stub
		super.setContentView(R.layout.activity_schoolnews);
		mHandler = new Handler();
		this.mListView.setPullLoadEnable(true);
		geneItems();
		this.mListView.setXListViewListener(this);
		this.mListView.setDividerHeight(0);
		this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View view, int position, long paramAnonymousLong) {

			}
		});
	}

	private void geneItems() {
		this.mProgressDialog = ProgressDialog.show(this, "请稍后", "正在努力加载中...", true);
		this.mProgressDialog.setCancelable(true);

		CFinal.fh.post(Urls.SCHOOLNEWS, new AjaxCallBack() {
			public void onFailure(Throwable throwable, int param, String string) {
				super.onFailure(throwable, param, string);
				mProgressDialog.dismiss();
			}

			public void onSuccess(Object object) {
				super.onSuccess(object);
				doc = Jsoup.parse(object.toString());
				Elements texts = doc.getElementsByClass("article_show");
				for (Element element : texts) {
					News news = new News();
					news.setDes(element.text());
					mDatas.add(news);
				}
				Elements as = doc.select("a:not(.mypager,.green,[title],[href=default.aspx]");
				for (int i = 0; i < as.size(); i++) {
					mDatas.get(i).setTitle(as.get(i).text());
				}
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

			}
		}, 100L);
	}

	public void onRefresh() {
		this.mHandler.postDelayed(new Runnable() {
			public void run() {

			}
		}, 100L);
	}
}
