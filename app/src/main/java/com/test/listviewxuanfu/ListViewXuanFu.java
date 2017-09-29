package com.test.listviewxuanfu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.test.R;

public class ListViewXuanFu extends Activity {
    private ListView lv;

    private LinearLayout invis;

    private String[] strs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky);
        invis = (LinearLayout) findViewById(R.id.invis);

        strs = new String[20];

        for (int i = 0; i < 20; i++) {
            strs[i] = "data-----" + i;
        }

        lv = (ListView) findViewById(R.id.lv);
        View header = View.inflate(this, R.layout.stick_header, null);//头部内容
        lv.addHeaderView(header);//添加头部
        lv.addHeaderView(View.inflate(this, R.layout.stick_action, null));//ListView条目中的悬浮部分 添加到头部

        lv.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, strs));
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem >= 1) {
                    invis.setVisibility(View.VISIBLE);
                } else {

                    invis.setVisibility(View.GONE);
                }
            }
        });
    }

}
