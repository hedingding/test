package com.test.welcome;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.test.R;
import com.test.gridviewchoicephoto.GenLinearItem;

public class WelcomeItem extends GenLinearItem {
    private TextView tv;

    public WelcomeItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onInit() {
        tv = (TextView) findViewById(R.id.tv);

    }

    protected void onSetData(Object o) {
        String s = (String) o;
        tv.setText(s);

    }
}
