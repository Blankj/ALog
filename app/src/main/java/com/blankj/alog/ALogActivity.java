package com.blankj.alog;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.blankj.ALog;


/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2017/04/06
 *     desc  : ALog的Demo
 * </pre>
 */
public class ALogActivity extends AppCompatActivity
        implements View.OnClickListener {

    private static final String TAG = "CMJ";

    private ALog.Builder mBuilder;

    private TextView tvAboutLog;

    private String  globalTag = "";
    private boolean head      = true;
    private boolean file      = false;
    private boolean border    = true;
    private int     filter    = ALog.V;

    private static final int UPDATE_TAG    = 0x01 << 0;
    private static final int UPDATE_HEAD   = 0x01 << 1;
    private static final int UPDATE_FILE   = 0x01 << 2;
    private static final int UPDATE_BORDER = 0x01 << 3;
    private static final int UPDATE_FILTER = 0x01 << 4;

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            ALog.v("verbose");
            ALog.d("debug");
            ALog.i("info");
            ALog.w("warn");
            ALog.e("error");
            ALog.a("assert");
        }
    };

    private static final String longStr;

    private String json = "{\"tools\": [{ \"name\":\"css format\" , \"site\":\"http://tools.w3cschool.cn/code/css\" },{ \"name\":\"json format\" , \"site\":\"http://tools.w3cschool.cn/code/json\" },{ \"name\":\"pwd check\" , \"site\":\"http://tools.w3cschool.cn/password/my_password_safe\" }]}";
    private String xml  = "<books><book><author>Jack Herrington</author><title>PHP Hacks</title><publisher>O'Reilly</publisher></book><book><author>Jack Herrington</author><title>Podcasting Hacks</title><publisher>O'Reilly</publisher></book></books>";

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("len = 10400\ncontent = \"");
        for (int i = 0; i < 800; ++i) {
            sb.append("Hello world. ");
        }
        sb.append("\"");
        longStr = sb.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alog);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitleTextColor(Color.WHITE);
            setSupportActionBar(toolbar);
        }

        mBuilder = ALogApp.sBuilder;
        findViewById(R.id.btn_toggle_tag).setOnClickListener(this);
        findViewById(R.id.btn_toggle_head).setOnClickListener(this);
        findViewById(R.id.btn_toggle_border).setOnClickListener(this);
        findViewById(R.id.btn_toggle_file).setOnClickListener(this);
        findViewById(R.id.btn_toggle_filter).setOnClickListener(this);
        findViewById(R.id.btn_log_no_tag).setOnClickListener(this);
        findViewById(R.id.btn_log_with_tag).setOnClickListener(this);
        findViewById(R.id.btn_log_in_new_thread).setOnClickListener(this);
        findViewById(R.id.btn_log_null).setOnClickListener(this);
        findViewById(R.id.btn_log_many_params).setOnClickListener(this);
        findViewById(R.id.btn_log_long).setOnClickListener(this);
        findViewById(R.id.btn_log_file).setOnClickListener(this);
        findViewById(R.id.btn_log_json).setOnClickListener(this);
        findViewById(R.id.btn_log_xml).setOnClickListener(this);
        tvAboutLog = (TextView) findViewById(R.id.tv_about_log);
        updateAbout(0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_toggle_tag:
                updateAbout(UPDATE_TAG);
                break;
            case R.id.btn_toggle_head:
                updateAbout(UPDATE_HEAD);
                break;
            case R.id.btn_toggle_file:
                updateAbout(UPDATE_FILE);
                break;
            case R.id.btn_toggle_border:
                updateAbout(UPDATE_BORDER);
                break;
            case R.id.btn_toggle_filter:
                updateAbout(UPDATE_FILTER);
                break;
            case R.id.btn_log_no_tag:
                ALog.v("verbose");
                ALog.d("debug");
                ALog.i("info");
                ALog.w("warn");
                ALog.e("error");
                ALog.a("assert");
                break;
            case R.id.btn_log_with_tag:
                ALog.v("customTag", "verbose");
                ALog.d("customTag", "debug");
                ALog.i("customTag", "info");
                ALog.w("customTag", "warn");
                ALog.e("customTag", "error");
                ALog.a("customTag", "assert");
                break;
            case R.id.btn_log_in_new_thread:
                Thread thread = new Thread(mRunnable);
                thread.start();
                break;
            case R.id.btn_log_null:
                ALog.v(null);
                ALog.d(null);
                ALog.i(null);
                ALog.w(null);
                ALog.e(null);
                ALog.a(null);
                break;
            case R.id.btn_log_many_params:
                ALog.v("customTag", "verbose0", "verbose1");
                ALog.d("customTag", "debug0", "debug1");
                ALog.i("customTag", "info0", "info1");
                ALog.w("customTag", "warn0", "warn1");
                ALog.e("customTag", "error0", "error1");
                ALog.a("customTag", "assert0", "assert1");
                break;
            case R.id.btn_log_long:
                ALog.d(longStr);
                break;
            case R.id.btn_log_file:
                ALog.file("test0 log to file");
                ALog.file("test1 log to file");
                ALog.file("test2 log to file");
                break;
            case R.id.btn_log_json:
                ALog.json(json);
                break;
            case R.id.btn_log_xml:
                ALog.xml(xml);
                break;
        }
    }

    private void updateAbout(int args) {
        switch (args) {
            case UPDATE_TAG:
                globalTag = globalTag.equals(TAG) ? "" : TAG;
                break;
            case UPDATE_HEAD:
                head = !head;
                break;
            case UPDATE_FILE:
                file = !file;
                break;
            case UPDATE_BORDER:
                border = !border;
                break;
            case UPDATE_FILTER:
                filter = filter == ALog.V ? ALog.W : ALog.V;
                break;
        }
        mBuilder.setGlobalTag(globalTag)
                .setLogHeadSwitch(head)
                .setLog2FileSwitch(file)
                .setBorderSwitch(border)
                .setLogFilter(filter);
        tvAboutLog.setText("tag: " + (globalTag.equals("") ? "null" : TAG)
                + "\nhead: " + String.valueOf(head)
                + "\nfile: " + String.valueOf(file)
                + "\nborder: " + String.valueOf(border)
                + "\nfilter: " + (filter == ALog.V ? "Verbose" : "Warn")
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_git_hub:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Blankj/ALog")));
                break;
            case R.id.action_blog:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.jianshu.com/u/46702d5c6978")));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
