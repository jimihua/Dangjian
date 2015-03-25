/**
 * @author Kimi
 */
package com.star.dangjian;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;

import org.jsoup.Jsoup;
import org.xml.sax.XMLReader;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Html.TagHandler;
import android.util.Log;
import android.widget.TextView;

import com.example.zakerdemo.R;
import com.star.base.BaseActivity;
import com.star.common.CFinal;

/**
 * @author Kimi
 * 
 */
@SuppressWarnings("all")
public class SchoolNewsDetail extends BaseActivity {
	@ViewInject(id = R.id.news_title)
	TextView news_title;
	@ViewInject(id = R.id.news_content)
	TextView news_content;
	@ViewInject(id = R.id.date)
	TextView date;
	@ViewInject(id = R.id.author)
	TextView author;
	@ViewInject(id = R.id.deptname)
	TextView deptname;
	private String url;

	/*
	 * @author Kimi
	 */
	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		setContentView(R.layout.schollnews_detail);
		url = getIntent().getStringExtra("url");
		getItems();
	}

	private void getItems() {
		mProgressDialog = ProgressDialog.show(this, "请稍后", "正在努力加载中...", true);
		mProgressDialog.setCancelable(true);
		CFinal.getFh("GBK").post(url, new AjaxCallBack<Object>() {
			/*
			 * @author Kimi
			 */
			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				// TODO Auto-generated method stub
				super.onFailure(t, errorNo, strMsg);
				mProgressDialog.dismiss();
			}

			/*
			 * @author Kimi
			 */
			@Override
			public void onSuccess(Object object) {
				// TODO Auto-generated method stub
				super.onSuccess(object);
				doc = Jsoup.parse(object.toString());
				news_title.setText(doc.select("#mytitle").text());
				date.setText("日期： " + doc.select("#mydate").text());
				author.setText("作者：" + doc.select("#myauthor").text());
				deptname.setText("供稿单位：" + doc.select("#mydeptname").text());

				news_content.setText(Html.fromHtml(doc.select("#mycontent").toString(), mImageGetter, null));
				mProgressDialog.dismiss();
			}
		});
	}

	private ImageGetter mImageGetter = new ImageGetter() {

		@Override
		public Drawable getDrawable(String source) {
			// TODO Auto-generated method stub
			LevelListDrawable d = new LevelListDrawable();
			// Drawable empty =
			// getResources().getDrawable(R.drawable.ic_launcher);
			// d.addLevel(0, 0, empty);
			// d.setBounds(0, 0, empty.getIntrinsicWidth(),
			// empty.getIntrinsicHeight());

			new LoadImage().execute(source, d);
			return d;
		}
	};

	class LoadImage extends AsyncTask<Object, Void, Bitmap> {

		private LevelListDrawable mDrawable;

		@Override
		protected Bitmap doInBackground(Object... params) {
			String source = (String) params[0];
			mDrawable = (LevelListDrawable) params[1];
			Log.d(TAG, "doInBackground " + source);
			try {
				InputStream is = new URL("http://www.zjnu.edu.cn" + source).openStream();
				return BitmapFactory.decodeStream(is);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Bitmap bitmap) {
			Log.d(TAG, "onPostExecute drawable " + mDrawable);
			Log.d(TAG, "onPostExecute bitmap " + bitmap);
			if (bitmap != null) {
				BitmapDrawable d = new BitmapDrawable(bitmap);
				mDrawable.addLevel(1, 1, d);
				mDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
				mDrawable.setLevel(1);
				// i don't know yet a better way to refresh TextView
				// mTv.invalidate() doesn't work as expected
				CharSequence t = news_content.getText();
				news_content.setText(t);
			}
		}
	}
}
