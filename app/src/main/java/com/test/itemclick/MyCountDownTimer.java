package com.test.itemclick;

import android.os.CountDownTimer;
import android.widget.TextView;

public class MyCountDownTimer extends CountDownTimer {
    private TextView tv;


    public MyCountDownTimer(long millisInFuture, long countDownInterval, TextView tv) {
        super(millisInFuture, countDownInterval);
        this.tv = tv;
    }

    //计时过程
    @Override
    public void onTick(long l) {
        tv.setText(DateFormatUtils.getHours(l));
    }

    public void cancle() {

    }


    //计时完毕的方法
    @Override
    public void onFinish() {
        tv.setText("结束");
    }
}