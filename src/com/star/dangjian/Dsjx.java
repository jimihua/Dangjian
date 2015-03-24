package com.star.dangjian;

import java.io.IOException;
import java.util.ArrayList;

import net.tsz.afinal.annotation.view.ViewInject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import com.example.zakerdemo.R;
import com.star.base.BaseActivity;
import com.star.db.DBManager;
import com.star.note.MyNote;
import com.star.tools.MyUtil;

public class Dsjx extends BaseActivity {
	private SQLiteDatabase database;
	private String url;
	private ArrayList<String> todayDs;
	private Context mContext;
	private ProgressDialog progressDialog = null;
	private ArrayList<String> dsjt = new ArrayList<String>();
	private int noteid=201499;
	@ViewInject(id = R.id.dsjx_tv_nr)
	TextView dsjx_tv_nr;

	private void initView() {
		// TODO Auto-generated method stub

		database = SQLiteDatabase.openOrCreateDatabase(DBManager.DB_PATH + "/"
				+ DBManager.DB_NAME, null);
		todayDs = getUrl(mContext);
		if (todayDs != null) {
			url = todayDs.get(0);
			MyUtil.Log(url);
			
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					Document doc = null;
					try {

						doc = Jsoup.connect(url).get();
						// MyUtil.Log(doc.title());
					
						Elements paragraphs = doc.select("p");
						// System.out.println(paragraphs.toString());
						Elements titles=doc.select("h2");
						
						for (Element t : titles) {
							String param = null;
							if (t.toString().contains("<h2>")) {
								param = t.toString().replace("<h2>","");
								param = param.replace("</h2>","");
								param = param.replace("&nbsp;"," ");
							}
							
							dsjt.add(param);
							new MyNote(mContext).addNote(noteid++,param);
							System.out.println(param);
							
						}
						
						
						
						for (Element p : paragraphs) {
							String param = null;
							if (p.toString().contains("<p>")) {
								param = p.toString().substring(3);
							}
							//dsjt.add(param);
							
							System.out.println(param);
							
						}

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					}

				}
			};
			new Thread(runnable).start();

		} else {
			url = "http://www.baidu.com";
		}
		database.close();
	}

	private ArrayList<String> getUrl(Context mContext) {
		// TODO Auto-generated method stub
		ArrayList<String> urls = new ArrayList<String>();

		String date = MyUtil.getDate(mContext);
		String sql = "select * from dangshi where date=" + date;
		Cursor cursor = database.rawQuery(sql, null);
		if (cursor != null) {

			while (cursor.moveToNext()) {
				String link = cursor.getString(cursor.getColumnIndex("link"));
				urls.add(link);
			}
			return urls;
		} else {

			return null;
		}
	}

	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.star_dsjx_main);
		mContext = this;
		initView();

	}
}
