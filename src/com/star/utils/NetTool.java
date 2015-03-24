package com.star.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import android.util.Log;

/**
 * NetTool:封装一个类实现客户端与服务器端交互
 * 
 * @author j_steven
 */
public class NetTool {
	private static final String TAG = NetTool.class.getSimpleName();
	private static final int TIMEOUT = 10000;// 10秒

	/**
	 * 通过get方式提交参数给服务器
	 */
	@Deprecated
	public static String sendGetRequest(String urlPath,
			Map<String, String> params, String encoding) throws IOException {
		// if (true)
		// throw new java.lang.UnsupportedOperationException();
		// // 使用StringBuilder对象
		// StringBuilder sb = new StringBuilder(urlPath);
		// sb.append('?');
		//
		// // 迭代Map
		// for (Map.Entry<String, String> entry : params.entrySet()) {
		// sb.append(entry.getKey()).append('=')
		// .append(URLEncoder.encode(entry.getValue(), encoding))
		// .append('&');
		// }
		// sb.deleteCharAt(sb.length() - 1);
		// // 打开链接
		// URL url = new URL(sb.toString());
		// HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// conn.setRequestMethod("GET");
		// conn.setRequestProperty("Content-Type", "text/xml");
		// conn.setRequestProperty("Charset", encoding);
		// conn.setConnectTimeout(TIMEOUT);
		// // 如果请求响应码是200，则表示成功
		// if (conn.getResponseCode() == 200) {
		// // 获得服务器响应的数据
		// BufferedReader in = new BufferedReader(new InputStreamReader(
		// conn.getInputStream(), encoding));
		// // 数据
		// String retData = null;
		// String responseData = "";
		// while ((retData = in.readLine()) != null) {
		// responseData += retData;
		// }
		// in.close();
		// return responseData;
		// }
		return "error";

	}

	/**
	 * 通过Post方式提交参数给服务器,也可以用来传送json或xml文件
	 */
	public static String sendPostRequest(final String urlPath,
			final Map<String, String> params, final String encoding)
			throws IOException {
		final String[] ret = new String[1];
		final Thread currentThread = Thread.currentThread();
		Thread t = new Thread() {
			public void run() {
				try {
					Log.d(TAG, "POST " + urlPath + params);
					StringBuilder sb = new StringBuilder();
					// 如果参数不为空
					if (params != null && !params.isEmpty()) {
						for (Map.Entry<String, String> entry : params
								.entrySet()) {
							// Post方式提交参数的话，不能省略内容类型与长度
							sb.append(entry.getKey())
									.append('=')
									.append(URLEncoder.encode(entry.getValue(),
											encoding)).append('&');
						}
						sb.deleteCharAt(sb.length() - 1);
					}
					// 得到实体的二进制数据
					byte[] entitydata = sb.toString().getBytes();
					URL url = new URL(urlPath);
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.setRequestMethod("POST");
					conn.setConnectTimeout(TIMEOUT);
					// 如果通过post提交数据，必须设置允许对外输出数据
					conn.setDoOutput(true);
					// 这里只设置内容类型与内容长度的头字段
					conn.setRequestProperty("Content-Type",
							"application/x-www-form-urlencoded");
					// conn.setRequestProperty("Content-Type", "text/xml");
					conn.setRequestProperty("Charset", encoding);
					conn.setRequestProperty("Content-Length",
							String.valueOf(entitydata.length));
					OutputStream outStream = conn.getOutputStream();
					// 把实体数据写入是输出流
					outStream.write(entitydata);
					// 内存中的数据刷入
					outStream.flush();
					outStream.close();
					// 如果请求响应码是200，则表示成功
					if (conn.getResponseCode() == 200) {
						// 获得服务器响应的数据
						BufferedReader in = new BufferedReader(
								new InputStreamReader(conn.getInputStream(),
										encoding));
						// 数据
						String retData = null;
						String responseData = "";
						while ((retData = in.readLine()) != null) {
							responseData += retData;
						}
						in.close();
						ret[0] = responseData;
						return;
					}
					ret[0] = "error";
				} catch (IOException ex) {
					Log.e(TAG, "connect to the internet", ex);
					currentThread.interrupt();
				}
			}

		};
		t.start();
		try {
			t.join();
		} catch (InterruptedException ex) {
			Log.e(TAG, "Connect to the " + urlPath + " failed");
			throw new InterruptedIOException(ex.getMessage());
		}
		ret[0].getClass(); // null check
		Log.e("result", ret[0]);
		return ret[0];
	}
}
