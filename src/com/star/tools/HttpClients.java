package com.star.tools;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLHandshakeException;

import org.apache.http.Header;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Context;

public class HttpClients
{
  private static HttpRequestRetryHandler requestRetryHandler = new HttpRequestRetryHandler()
  {
    public boolean retryRequest(IOException paramAnonymousIOException, int paramAnonymousInt, HttpContext paramAnonymousHttpContext)
    {
      if (paramAnonymousInt >= 3);
      do
      {
        return false;
      }
      while (((paramAnonymousIOException instanceof SSLHandshakeException)) || (((HttpRequest)paramAnonymousHttpContext.getAttribute("http.request") instanceof HttpEntityEnclosingRequest)));
    }
  };
  private Activity activity = null;
  private long contentLength;
  private Context context;
  private DefaultHttpClient httpClient;
  private HttpParams httpParams;
  private String strResult = "服务器无法连接，请检查网络";

  public HttpClients(Activity paramActivity)
  {
    this.context = paramActivity.getBaseContext();
    this.activity = paramActivity;
    getHttpClient();
  }

  private Header[] getHeader()
  {
    return new MyHttpCookies(this.context).getHttpHeader();
  }

  public static String nullToString(Object paramObject)
  {
    if (paramObject == null)
      return "";
    return paramObject.toString();
  }

  public String doGet(String paramString)
  {
    HttpGet localHttpGet = new HttpGet(paramString);
    localHttpGet.setHeaders(getHeader());
    try
    {
      MyHttpCookies localMyHttpCookies = new MyHttpCookies(this.context);
      if (localMyHttpCookies.getuCookie() != null)
        this.httpClient.setCookieStore(localMyHttpCookies.getuCookie());
      HttpResponse localHttpResponse = this.httpClient.execute(localHttpGet);
      if (localHttpResponse.getStatusLine().getStatusCode() == 200)
      {
        this.strResult = EntityUtils.toString(localHttpResponse.getEntity());
        localMyHttpCookies.setuCookie(this.httpClient.getCookieStore());
      }
      while (true)
      {
        return this.strResult;
      }
    }
    catch (ClientProtocolException localClientProtocolException)
    {
      while (true)
      {
        this.strResult = nullToString(localClientProtocolException.getMessage());
        localClientProtocolException.printStackTrace();
        localHttpGet.abort();
        shutDownClient();
      }
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        this.strResult = nullToString(localIOException.getMessage());
        localIOException.printStackTrace();
        localHttpGet.abort();
        shutDownClient();
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        this.strResult = nullToString(localException.getMessage());
        localException.printStackTrace();
        localHttpGet.abort();
        shutDownClient();
      }
    }
    finally
    {
      localHttpGet.abort();
      shutDownClient();
    }
  }

  public String doGet(String paramString, List<NameValuePair> paramList)
  {
    String str1 = "";
    if (paramList == null)
      paramList = new ArrayList();
    Iterator localIterator = paramList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        if (!str1.equals(""))
        {
          String str2 = str1.replaceFirst("&", "?");
          paramString = paramString + str2;
        }
        return doGet(paramString);
      }
      NameValuePair localNameValuePair = (NameValuePair)localIterator.next();
      str1 = str1 + new StringBuilder("&").append(localNameValuePair.getName()).append("=").append(URLEncoder.encode(localNameValuePair.getValue())).toString();
    }
  }

  public String doGet(String paramString, Map paramMap)
  {
    String str1 = "";
    if (paramMap == null)
      paramMap = new HashMap();
    Iterator localIterator = paramMap.entrySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        if (!str1.equals(""))
        {
          String str3 = str1.replaceFirst("&", "?");
          paramString = paramString + str3;
        }
        return doGet(paramString);
      }
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Object localObject = localEntry.getKey();
      String str2 = nullToString(localEntry.getValue());
      str1 = str1 + new StringBuilder("&").append(localObject).append("=").append(URLEncoder.encode(str2)).toString();
    }
  }

  public String doPost(String paramString, List<NameValuePair> paramList)
  {
    HttpPost localHttpPost = new HttpPost(paramString);
    localHttpPost.setHeaders(getHeader());
    try
    {
      localHttpPost.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));
      MyHttpCookies localMyHttpCookies = new MyHttpCookies(this.context);
      if (localMyHttpCookies.getuCookie() != null)
        this.httpClient.setCookieStore(localMyHttpCookies.getuCookie());
      HttpResponse localHttpResponse = this.httpClient.execute(localHttpPost);
      if (localHttpResponse.getStatusLine().getStatusCode() == 200)
      {
        this.strResult = EntityUtils.toString(localHttpResponse.getEntity());
        localMyHttpCookies.setuCookie(this.httpClient.getCookieStore());
      }
      while (true)
      {
        return this.strResult;
      }
    }
    catch (ClientProtocolException localClientProtocolException)
    {
      while (true)
      {
        this.strResult = "";
        localClientProtocolException.printStackTrace();
        localHttpPost.abort();
        shutDownClient();
      }
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        this.strResult = "";
        localIOException.printStackTrace();
        localHttpPost.abort();
        shutDownClient();
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        this.strResult = "";
        localException.printStackTrace();
        localHttpPost.abort();
        shutDownClient();
      }
    }
    finally
    {
      localHttpPost.abort();
      shutDownClient();
    }
  }

  public long getContentLength()
  {
    return this.contentLength;
  }

  public DefaultHttpClient getHttpClient()
  {
    this.httpParams = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout(this.httpParams, 20000);
    HttpConnectionParams.setSoTimeout(this.httpParams, 20000);
    HttpConnectionParams.setSocketBufferSize(this.httpParams, 8192);
    HttpClientParams.setRedirecting(this.httpParams, true);
    String str = new MyHttpCookies(this.context).getHttpProxyStr();
    if ((str != null) && (str.trim().length() > 0))
    {
      HttpHost localHttpHost = new HttpHost(str, 80);
      this.httpClient.getParams().setParameter("http.route.default-proxy", localHttpHost);
    }
    this.httpClient = new DefaultHttpClient(this.httpParams);
    this.httpClient.setHttpRequestRetryHandler(requestRetryHandler);
    return this.httpClient;
  }

  public void shutDownClient()
  {
    this.httpClient.getConnectionManager().shutdown();
  }
}

