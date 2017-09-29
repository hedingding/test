package com.test.headlistview;

import android.content.Context;
import android.view.View;

import com.squareup.okhttp.Request;
import com.test.gridviewchoicephoto.LogUtils;

public abstract class HttpGet {
    public String re;

    public HttpGet(final String url, final Context c) {

        OkHttpClientManager.getAsyn(url, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                MyTipDialog mt = new MyTipDialog(c, "网络请求失败,请重试") {
                    @Override
                    public void onOkClick() {
                        dismiss();
                    }
                };
                mt.show();
                mt.setCanceledOnTouchOutside(false);
            }

            @Override
            public void onResponse(String s) {
                LogUtils.showReqLog(url, null, s);
                re = s;
                OK();
            }
        });
    }


    public HttpGet(final String url, final Context c, final View view) {
        view.setEnabled(false);
        OkHttpClientManager.getAsyn(url, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                MyTipDialog mt = new MyTipDialog(c, "网络请求失败,请重试") {
                    @Override
                    public void onOkClick() {
                        view.setEnabled(true);
                        dismiss();
                    }
                };
                mt.show();
                mt.setCanceledOnTouchOutside(false);
            }

            @Override
            public void onResponse(String s) {
                view.setEnabled(true);
                LogUtils.showReqLog(url, null, s);
                re = s;
                OK();
            }
        });
    }


    public abstract void OK();
}