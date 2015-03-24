package com.star.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetWorking
{
  public static int getConnectedType(Context paramContext)
  {
    if (paramContext != null)
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable()))
        return localNetworkInfo.getType();
    }
    return -1;
  }

  public static boolean isMobileConnected(Context paramContext)
  {
    boolean bool = false;
    if (paramContext != null)
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getNetworkInfo(0);
      bool = false;
      if (localNetworkInfo != null)
        bool = localNetworkInfo.isAvailable();
    }
    return bool;
  }

  public static boolean isNetworkConnected(Context paramContext)
  {
    if (paramContext != null)
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (localNetworkInfo != null)
        return localNetworkInfo.isAvailable();
    }
    return false;
  }

  public static boolean isWifiConnected(Context paramContext)
  {
    if (paramContext != null)
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getNetworkInfo(1);
      if (localNetworkInfo != null)
        return localNetworkInfo.isAvailable();
    }
    return false;
  }
}

