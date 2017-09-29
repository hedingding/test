package com.test.headlistview;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.test.R;

import java.util.ArrayList;
import java.util.List;

public class Const {


    public static DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showStubImage(R.mipmap.waitlogin)          // 设置图片下载期间显示的图片
            .showImageForEmptyUri(R.mipmap.waitlogin)  // 设置图片Uri为空或是错误的时候显示的图片
            .showImageOnFail(R.mipmap.waitlogin)       // 设置图片加载或解码过程中发生错误显示的图片
            .cacheInMemory(true)                        // 设置下载的图片是否缓存在内存中
            .cacheOnDisc(true)                          // 设置下载的图片是否缓存在SD卡中
            //.displayer(new RoundedBitmapDisplayer(20))  // 设置成圆角图片
            .build();


    public static void ShowMyTipDialog(Context context, String s) {
        MyTipDialog mt = new MyTipDialog(context, s) {
            @Override
            public void onOkClick() {

            }
        };
        mt.show();
        mt.setCanceledOnTouchOutside(false);
    }

    public static void setFullScreen(ProgressDialog bd) {
        bd.show();
        bd.setIndeterminate(false);
        bd.setCanceledOnTouchOutside(false);
        Window window = bd.getWindow();
        if (bd != null && window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams attr = window.getAttributes();
            if (attr != null) {
                attr.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                attr.width = ViewGroup.LayoutParams.MATCH_PARENT;
                attr.gravity = Gravity.BOTTOM;
                window.setAttributes(attr);
            }
        }
    }


    public static List<String> getItem(String[] items) {
        List<String> ls = new ArrayList<>();
        for (String s : items) {
            ls.add(s);
        }
        return ls;
    }


}