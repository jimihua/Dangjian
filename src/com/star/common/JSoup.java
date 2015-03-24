package com.star.common;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.star.tools.MyUtil;

public class JSoup {

	public static void connect(String url) {
		Document doc = null;
		MyUtil.Log("connect");
		MyUtil.Log(url);
		// TODO Auto-generated method stub
		try {
			doc = Jsoup.connect(url).get();
			MyUtil.Log(doc.title());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		if (doc != null) {
			Elements paragraphs = doc.select("p");
			for (Element p : paragraphs) {
				MyUtil.Log(p.toString());
			}
		}

		else {
			MyUtil.Log("fail");
		}
	}
}
