package com.test.updateapk;

import android.app.Activity;
import android.os.Bundle;

import com.test.R;
import com.test.gridviewchoicephoto.ToastManager;

public class UpDateApkActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateapk);


        MyChoiceDialog mc = new MyChoiceDialog(this, "检测到版本更新,是否更新") {
            @Override
            public void onCancleClick() {
                new ToastManager(UpDateApkActivity.this).show("日");
            }

            @Override
            public void onOkClick() {
                DownLoadApkDialog dl = new DownLoadApkDialog(UpDateApkActivity.this, "http://app.znds" +
                        ".com/down/20170314/dsj2.0_2.9.10_dangbei.apk");
                dl.show();
                dl.setCanceledOnTouchOutside(false);

            }
        };
        mc.show();
        mc.setCanceledOnTouchOutside(false);
    }

}
