package com.test.itemclick;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.test.R;
import com.test.gridviewchoicephoto.GenLinearItem;
import com.test.gridviewchoicephoto.IViewEventListener;

public class DaojishiItem extends GenLinearItem implements OnClickListener, IViewEventListener {
    private Button b;
    private TextView tv;
    private DaojishiBean bean;

    public DaojishiItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onInit() {
        b = (Button) findViewById(R.id.b);
        tv = (TextView) findViewById(R.id.tv);
        b.setOnClickListener(this);

    }

    protected void onSetData(Object o) {
        bean = (DaojishiBean) o;
        bean.setListener(this);
        bean.setShow(true);
        if (bean.isShow()) {
            if (bean.getTime() <= 0) {
                tv.setText("结束");
            } else {
                tv.setText(bean.getTimeToString());
            }
            tv.setVisibility(VISIBLE);
        } else {
            tv.setVisibility(VISIBLE);
        }


    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b:

                tv.setVisibility(VISIBLE);

                break;
        }
    }

    @Override
    public void onUpdate(View paramView, int paramInt) {
        if (bean.getTime() <= 0) {
            tv.setText("结束");
        } else {
            tv.setText(bean.getTimeToString());
        }

    }
}
