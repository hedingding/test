package com.test.headlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.test.R;
import com.test.gridviewchoicephoto.GenLinearItem;


public class ChoiceAddlItem extends GenLinearItem {
    private TextView name, letter;
    private View v;


    public ChoiceAddlItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onInit() {
        name = (TextView) findViewById(R.id.name);
        letter = (TextView) findViewById(R.id.letter);
        v = findViewById(R.id.v);
    }

    protected void onSetData(Object o) {
        AddBean.DataBean f = (AddBean.DataBean) o;
        name.setText(f.getName());
        letter.setText(f.getLetter());

        if (f.isshow()) {
            letter.setVisibility(View.VISIBLE);
            v.setVisibility(View.VISIBLE);
        } else {
            letter.setVisibility(View.GONE);
            v.setVisibility(View.GONE);
        }


    }
}


