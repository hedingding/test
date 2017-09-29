package com.test.headlistview;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.test.R;


public abstract class MyTipDialog extends ProgressDialog {
    private TextView dialogtv;
    private Button dialogb;
    private String sv;

    public MyTipDialog(Context context, String s) {
        super(context);
        sv = s;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tip_dialog);

        dialogtv = (TextView) findViewById(R.id.tip);
        dialogtv.setText(sv);
        dialogb = (Button) findViewById(R.id.ok);
        dialogb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTipDialog.this.dismiss();
                onOkClick();
            }
        });

    }


    public abstract void onOkClick();


}
