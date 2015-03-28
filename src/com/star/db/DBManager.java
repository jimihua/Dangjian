package com.star.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.example.zakerdemo.R;

public class DBManager {
	private static int BUFFER_SIZE = 600000;
	public static final String DB_NAME = "dangjian.db"; // 保存的数据库文件名
	public static final String PACKAGE_NAME = "com.example.zakerdemo";
	public static final String DB_PATH = "/data" + Environment.getDataDirectory().getAbsolutePath() + "/" + PACKAGE_NAME + "/databases"; // 在手机里存放数据库的位置

	private SQLiteDatabase database;
	private Context context;

	public DBManager(Context context) {
		this.context = context;
	}

	public void openDatabase() {
		this.database = this.openDatabase(DB_PATH + "/" + DB_NAME);
	}

	private SQLiteDatabase openDatabase(String dbfile) {
		File file = new File(DB_PATH);
		if (!file.exists()) {
			file.mkdirs();
		}
		;
		try {
			if (!(new File(dbfile).exists())) {// 判断数据库文件是否存在，若不存在则执行导入，否则直接打开数据库
				System.out.println("input");
				InputStream is = this.context.getResources().openRawResource(R.raw.dangjian); // 欲导入的数据库
				FileOutputStream fos = new FileOutputStream(dbfile);
				byte[] buffer = new byte[BUFFER_SIZE];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
				fos.close();
				is.close();
			}
			SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile, null);
			return db;
		} catch (FileNotFoundException e) {
			Log.e("Database", "File not found");
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("Database", "IO exception");
			e.printStackTrace();
		}
		return null;
	}

	public void closeDatabase() {
		this.database.close();
	}

	/**
	 * 查询cursor的字符串值，cursor为查询游标，column是柱名 返回得到的字符串
	 * 
	 * */
	public static String getCursorString(Cursor cursor, String column) {

		return cursor.getString(cursor.getColumnIndex(column));
	}
}
