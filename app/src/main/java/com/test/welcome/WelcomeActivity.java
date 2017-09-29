package com.test.welcome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.test.R;
import com.test.headlistview.Const;
import com.test.headlistview.HeadListViewActivity;
import com.test.itemclick.ItemClickActivity;
import com.test.listviewxuanfu.ListViewXuanFu;
import com.test.gridviewchoicephoto.GenListviewAdapter;
import com.test.gridviewchoicephoto.MainActivity;
import com.test.testgif.TestGifActivity;
import com.test.updateapk.UpDateApkActivity;

public class WelcomeActivity extends Activity implements AdapterView.OnItemClickListener {


    private String[] items = {"GridView照片选择", "Item倒计时", "gif播放", "带头部的可以的选择的ListView", "ListView带悬浮,超简单", "下载更新apk"};
    private GenListviewAdapter adapter;
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(this);
        ImageLoader.getInstance().init(configuration);

        lv = (ListView) findViewById(R.id.lv);
        adapter = new GenListviewAdapter(R.layout.welcome_item);
        adapter.addAllItem(Const.getItem(items));
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, ItemClickActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, TestGifActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, HeadListViewActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, ListViewXuanFu.class));
                break;
            case 5:
                startActivity(new Intent(this, UpDateApkActivity.class));
                break;


        }
    }
}
