package com.test.headlistview;


import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.test.gridviewchoicephoto.ToastManager;

import java.util.List;

public class ShouYeBannerAdapter extends PagerAdapter {
    private List<ShouYeInfoBean.AdvBean> adList;
    private List<ImageView> imageViews;
    private Context ctx;

    public ShouYeBannerAdapter(Context ctx, List<ShouYeInfoBean.AdvBean> adList, List<ImageView> imageViews) {
        this.adList = adList;
        this.imageViews = imageViews;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return adList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = imageViews.get(position);
        ((ViewPager) container).addView(iv);
        final ShouYeInfoBean.AdvBean adDomain = adList.get(position);
        // 在这个方法里面设置图片的点击事件
        iv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new ToastManager(ctx).show("您点击了头部轮播图,此图片ID为" + adDomain.getId());

//                    Intent i = new Intent(getActivity(), WebViewActivity.class);
//                    Bundle b = new Bundle();
//                    b.putString("key", adDomain.getUrl());
//                    i.putExtras(b);
//                    startActivity(i);
            }
        });
        return iv;
    }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {

    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void startUpdate(View arg0) {

    }

    @Override
    public void finishUpdate(View arg0) {

    }

}