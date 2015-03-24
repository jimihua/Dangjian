package com.star.common;

import java.util.Iterator;
import java.util.Stack;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

public class AppManager
{
  private static Stack<Activity> activityStack;
  private static AppManager instance;

  public static AppManager getAppManager()
  {
    if (instance == null)
      instance = new AppManager();
    return instance;
  }

  @SuppressWarnings("deprecation")
public void AppExit(Context paramContext)
  {
    try
    {
      
      finishAllActivity();
      ((ActivityManager)paramContext.getSystemService("activity")).restartPackage(paramContext.getPackageName());
      System.exit(0);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void addActivity(Activity paramActivity)
  {
    if (activityStack == null)
      activityStack = new Stack();
    activityStack.add(paramActivity);
  }

  public Activity currentActivity()
  {
    return (Activity)activityStack.lastElement();
  }

  public void finishActivity()
  {
    finishActivity((Activity)activityStack.lastElement());
  }

  public void finishActivity(Activity paramActivity)
  {
    if (paramActivity != null)
    {
      activityStack.remove(paramActivity);
      paramActivity.finish();
    }
  }

  public void finishActivity(Class<?> paramClass)
  {
    Iterator localIterator = activityStack.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      Activity localActivity = (Activity)localIterator.next();
      if (localActivity.getClass().equals(paramClass))
        finishActivity(localActivity);
    }
  }

  public void finishAllActivity()
  {
    int i = 0;
    int j = activityStack.size();
    while (true)
    {
      if (i >= j)
      {
        activityStack.clear();
        return;
      }
      if (activityStack.get(i) != null)
        ((Activity)activityStack.get(i)).finish();
      i++;
    }
  }
}
