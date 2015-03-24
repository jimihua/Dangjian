package com.star.tools;

import org.apache.http.Header;
import org.apache.http.client.CookieStore;
import org.apache.http.message.BasicHeader;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.net.wifi.WifiManager;

public class MyHttpCookies
{
  public static String baseurl = "http://www.139e.com";
  private static Header[] httpHeader;
  private static String httpProxyStr;
  private static int pageSize = 10;
  private static CookieStore uCookie = null;
  Context context;

  public MyHttpCookies(Context paramContext)
  {
    this.context = paramContext;
    Header[] arrayOfHeader = new Header[1];
    arrayOfHeader[0] = new BasicHeader("PagingRows", String.valueOf(pageSize));
    httpHeader = arrayOfHeader;
  }

  public Header[] getHttpHeader()
  {
    return httpHeader;
  }

  public String getHttpProxyStr()
  {
    return httpProxyStr;
  }

  public int getPageSize()
  {
    return pageSize;
  }

  public CookieStore getuCookie()
  {
    return uCookie;
  }

  public void initHTTPProxy()
  {
    if (!((WifiManager)this.context.getSystemService("wifi")).isWifiEnabled())
    {
      Uri localUri = Uri.parse("content://telephony/carriers/preferapn");
      Cursor localCursor = this.context.getContentResolver().query(localUri, null, null, null, null);
      if (localCursor != null)
      {
        localCursor.moveToNext();
        httpProxyStr = localCursor.getString(localCursor.getColumnIndex("proxy"));
      }
      return;
    }
    httpProxyStr = null;
  }

  public void setPageSize(int paramInt)
  {
    pageSize = paramInt;
  }

  public void setuCookie(CookieStore paramCookieStore)
  {
    uCookie = paramCookieStore;
  }
}

