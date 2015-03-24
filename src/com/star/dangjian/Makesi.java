package com.star.dangjian;

import java.util.ArrayList;
import java.util.HashMap;

import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.zakerdemo.R;
import com.star.base.BaseActivity;
import com.star.db.DBManager;
import com.star.utils.WarnUtils;

public class Makesi extends BaseActivity {

	private ArrayList<HashMap<String, String>> Makesi=new ArrayList<HashMap<String,String>>();
	private SQLiteDatabase database;
	private Context mContext;
	private int questionnum = 1;
	private String answer;
	private String questionid="0";
	private static int RIGHT = 1;
	private static int WRONG = -1;
	private static int NOTANS = 0;

	@ViewInject(id = R.id.radio1)
	RadioButton radio1;

	@ViewInject(id = R.id.radio2)
	RadioButton radio2;

	@ViewInject(id = R.id.radio3)
	RadioButton radio3;

	@ViewInject(id = R.id.radio4)
	RadioButton radio4;

	// 上一题
	@ViewInject(id = R.id.btn_previous)
	Button btn_previous;
	// 下一题
	@ViewInject(id = R.id.btn_next)
	Button btn_next;
	// 确定
	@ViewInject(id = R.id.question_finished_btn)
	Button question_finished_btn;

	@ViewInject(id = R.id.question_multi_label)
	TextView question_multi_label;

	@ViewInject(id = R.id.question_content)
	TextView question_content;
	
	@ViewInject(id = R.id.txt_label)
	TextView txt_label;
	
	@ViewInject(id = R.id.btn_mode)
	TextView btn_mode;
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.practice);
		mContext = this;
		initView();

	}

	private void initView() {
		// TODO Auto-generated method stub

		database = SQLiteDatabase.openOrCreateDatabase(DBManager.DB_PATH + "/"
				+ DBManager.DB_NAME, null);

		this.Makesi = getMakesi(mContext);

		database.close();
		clearRadio();
		radio1.setChecked(true);
		if (Makesi.get(0).get("questiontype").equals("0")) {
			question_multi_label.setText("【单选题】");
			radio1.setOnCheckedChangeListener(onsingleListener);
			radio2.setOnCheckedChangeListener(onsingleListener);
			radio3.setOnCheckedChangeListener(onsingleListener);
			radio4.setOnCheckedChangeListener(onsingleListener);
		}

		refreshquestion();
		question_finished_btn.setOnClickListener(onClickListener);
		btn_previous.setOnClickListener(onClickListener);
		btn_next.setOnClickListener(onClickListener);
	}

	private ArrayList<HashMap<String, String>> getMakesi(Context mContext) {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, String>> Makesi = new ArrayList<HashMap<String, String>>();

		String sql = "select * from makesi where fal=0 LIMIT 150";
		Cursor cursor = database.rawQuery(sql, null);
		if (cursor != null) {
			while (cursor.moveToNext()) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("id", DBManager.getCursorString(cursor, "id"));
				map.put("question",
						DBManager.getCursorString(cursor, "question"));
				map.put("optionone",
						DBManager.getCursorString(cursor, "optionone"));
				map.put("optiontwo",
						DBManager.getCursorString(cursor, "optiontwo"));
				map.put("optionthree",
						DBManager.getCursorString(cursor, "optionthree"));
				map.put("optionfour",
						DBManager.getCursorString(cursor, "optionfour"));
				map.put("answer", DBManager.getCursorString(cursor, "answer"));
				map.put("fal", DBManager.getCursorString(cursor, "fal"));
				map.put("questiontype",
						DBManager.getCursorString(cursor, "questiontype"));
				Makesi.add(map);
			}
			return Makesi;
		}
		return null;
	}

	private OnCheckedChangeListener onsingleListener = new OnCheckedChangeListener() {

		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			clearRadio();
			if (isChecked) {
				buttonView.setChecked(isChecked);
			}

		}
	};

	private void clearRadio() {
		radio1.setChecked(false);
		radio2.setChecked(false);
		radio3.setChecked(false);
		radio4.setChecked(false);

	}

	private boolean refreshquestion() {
		if (Makesi.size()>=questionnum) {
			question_content.setText(questionnum + "."
					+ Makesi.get(questionnum - 1).get("question"));
			radio1.setText("A." + Makesi.get(questionnum - 1).get("optionone"));
			radio2.setText("B." + Makesi.get(questionnum - 1).get("optiontwo"));
			radio3.setText("C."
					+ Makesi.get(questionnum - 1).get("optionthree"));
			radio4.setText("D." + Makesi.get(questionnum - 1).get("optionfour"));
			answer = Makesi.get(questionnum - 1).get("answer");
			txt_label.setText(questionnum+"/"+Makesi.size());
			questionid=Makesi.get(questionnum - 1).get("id");
			questionnum++;
			return true;
		}
		return false;

	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.question_finished_btn:
				int isRight = isRight();
				if (isRight == 1) {
					WarnUtils.toast(mContext, "恭喜你，答对了");
					updatequestion(questionid);
					if (!refreshquestion()) {
						WarnUtils.toast(mContext, "题目已经答完,请等待更新");
					}
					
					
				}
				if (isRight == 0) {
					WarnUtils.toast(mContext, "您还未选择答案，请选择答案");
				}
				if (isRight == -1) {
					WarnUtils.toast(mContext, "你答错啦");
				}
				break;
			case R.id.btn_previous:
				questionnum=questionnum-2;
				if (questionnum<1) {
					questionnum=1;
				}
				refreshquestion();
				break;
			case R.id.btn_next:
				refreshquestion();
				break;
			default:
				break;
			}
		}
	};

	private int isRight() {
		String chooseanswer;
		if (radio1.isChecked()) {
			chooseanswer = radio1.getText().toString();
			chooseanswer = chooseanswer
					.substring(chooseanswer.indexOf(".") + 1);
			System.out.println(chooseanswer);
			if (chooseanswer.equals(answer)) {
				return RIGHT;
			} else {
				return WRONG;
			}
		}

		if (radio2.isChecked()) {
			chooseanswer = radio2.getText().toString();
			chooseanswer = chooseanswer
					.substring(chooseanswer.indexOf(".") + 1);
			System.out.println(chooseanswer);
			if (chooseanswer.equals(answer)) {
				return RIGHT;
			} else {
				return WRONG;
			}
		}
		if (radio3.isChecked()) {
			chooseanswer = radio3.getText().toString();
			chooseanswer = chooseanswer
					.substring(chooseanswer.indexOf(".") + 1);
			System.out.println(chooseanswer);
			if (chooseanswer.equals(answer)) {
				return RIGHT;
			} else {
				return WRONG;
			}
		}
		if (radio4.isChecked()) {
			chooseanswer = radio4.getText().toString();
			chooseanswer = chooseanswer
					.substring(chooseanswer.indexOf(".") + 1);
			System.out.println(chooseanswer);
			if (chooseanswer.equals(answer)) {
				return RIGHT;
			} else {
				return WRONG;
			}
		}
		return NOTANS;

	}
	
	/**
	 * @param id
	 *  更新题目状态
	 */
	private void updatequestion(String id){
		String sql = "update makesi set questiontype=1 where id="+id;
		Cursor cursor = database.rawQuery(sql, null);
	}
	
}
