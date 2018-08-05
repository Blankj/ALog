package com.blankj.alog;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.blankj.ALog;

import java.util.ArrayList;


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

    private static final String TAG                   = "CMJ";
    private static final int    UPDATE_LOG            = 0x01;
    private static final int    UPDATE_CONSOLE        = 0x01 << 1;
    private static final int    UPDATE_TAG            = 0x01 << 2;
    private static final int    UPDATE_HEAD           = 0x01 << 3;
    private static final int    UPDATE_FILE           = 0x01 << 4;
    private static final int    UPDATE_DIR            = 0x01 << 5;
    private static final int    UPDATE_BORDER         = 0x01 << 6;
    private static final int    UPDATE_SINGLE         = 0x01 << 7;
    private static final int    UPDATE_CONSOLE_FILTER = 0x01 << 8;
    private static final int    UPDATE_FILE_FILTER    = 0x01 << 9;

    private static final String            JSON        = "{\"tools\": [{ \"name\":\"css format\" , \"site\":\"http://tools.w3cschool.cn/code/css\" },{ \"name\":\"JSON format\" , \"site\":\"http://tools.w3cschool.cn/code/JSON\" },{ \"name\":\"pwd check\" , \"site\":\"http://tools.w3cschool.cn/password/my_password_safe\" }]}";
    private static final String            XML         = "<books><book><author>Jack Herrington</author><title>PHP Hacks</title><publisher>O'Reilly</publisher></book><book><author>Jack Herrington</author><title>Podcasting Hacks</title><publisher>O'Reilly</publisher></book></books>";
    private static final int[]             ONE_D_ARRAY = new int[]{1, 2, 3};
    private static final int[][]           TWO_D_ARRAY = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    private static final Throwable         THROWABLE   = new NullPointerException();
    private static final Bundle            BUNDLE      = new Bundle();
    private static final Intent            INTENT      = new Intent();
    private static final ArrayList<String> LIST        = new ArrayList<>();

    private static final String LONG_STR;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("len = 10400\ncontent = \"");
        for (int i = 0; i < 800; ++i) {
            sb.append("Hello world. ");
        }
        sb.append("\"");
        LONG_STR = sb.toString();

        BUNDLE.putByte("byte", (byte) -1);
        BUNDLE.putChar("char", 'c');
        BUNDLE.putCharArray("charArray", new char[]{'c', 'h', 'a', 'r', 'A', 'r', 'r', 'a', 'y'});
        BUNDLE.putCharSequence("charSequence", "charSequence");
        BUNDLE.putCharSequenceArray("charSequenceArray", new CharSequence[]{"char", "Sequence", "Array"});
        BUNDLE.putBundle("bundle", BUNDLE);
        BUNDLE.putBoolean("boolean", true);
        BUNDLE.putInt("int", 1);
        BUNDLE.putFloat("float", 1.f);
        BUNDLE.putLong("long", 1L);
        BUNDLE.putShort("short", (short) 1);

        INTENT.setAction("ALog action");
        INTENT.addCategory("ALog category");
        INTENT.setData(Uri.parse("https://blankj.com"));
        INTENT.setType(Intent.ACTION_DIAL);
        INTENT.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        INTENT.setPackage("com.blankj.alog");
        INTENT.setComponent(new ComponentName("com.blankj.alog", ALogActivity.class.toString()));
        INTENT.putExtra("int", 1);
        INTENT.putExtra("float", 1f);
        INTENT.putExtra("char", 'c');
        INTENT.putExtra("string", "string");
        INTENT.putExtra("intArray", ONE_D_ARRAY);
        ArrayList<String> list = new ArrayList<>();
        list.add("ArrayList");
        list.add("is");
        list.add("serializable");
        INTENT.putExtra("serializable", list);
        INTENT.putExtra("bundle", BUNDLE);

        LIST.add("Hello");
        LIST.add("ALog");
    }

    private TextView tvAboutLog;

    private ALog.Config mConfig = ALog.getConfig();

    private String  dir           = "";
    private String  globalTag     = "";
    private boolean log           = true;
    private boolean console       = true;
    private boolean head          = true;
    private boolean file          = false;
    private boolean border        = true;
    private boolean single        = true;
    private int     consoleFilter = ALog.V;
    private int     fileFilter    = ALog.V;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alog);
        tvAboutLog = findViewById(R.id.tv_about_log);
        findViewById(R.id.btn_toggle_log).setOnClickListener(this);
        findViewById(R.id.btn_toggle_console).setOnClickListener(this);
        findViewById(R.id.btn_toggle_tag).setOnClickListener(this);
        findViewById(R.id.btn_toggle_head).setOnClickListener(this);
        findViewById(R.id.btn_toggle_border).setOnClickListener(this);
        findViewById(R.id.btn_toggle_single).setOnClickListener(this);
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
        findViewById(R.id.btn_log_array).setOnClickListener(this);
        findViewById(R.id.btn_log_throwable).setOnClickListener(this);
        findViewById(R.id.btn_log_bundle).setOnClickListener(this);
        findViewById(R.id.btn_log_intent).setOnClickListener(this);
        findViewById(R.id.btn_log_array_list).setOnClickListener(this);
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
            case R.id.btn_toggle_single:
                updateAbout(UPDATE_SINGLE);
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
                ALog.d(LONG_STR);
                break;
            case R.id.btn_log_file:
                for (int i = 0; i < 100; i++) {
                    ALog.file("test0 log to file");
                    ALog.file(ALog.I, "test0 log to file");
                }
                break;
            case R.id.btn_log_json:
                ALog.json(JSON);
                ALog.json(ALog.I, JSON);
                break;
            case R.id.btn_log_xml:
                ALog.xml(XML);
                ALog.xml(ALog.I, XML);
                break;
            case R.id.btn_log_array:
                ALog.e((Object) ONE_D_ARRAY);
                ALog.e((Object) TWO_D_ARRAY);
                break;
            case R.id.btn_log_throwable:
                ALog.e(THROWABLE);
                break;
            case R.id.btn_log_bundle:
                ALog.e(BUNDLE);
                break;
            case R.id.btn_log_intent:
                ALog.e(INTENT);
                break;
            case R.id.btn_log_array_list:
                ALog.e(LIST);
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
            case UPDATE_SINGLE:
                single = !single;
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
                .setSingleTagSwitch(single)
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
