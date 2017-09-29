package com.test.itemclick;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.test.R;
import com.test.gridviewchoicephoto.GenListviewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ItemClickActivity extends Activity {

    private ListView lv;

    private List<DaojishiBean> daojishiList;
    private GenListviewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemdaojishi);
        lv = (ListView) findViewById(R.id.lv);
        daojishiList = new ArrayList<>();
        long currentTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            daojishiList.add(new DaojishiBean(300000 * i));

        }
        adapter = new GenListviewAdapter(R.layout.daojishi_item);
        adapter.addAllItem(daojishiList);
        lv.setAdapter(adapter);
    }

}
