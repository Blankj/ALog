package com.blankj.alog;

import android.app.Application;

import com.blankj.aloglibrary.ALog;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2017/04/06
 *     desc  :
 * </pre>
 */
public class ALogApp extends Application {

    public static ALog.Builder sBuilder;

    @Override
    public void onCreate() {
        super.onCreate();
        sBuilder = new ALog.Builder(this);
        sBuilder.setLogSwitch(BuildConfig.DEBUG);
    }
}
