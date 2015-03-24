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
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;

import com.example.zakerdemo.R;
import com.star.base.BaseActivity;
import com.star.db.DBManager;
import com.star.note.MyNote;
import com.star.tools.MyUtil;

public class MainActivity extends BaseActivity {
	private DBManager dbHelper;
	private Context mContext;
	private SQLiteDatabase database;
	private String url;
	private ArrayList<String> todayDs;
	private ProgressDialog progressDialog = null;
	private ArrayList<String> dsjt = new ArrayList<String>();
	private int noteid = 201499;
	@ViewInject(id = R.id.dj_fl_ddls)
	FrameLayout dj_fl_ddls;

	@ViewInject(id = R.id.dj_fl_dsjs)
	FrameLayout dj_fl_dsjs;
	
	@ViewInject(id = R.id.dj_fl_mks)
	FrameLayout dj_fl_mks;
	
	@ViewInject(id = R.id.dj_fl_mg)
	FrameLayout dj_fl_mg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		dbHelper = new DBManager(mContext);
		dbHelper.openDatabase();
		dbHelper.closeDatabase();
		initView();
		dj_fl_ddls.setOnClickListener(onClickListener);
		dj_fl_dsjs.setOnClickListener(onClickListener);
		dj_fl_mks.setOnClickListener(onClickListener);
		dj_fl_mg.setOnClickListener(onClickListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.dj_fl_ddls:
				MyUtil.JumpPages(mContext, Dangshi.class, null);
				break;
			case R.id.dj_fl_dsjs:
				MyUtil.JumpPages(mContext, Dsjx.class, null);
				break;
			case R.id.dj_fl_mks:
				MyUtil.JumpPages(mContext, Practice.class, "practice","makesi");
				break;
//			case R.id.dj_fl_mg:
//				MyUtil.JumpPages(mContext, Practice.class, "practice","maogai");
//				break;
			default:
				break;
			}
		}
	};

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
					while (true) {
						Document doc = null;
						try {

							doc = Jsoup.connect(url).get();
							// MyUtil.Log(doc.title());

							Elements paragraphs = doc.select("p");
							// System.out.println(paragraphs.toString());
							Elements titles = doc.select("h2");

							for (Element t : titles) {
								String param = null;
								if (t.toString().contains("<h2>")
										&& !t.toString().contains("img")) {
									param = t.toString().replace("<h2>", "");
									param = param.replace("</h2>", "");
									param = param.replace("&nbsp;", " ");
									dsjt.add(param);
									new MyNote(mContext).addNote(noteid++,
											param);
									System.out.println(noteid++ + param);
								}

							}

							for (Element p : paragraphs) {
								String param = null;
								if (p.toString().contains("<p>")) {
									param = p.toString().substring(3);
								}
								// dsjt.add(param);

							//	System.out.println(param);

							}

							try {
								Thread.sleep(1000*3600*24);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();

						}

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

}
