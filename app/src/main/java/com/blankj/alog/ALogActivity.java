package com.blankj.alog;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
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

    private TextView tvAboutLog;

    private ALog.Config mConfig = ALog.getConfig();

    private String  dir           = "";
    private String  globalTag     = "";
    private boolean log           = true;
    private boolean console       = true;
    private boolean head          = true;
    private boolean file          = false;
    private boolean border        = true;
    private int     consoleFilter = ALog.V;
    private int     fileFilter    = ALog.V;

    private static final int UPDATE_LOG            = 0x01;
    private static final int UPDATE_CONSOLE        = 0x01 << 1;
    private static final int UPDATE_TAG            = 0x01 << 2;
    private static final int UPDATE_HEAD           = 0x01 << 3;
    private static final int UPDATE_FILE           = 0x01 << 4;
    private static final int UPDATE_DIR            = 0x01 << 5;
    private static final int UPDATE_BORDER         = 0x01 << 6;
    private static final int UPDATE_CONSOLE_FILTER = 0x01 << 7;
    private static final int UPDATE_FILE_FILTER    = 0x01 << 8;

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
        findViewById(R.id.btn_toggle_log).setOnClickListener(this);
        findViewById(R.id.btn_toggle_console).setOnClickListener(this);
        findViewById(R.id.btn_toggle_tag).setOnClickListener(this);
        findViewById(R.id.btn_toggle_head).setOnClickListener(this);
        findViewById(R.id.btn_toggle_border).setOnClickListener(this);
        findViewById(R.id.btn_toggle_file).setOnClickListener(this);
        findViewById(R.id.btn_toggle_dir).setOnClickListener(this);
        findViewById(R.id.btn_toggle_conole_filter).setOnClickListener(this);
        findViewById(R.id.btn_toggle_file_filter).setOnClickListener(this);
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
            case R.id.btn_toggle_log:
                updateAbout(UPDATE_LOG);
                break;
            case R.id.btn_toggle_console:
                updateAbout(UPDATE_CONSOLE);
                break;
            case R.id.btn_toggle_tag:
                updateAbout(UPDATE_TAG);
                break;
            case R.id.btn_toggle_head:
                updateAbout(UPDATE_HEAD);
                break;
            case R.id.btn_toggle_file:
                updateAbout(UPDATE_FILE);
                break;
            case R.id.btn_toggle_dir:
                updateAbout(UPDATE_DIR);
                break;
            case R.id.btn_toggle_border:
                updateAbout(UPDATE_BORDER);
                break;
            case R.id.btn_toggle_conole_filter:
                updateAbout(UPDATE_CONSOLE_FILTER);
                break;
            case R.id.btn_toggle_file_filter:
                updateAbout(UPDATE_FILE_FILTER);
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
                ALog.vTag("customTag", "verbose");
                ALog.dTag("customTag", "debug");
                ALog.iTag("customTag", "info");
                ALog.wTag("customTag", "warn");
                ALog.eTag("customTag", "error");
                ALog.aTag("customTag", "assert");
                break;
            case R.id.btn_log_in_new_thread:
                Thread thread = new Thread(mRunnable);
                thread.start();
                break;
            case R.id.btn_log_null:
                ALog.v((Object) null);
                ALog.d((Object) null);
                ALog.i((Object) null);
                ALog.w((Object) null);
                ALog.e((Object) null);
                ALog.a((Object) null);
                break;
            case R.id.btn_log_many_params:
                ALog.v("verbose0", "verbose1");
                ALog.vTag("customTag", "verbose0", "verbose1");
                ALog.d("debug0", "debug1");
                ALog.dTag("customTag", "debug0", "debug1");
                ALog.i("info0", "info1");
                ALog.iTag("customTag", "info0", "info1");
                ALog.w("warn0", "warn1");
                ALog.wTag("customTag", "warn0", "warn1");
                ALog.e("error0", "error1");
                ALog.eTag("customTag", "error0", "error1");
                ALog.a("assert0", "assert1");
                ALog.aTag("customTag", "assert0", "assert1");
                break;
            case R.id.btn_log_long:
                ALog.d(longStr);
                break;
            case R.id.btn_log_file:
                for (int i = 0; i < 100; i++) {
                    ALog.file("test0 log to file");
                    ALog.file(ALog.I, "test0 log to file");
                }
                break;
            case R.id.btn_log_json:
                String json = "{\"tools\": [{ \"name\":\"css format\" , \"site\":\"http://tools.w3cschool.cn/code/css\" },{ \"name\":\"json format\" , \"site\":\"http://tools.w3cschool.cn/code/json\" },{ \"name\":\"pwd check\" , \"site\":\"http://tools.w3cschool.cn/password/my_password_safe\" }]}";
                ALog.json(json);
                ALog.json(ALog.I, json);
                break;
            case R.id.btn_log_xml:
                String xml = "<books><book><author>Jack Herrington</author><title>PHP Hacks</title><publisher>O'Reilly</publisher></book><book><author>Jack Herrington</author><title>Podcasting Hacks</title><publisher>O'Reilly</publisher></book></books>";
                ALog.xml(xml);
                ALog.xml(ALog.I, xml);
                break;
        }
    }

    private void updateAbout(int args) {
        switch (args) {
            case UPDATE_LOG:
                log = !log;
                break;
            case UPDATE_CONSOLE:
                console = !console;
                break;
            case UPDATE_TAG:
                globalTag = globalTag.equals(TAG) ? "" : TAG;
                break;
            case UPDATE_HEAD:
                head = !head;
                break;
            case UPDATE_FILE:
                file = !file;
                break;
            case UPDATE_DIR:
                if (getDir().contains("test")) {
                    dir = null;
                } else {
                    if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                        dir = Environment.getExternalStorageDirectory().getPath() + System.getProperty("file.separator") + "test";
                    }
                }
                break;
            case UPDATE_BORDER:
                border = !border;
                break;
            case UPDATE_CONSOLE_FILTER:
                consoleFilter = consoleFilter == ALog.V ? ALog.W : ALog.V;
                break;
            case UPDATE_FILE_FILTER:
                fileFilter = fileFilter == ALog.V ? ALog.I : ALog.V;
                break;
        }
        mConfig.setLogSwitch(log)
                .setConsoleSwitch(console)
                .setGlobalTag(globalTag)
                .setLogHeadSwitch(head)
                .setLog2FileSwitch(file)
                .setDir(dir)
                .setBorderSwitch(border)
                .setConsoleFilter(consoleFilter)
                .setFileFilter(fileFilter);
        tvAboutLog.setText(mConfig.toString());
    }
    private String getDir() {
        return mConfig.toString().split(System.getProperty("line.separator"))[5].substring(5);
    }

    @Override
    protected void onDestroy() {
        ALogApp.getInstance().initALog();
        super.onDestroy();
    }
}
