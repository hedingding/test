package com.test.itemclick;

import android.os.Handler;

import com.test.gridviewchoicephoto.IViewEventListener;

public class DaojishiBean implements Runnable {
    private Handler handler;
    private long time;
    private IViewEventListener listener;

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    private boolean show;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public DaojishiBean(long time) {
        this.time = time;
        start();
    }

    public void start() {
        if (handler == null) {
            handler = new Handler();
            handler.post(this);
        }

    }

    public String getTimeToString() {
        return DateFormatUtils.getHours(time);
    }

    public void setListener(IViewEventListener listener) {
        this.listener = listener;
    }


    @Override
    public void run() {
        if (time >= 0) {
            time -= 1;
            handler.postDelayed(this, 1000);
            if (listener != null) {
                listener.onUpdate(null, 1);
            }
        }

    }
}
