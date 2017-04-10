package com.blankj.alog;

import android.app.Application;

import com.blankj.ALog;

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
        sBuilder = new ALog.Builder(this)
                .setLogSwitch(BuildConfig.DEBUG)// 设置log总开关，默认开
                .setGlobalTag("")// 设置log全局标签，默认为空
                // 当全局标签不为空时，我们输出的log全部为该tag，
                // 为空时，如果传入的tag为空那就显示类名，否则显示tag
                .setLogHeadSwitch(true)// 设置log头部是否显示，默认显示
                .setLog2FileSwitch(false)// 打印log时是否存到文件的开关，默认关
                .setBorderSwitch(true)// 输出日志是否带边框开关，默认开
                .setLogFilter(ALog.V);// log过滤器，和logcat过滤器同理，默认Verbose
    }
}
