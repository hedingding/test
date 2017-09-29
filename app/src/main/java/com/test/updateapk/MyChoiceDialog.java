package com.test.updateapk;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.test.R;


public abstract class MyChoiceDialog extends Dialog {
    private Button cancle, ok;
    private TextView tip;
    private String title;

    public MyChoiceDialog(Context context, String title) {
        super(context, R.style.Dialog);
        this.title = title;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_dialog);

        tip = (TextView) findViewById(R.id.tip);
        tip.setText(title);

        cancle = (Button) findViewById(R.id.cancle);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancleClick();
                MyChoiceDialog.this.dismiss();
            }
        });

        ok = (Button) findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOkClick();
                MyChoiceDialog.this.dismiss();
            }
        });


    }


    public abstract void onCancleClick();

    public abstract void onOkClick();


}
