package com.test.headlistview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.test.R;
import com.test.gridviewchoicephoto.GenListviewAdapter;
import com.test.gridviewchoicephoto.LogUtils;
import com.test.gridviewchoicephoto.ToastManager;

import java.util.ArrayList;
import java.util.List;

public class HeadListViewActivity extends Activity implements Runnable {

    private AddBean addbean;
    private SuoYin suoyin;
    private GenListviewAdapter lvadapter;
    private ListView lv;
    private LinearLayout headItem;
    private TextView float_letter, letter;

    private ShouYeInfoBean syinfoBean;
    private ViewPager adViewPager;
    private List<ImageView> imageViews;
    private List<View> dots;
    private List<View> dotList;
    private int currentItem = 0;
    private View[] pointViews;
    private int[] pointViewIds = {R.id.v_dot0, R.id.v_dot1, R.id.v_dot2, R.id.v_dot3, R.id.v_dot4, R.id.v_dot5, R.id
            .v_dot6, R.id.v_dot7, R.id.v_dot8, R.id.v_dot9};
    private Handler h;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headlistview);

        new HttpGet("http://122.97.128.155:8081/index.php/AppComm-getCityList?", this) {
            @Override
            public void OK() {
                addbean = JSON.parseObject(re, AddBean.class);

                ArrayList<String> ls = new ArrayList<String>();

                for (int i = 0; i < addbean.getData().size(); i++) {
                    String letter = addbean.getData().get(i).getLetter();
                    boolean isin = false;

                    for (int z = 0; z < ls.size(); z++) {
                        if (letter.equals(ls.get(z))) {
                            isin = true;
                        }

                    }

                    if (!isin) {
                        ls.add(letter);
                    }


                }
                String[] str = new String[ls.size() + 1];
                str[0] = "";
                for (int a = 0; a < ls.size(); a++) {
                    str[a + 1] = ls.get(a);
                }

                SuoYin.letters = str;


                initView();
                initListTitle();
            }
        };


    }

    private void initListTitle() {
        new HttpGet("http://zgzlw.zilankeji.com/sindex/index?prov=安徽", this) {
            @Override
            public void OK() {
                syinfoBean = JSON.parseObject(re, ShouYeInfoBean.class);

                headItem = (LinearLayout) LayoutInflater.from(HeadListViewActivity.this).inflate(R.layout
                        .listview_head, null);


                if (syinfoBean.getStatus() == 1) {
                    imageViews = new ArrayList<>();
                    dots = new ArrayList<>();
                    dotList = new ArrayList<>();


                    pointViews = new View[pointViewIds.length];
                    for (int i = 0; i < pointViewIds.length; i++) {
                        pointViews[i] = headItem.findViewById(pointViewIds[i]);
                        dots.add(pointViews[i]);
                    }

                    adViewPager = (ViewPager) headItem.findViewById(R.id.vp);


                    for (int i = 0; i < syinfoBean.getAdv().size(); i++) {
                        ImageView imageView = new ImageView(HeadListViewActivity.this);

                        ImageLoader.getInstance().displayImage("http://zgzlw.zilankeji.com/" + syinfoBean.getAdv()
                                        .get(i)
                                        .getImg(),
                                imageView, Const.options);
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                        imageViews.add(imageView);
                        dots.get(i).setVisibility(View.VISIBLE);
                        dotList.add(dots.get(i));


                    }

                    adViewPager.setAdapter(new ShouYeBannerAdapter(HeadListViewActivity.this, syinfoBean.getAdv(),
                            imageViews));//
                    // 设置填充ViewPager页面的适配器
                    // 设置一个监听器，当ViewPager中的页面改变时调用
                    adViewPager.addOnPageChangeListener(new MyPageChangeListener());

                    if (h == null) {
                        h = new Handler();
                    }
                    h.removeCallbacks(HeadListViewActivity.this);
                    h.postDelayed(HeadListViewActivity.this, 3000);

                    lv.addHeaderView(headItem);//添加头部

                    lv.addHeaderView(LayoutInflater.from(HeadListViewActivity.this).inflate(R.layout
                            .listview_headtest, null));

                    lv.addHeaderView(LayoutInflater.from(HeadListViewActivity.this).inflate(R.layout
                            .listview_headtest, null));
                    lv.addHeaderView(LayoutInflater.from(HeadListViewActivity.this).inflate(R.layout
                            .listview_headtest, null));
                    lv.addHeaderView(LayoutInflater.from(HeadListViewActivity.this).inflate(R.layout
                            .listview_headtest, null));
                    lv.addHeaderView(LayoutInflater.from(HeadListViewActivity.this).inflate(R.layout
                            .listview_headtest, null));
                    lv.addHeaderView(LayoutInflater.from(HeadListViewActivity.this).inflate(R.layout
                            .listview_headtest, null));
                    lv.addFooterView(LayoutInflater.from(HeadListViewActivity.this).inflate(R.layout
                            .listview_headtest, null));
                    lv.addFooterView(LayoutInflater.from(HeadListViewActivity.this).inflate(R.layout
                            .listview_headtest, null));
                    lv.addFooterView(LayoutInflater.from(HeadListViewActivity.this).inflate(R.layout
                            .listview_headtest, null));
                    lv.addFooterView(LayoutInflater.from(HeadListViewActivity.this).inflate(R.layout
                            .listview_headtest, null));
                    lv.addFooterView(LayoutInflater.from(HeadListViewActivity.this).inflate(R.layout
                            .listview_headtest, null));
                    lv.addFooterView(LayoutInflater.from(HeadListViewActivity.this).inflate(R.layout
                            .listview_headtest, null));
                    lv.addFooterView(LayoutInflater.from(HeadListViewActivity.this).inflate(R.layout
                            .listview_headtest, null));
                    lv.addFooterView(LayoutInflater.from(HeadListViewActivity.this).inflate(R.layout
                            .listview_headtest, null));


                }
            }
        };


    }

    private void initView() {
        lv = (ListView) findViewById(R.id.lv);
        letter = (TextView) findViewById(R.id.letter);
        lvadapter = new GenListviewAdapter(R.layout.my_city_item);
        lv.setAdapter(lvadapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtils.e("" + parent.getCount());
                if (position >= 7 && position <= parent.getCount() - 9) {
                    new ToastManager(HeadListViewActivity.this).show(addbean.getData().get(position - 7).getName());
                } else {
                    new ToastManager(HeadListViewActivity.this).show("我点了一个刷存在感的View");
                }

            }
        });


        ArrayList<String> ls = new ArrayList<String>();

        for (int i = 0; i < addbean.getData().size(); i++) {
            String letter = addbean.getData().get(i).getLetter();
            boolean isin = false;

            for (int z = 0; z < ls.size(); z++) {
                if (letter.equals(ls.get(z))) {
                    isin = true;
                }

            }

            if (!isin) {
                addbean.getData().get(i).setIsshow(true);
                ls.add(letter);
            } else {
                addbean.getData().get(i).setIsshow(false);
            }


        }
        String[] str = new String[ls.size() + 1];
        str[0] = "";
        for (int a = 0; a < ls.size(); a++) {
            str[a + 1] = ls.get(a);
        }

        SuoYin.letters = str;

        initsuoyin();
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (firstVisibleItem >= 7 && firstVisibleItem <= totalItemCount - 9) {
                    suoyin.setVisibility(View.VISIBLE);
                    letter.setVisibility(View.VISIBLE);
                    letter.setText(addbean.getData().get(firstVisibleItem - 7).getLetter());
                } else {
                    suoyin.setVisibility(View.GONE);
                    letter.setVisibility(View.GONE);
                }
            }
        });
        lvadapter.addAllItem(addbean.getData());


    }

    private void initsuoyin() {
        suoyin = (SuoYin) findViewById(R.id.slideBar);
        float_letter = (TextView) findViewById(R.id.float_letter);
        suoyin.setOnTouchLetterChangeListenner(new SuoYin.OnTouchLetterChangeListenner() {

            @Override
            public void onTouchLetterChange(MotionEvent event, String s) {

                float_letter.setText(s);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        float_letter.setVisibility(View.VISIBLE);
                        if (float_letter.getText().toString().equals("A")) {
                            float_letter.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    float_letter.setVisibility(View.GONE);
                                }
                            }, 500);


                        }
                        break;

                    case MotionEvent.ACTION_UP:
                    default:
                        float_letter.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                float_letter.setVisibility(View.GONE);
                            }
                        }, 100);
                        break;
                }
                for (int i = 0; i < addbean.getData().size(); i++) {
                    AddBean.DataBean ocm = addbean.getData().get(i);
                    if (ocm.getLetter().equals(s)) {
                        lv.setSelection(i + 7);
                        break;
                    }
                }
                // int position = letterCityList.indexOf(s);//这个array就是传给自定义Adapter的
                //  lv.setSelection(position);//调用ListView的setSelection()方法就可实现了
            }
        });

    }

    @Override
    public void run() {
        currentItem = (currentItem + 1) % imageViews.size();
        adViewPager.setCurrentItem(currentItem);
        h.postDelayed(this, 3000);
    }

    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        private int oldPosition = 0;

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            h.removeCallbacks(HeadListViewActivity.this);
            h.postDelayed(HeadListViewActivity.this, 3000);
        }

        @Override
        public void onPageSelected(int position) {
            currentItem = position;
            dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
            dots.get(position).setBackgroundResource(R.drawable.dot_focused);
            oldPosition = position;
        }
    }

}
