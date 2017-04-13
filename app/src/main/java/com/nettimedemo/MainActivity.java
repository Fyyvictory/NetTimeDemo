package com.nettimedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * String webUrl1 = "http://www.bjtime.cn";//bjTime
 * String webUrl3 = "http://www.taobao.com";//淘宝
 * String webUrl4 = "http://www.ntsc.ac.cn";//中国科学院国家授时中心(亲测可用)
 * String webUrl5 = "http://www.360.cn";//360
 * String webUrl6 = "http://www.beijing-time.org";//beijing-time
 */

public class MainActivity extends AppCompatActivity {

    protected TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //ntsc.ac.cn
                    URL mURL = new URL("http://www.ntsc.ac.cn");
                    URLConnection mConnection = mURL.openConnection();
                    mConnection.connect();
                    long mDate = mConnection.getDate();
                    Date mDate1 = new Date(mDate);
                    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                    final String mS = mFormat.format(mDate1);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            text.setText(mS);
                        }
                    });
                    Log.e("date", mS);
                } catch (MalformedURLException mE) {
                    mE.printStackTrace();
                } catch (IOException mE) {
                    mE.printStackTrace();
                }
            }
        }).start();
    }

    private void initView() {
        text = (TextView) findViewById(R.id.text);
    }
}
